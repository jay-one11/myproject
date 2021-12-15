<template>
<!-- Contact-->
        <section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0" v-if="type == 'register'">글작성</h2>
                        <h2 class="mt-0" v-if="type == 'update'">글수정</h2>
                        <hr class="divider" />
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
                    <div class="col-lg-6">
                        <!-- * * * * * * * * * * * * * * *-->
                        <!-- * * SB Forms Contact Form * *-->
                        <!-- * * * * * * * * * * * * * * *-->
                        <!-- This form is pre-integrated with SB Forms.-->
                        <!-- To make this form functional, sign up at-->
                        <!-- https://startbootstrap.com/solution/contact-forms-->
                        <!-- to get an API token!-->
                        <form id="contactForm" @submit="onSubmit">
                            <!-- id input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="userid"
                                v-model="article.userid"
                                type="text"
                                required
                                readonly/>
                                <label for="id">ID</label>
                            </div>
                            <!-- password number input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" type="text"
                                id="subject"
                                v-model="article.subject"
                                required/>
                                <label for="subject">제목</label>
                            </div>
                            <!-- Message input-->
                            <div class="form-floating mb-3">
                                <textarea class="form-control" id="message" type="text" placeholder="Enter your message here..." style="height: 20rem"
                                v-model="article.content"
                                required></textarea>
                                <label for="message">내용</label>
                            </div>
                            <!-- Submit Button-->
                            <div class="d-grid"><button class="btn btn-primary btn-xl" id="submitButton" type="submit" v-if="type === 'register'">등록하기</button></div>
                            <div class="d-grid"><button class="btn btn-primary btn-xl" id="submitButton" type="submit" v-if="type === 'update'">수정하기</button></div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
</template>

<script>
import { mapState } from "vuex";
import http from "@/util/http-common";

const userStore = "userStore";

export default {
    name: "BoardWriteForm",
    data() {
        return {
            article: {
                articleno: 0,
                userid: "",
                subject: "",
                content: "",
            },
        }
    },
    props: {
    type: { type: String },
  },
  created() {
    if (this.type === "update") {
      http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
        this.article = data;
      });
    }
     if (this.userInfo) {
            this.article.userid = this.userInfo.userid;
    }
  },
  computed: {
        ...mapState(userStore, ["isLogin", "userInfo"]),
    },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
    !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    registArticle() {
      http
        .post(`/board`, {
          userid: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyArticle() {
      http
        .put(`/board`, {
          articleno: this.article.articleno,
          userid: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "BoardList" });
        });
    },
    moveList() {
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>

<style scoped>

</style>
