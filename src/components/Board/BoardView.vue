<template>
        <section class="page-section" id="contact">
            <div class="container">
              <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">Detail</h2>
                        <hr class="divider" />
                    </div>
                </div>
                <div class="row">
                  <div class="col">
                    <button class="btn btn-primary btn-xs" id="listButton" type="button" @click="list">목록</button>
                  </div>
                  <div class="col" v-if="user.userid === article.userid">
                     <button class="btn btn-primary btn-xs" id="updateButton" type="button" @click="moveUpdate">수정하기</button>
                     <button class="btn btn-primary btn-xs" id="delButton" type="button" @click="del">삭제하기</button>
                  </div>
                </div>
                <div class="text-center">
                    <table class="table">
                      <tbody>
                            <tr>
                                <th class="head" scope="row">글번호</th>
                                <td>{{ article.articleno }}</td>
                            </tr>
                            <tr>
                                <th class="head" scope="row">제목</th>
                                <td>{{ article.subject }}</td>
                            </tr>
                            <tr>
                                <th class="head" scope="row">조회수</th>
                                <td>{{ article.hit }}</td>
                            </tr>
                            <tr>
                                <th class="head" scope="row">작성자</th>
                                <td>{{ article.userid }}</td>
                            </tr>
                            <tr>
                                <th class="head" scope="row">작성일</th>
                                <td>{{ article.regtime }}</td>
                            </tr>
                            <tr class="content">
                                <th class="head" scope="row">내용</th>
                                <td>{{ article.content }}</td>
                            </tr>
                      </tbody>
                    </table>
                    <!-- Message input-->
                    <div class="form-floating mb-3">
                        <textarea class="form-control" id="message" type="text" placeholder="Enter your message here..." style="height: 5rem"
                        required v-model="answer.comment"></textarea>
                        <label for="comment">댓글</label>
                    </div>
                    <button class="btn btn-primary btn-xs" id="commentButton" type="button" @click="answerRegi">등록</button>
                    <table class="table" id="comment" v-if="comments.length">
                      <tbody>
                        <tr>
                          <th>작성자</th>
                          <th>작성일</th>
                          <th>댓글</th>
                          <th></th>
                        </tr>
                        <comment-list-row v-for="(comment, index) in comments"
                            :key="index"
                            v-bind="comment"/>
                      </tbody>
                    </table>
                    <div v-else>작성된 댓글이 없습니다.</div>
                </div>
            </div>
        </section>
</template>

<script>
import { mapState } from "vuex";
import http from "@/util/http-common";

import CommentListRow from "@/components/Board/CommentListRow";

const userStore = "userStore";

export default {
    name: "BoardView",
     data() {
        return {
            article: {},
            user: {},
            answer: {
              userid: "",
              comment: "",
              articleno: 0,
            },
            comments: [],
        };
    },
    components: {
      CommentListRow,
    },
    computed: {
      ...mapState(userStore, ["isLogin", "userInfo"]),
      message() {
        if (this.article.content)
          return this.article.content.split("\n").join("<br>");
        return "";
    },
  },
  created() {
    http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
    });
    http.get(`/memo/${this.$route.params.articleno}`).then(({ data }) => {
      console.log(data);
      this.comments = data;
    });
    if (this.userInfo) {
      this.user = this.userInfo;
      this.answer.userid = this.userInfo.userid;
    }
  },
   methods: {
    list() {
      this.$router.push({ name: "BoardList" });
    },
    moveUpdate() {
      this.$router.replace({
        name: "BoardUpdate",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    del() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        http.delete(`/board/${this.$route.params.articleno}`).then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "BoardList" });
      });
      }
    },
    answerRegi() {
      if (this.userInfo) {
        if(this.answer.comment) {
          http
        .post(`/memo`, {
          userid: this.answer.userid,
          comment: this.answer.comment,
          articleno: this.article.articleno,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
        this.$router.go();
        });
        } else {
          alert("댓글을 입력해주세요!");
        } 
      } else {
        alert("로그인 후 이용가능한 서비스입니다!");
      }
      
    }
  },
};
</script>

<style scoped>
table {
    border: 1px solid #bec6c9;
    text-align: left;
}

.head {
    background-color: #91c4cf;
    width: 120px;
    text-align: center;
}

#listButton {
  float: left;
  margin-bottom: 10px;
}

#updateButton {
  float: right;
  margin-bottom: 10px;
}

#delButton {
  float: right;
  margin-bottom: 10px;
  margin-right: 5px;
}

#commentButton {
  float: right;
  margin-bottom: 16px;
}
.content {
    height: 300px;
}

</style>
