<template>
        <section class="page-section" id="contact">
            <div class="container">
                <div class="row">
                    
                  <h2 class="text-center">게시판</h2>
                  <hr class="divider" />
                
                </div>
                <div class="row">
                  <div class="col">
                    <div id="searchDiv">
                      <select id="searchSelect" v-model="content.type">
                        <option value="subject">제목</option>
                        <option value="userid">작성자</option>
                      </select>
                      <input id="search" type="text" class="mb-3"
                       v-model="content.word"/>
                      <button class="btn btn-primary btn-xs" id="searchButton" type="button" 
                      @click="search">검색</button>
                    </div>
                  </div>
                  <div class="col">
                     <button class="btn btn-primary btn-xs" id="writeButton" type="button"  @click="moveWrite">글쓰기</button>
                  </div>
                </div>
                <div class="text-center" v-if="articles.length">
                    <table class="table">
                        <thead>
                            <tr>
                            <th scope="col">글번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">조회수</th>
                            <th scope="col">작성자</th>
                            <th scope="col">작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
                            <board-list-row
                            v-for="(article, index) in articles"
                            :key="index"
                            v-bind="article"
                            />
                        </tbody>
                    </table>
                </div>
                <div v-else>글이 없습니다.</div>
                <div class="page">
                  <ul class="pagination">
                        <li class="page-item"><a class="page-link" @click="movepg(1)">최신</a></li>
                        <li class="page-item"><a class="page-link" @click="movepg(pages.currentPage-1)">이전</a></li>
                        <li class="page-item" v-for="index in pages.totalPageCount" :key="index"><a class="page-link">{{ index }}</a></li> 
                        <li class="page-item"><a class="page-link" @click="movepg(pages.currentPage+1)">다음</a></li>
                        <li class="page-item"><a class="page-link" @click="movepg(pages.totalPageCount)">마지막</a></li>
                  </ul>
                </div>
            </div>
        </section>
</template>

<script>
import { mapState } from "vuex";
import http from "@/util/http-common";
import BoardListRow from "@/components/Board/BoardListRow";

const userStore = "userStore";

export default {
    name: "BoardList",
    components: {
    BoardListRow,
  },
  data() {
    return {
      content : {
        word : "",
        type : "",
      },
      pages: {},
      startPage : 0,
      endPage : 0,
      pg : 0,
      articles: [],
    };
  },
  created() {
    http.get(`/board`).then(({ data }) => {
      this.articles = data;
    });
    http.get(`/board/page`).then(({ data }) => {
      console.log(data);
      this.pages = data;
      this.startPage = (this.pages.currentPage-1)/this.naviSize * this.pages.naviSize+1;
      this.endPage = this.startPage + this.pages.naviSize-1;
      if(this.pages.totalPageCount < this.endPage) this.endPage = this.pages.totalPageCount;
    });
  },
  computed: {
      ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    moveWrite() {
        if (this.userInfo) {
          this.$router.push({ name: "BoardWrite" });
        } else {
          alert("로그인 후 이용가능한 서비스입니다.")
        }
    },
    search() {
        http.get(`/board?key=${this.content.type}&word=${this.content.word}`).then((response)=>{
          this.articles = response.data;
          //console.log("검색 완료");
        })
    },
    movepg(val) {
      console.log(val);
      this.pg = val;
      http.get(`/board?pg=${this.pg}&spp=10`).then(({ data }) => {
      this.articles = data;
      });
    }
  },
};
</script>

<style scoped>

table > thead{
background-color: #91c4cf;
}

#searchButton {
  margin-bottom: 6px;
}

#writeButton {
  float: right;
  margin-bottom: 6px;
}

#search, #searchSelect {
   height: 35px;
   margin-right: 5px;
}

#searchDiv {
  float: left;
}

ul {
  width: 300px;
  margin-left: auto;
  margin-right: auto;
}

</style>
