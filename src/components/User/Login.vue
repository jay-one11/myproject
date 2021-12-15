<template>
<!-- Contact-->
        <section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">Login</h2>
                        <hr class="divider" />
                        <p class="text-muted mb-5">로그인을 하고 다양한 기능을 활용하세요!</p>
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
                                 v-model="user.userid"
                                required
                                placeholder="아이디 입력...."
                                @keyup.enter="confirm" />
                                <label for="id">ID</label>
                            </div>
                            <!-- password number input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" type="password"
                                id="userpwd"
                                v-model="user.userpwd"
                                required
                                placeholder="비밀번호 입력...."
                                @keyup.enter="confirm"/>
                                <label for="pwd">Password</label>
                            </div>
                            <!-- Submit Button-->
                            <div class="d-grid"><button class="btn btn-primary btn-xl" id="submitButton" type="submit">Login</button></div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
    name: "Login",
    data() {
        return {
            user : {
                userid: null,
                userpwd: null,
            },
        };
    },
     computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    onSubmit(event) {
        event.preventDefault();

        let err = true;
        let msg = "";
        !this.user.userid &&
            ((msg = "아이디를 입력해주세요"),
            (err = false),
            this.$refs.userid.focus());
        err &&
            !this.user.userpwd &&
            ((msg = "비밀번호를 입력해주세요"),
            (err = false),
            this.$refs.userpwd.focus());

        if (!err) alert(msg);
        else this.confirm();
    },
    async confirm() {
        await this.userConfirm(this.user);
        let token = sessionStorage.getItem("access-token");
        if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
        } else {
            alert("존재하지 않는 아이디/비밀번호입니다!");
        }
          
    },
  },
};
</script>

<style scoped>

</style>
