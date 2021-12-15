<template>
     <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3 shadow" id="mainNav">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand"><router-link :to="{ name: 'Home' }" class="nav-link">HAPPY HOUSE</router-link></a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto my-2 my-lg-0">
                        <li class="nav-item"><router-link :to="{ name: 'Home' }" class="nav-link">HOME</router-link></li>
                        <li class="nav-item"><router-link :to="{ name: 'House' }" class="nav-link">부동산 정보</router-link></li>
                        <li class="nav-item"><router-link :to="{ name: 'Board' }" class="nav-link">게시판</router-link></li>

                        <li class="nav-item" v-if="userInfo && userInfo.userid === 'admin'"><router-link :to="{ name: 'UserList' }" class="nav-link">회원관리</router-link></li>
                        <li class="nav-item" v-if="userInfo"><router-link :to="{ name: 'UpdateUser' }" class="nav-link">마이 페이지</router-link></li>
                        <li class="nav-item" v-if="userInfo" @click.prevent="onClickLogout"><span class="nav-link">로그아웃</span></li>
                        <li class="nav-item" v-if="userInfo"><span class="nav-link" id="username"><i class="bi-person-circle"></i>&nbsp; {{ userInfo.username }}님</span></li>

                        <li class="nav-item" v-if="userInfo == null"><router-link :to="{ name: 'RegisterUser' }" class="nav-link">회원가입</router-link></li>
                        <li class="nav-item" v-if="userInfo == null"><router-link :to="{ name: 'Login' }" class="nav-link">로그인</router-link></li>
                    </ul>
                </div>
            </div>
        </nav>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
    name: "NavBar",
    computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    },
    methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style>
#username {
    color: rgb(28, 31, 56);
}
</style>
