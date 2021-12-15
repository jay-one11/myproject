<template>
        <section class="page-section" id="contact">
            <div class="container">
                <div class="row">
                    
                  <h2 class="text-center">회원 목록</h2>
                  <hr class="divider" />
                
                </div>
                <div class="row">
                  <div class="col">
                    <div id="searchDiv">
                      <select id="searchSelect" v-model="content.type">
                        <option value="username">이름</option>
                        <option value="userid">id</option>
                      </select>
                      <input id="search" type="text" class="mb-3"
                       v-model="content.word"/>
                      <button class="btn btn-primary btn-xs" id="searchButton" type="button" 
                      @click="search">검색</button>
                    </div>
                  </div>
                  <div class="col">
                     <button class="btn btn-primary btn-xs" id="writeButton" type="button"  @click="addUser">추가</button>
                  </div>
                </div>
                <!-- <modal-view v-if="isModalViewed" @close-modal="isModalViewed = false">
                        <p>첫 모달</p>
                </modal-view> -->
                <div class="text-center" v-if="users.length">
                    <table class="table">
                        <thead>
                            <tr>
                            <th scope="col">no</th>   
                            <th scope="col">id</th>
                            <th scope="col">이름</th>
                            <th scope="col">비밀번호</th>
                            <th scope="col">이메일</th>
                            <th scope="col">가입일</th>
                            <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
                            <user-list-row
                            v-for="(user, index) in users"
                            :key="index"
                            v-bind="user"
                            :index="index"
                            />
                        </tbody>
                    </table>
                </div>
                <div v-else>회원이 없습니다.</div>
            </div>
        </section>
</template>

<script>
import http from "@/util/http-common";
import UserListRow from "@/components/User/UserListRow";
//import ModalView from "@/components/layout/ModalView";

export default {
    name: "UserList",
    components: {
    UserListRow,
  },
  data() {
    return {
      content : {
        word : "",
        type : "",
      },
      users: [],
    };
  },
  created() {
    http.get(`/user`).then(({ data }) => {
      this.users = data;
      //console.log(data);
    });
  },
  methods: {
    search() {
        http.get(`/user?key=${this.content.type}&word=${this.content.word}`).then((response)=>{
          this.users = response.data;
          //console.log("검색 완료");
        })
    },
    addUser() {
      this.$router.push({ name: "AddUser" });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
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
</style>
