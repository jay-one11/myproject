<template>
<!-- Contact-->
        <section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0" v-if="type == 'add'">회원등록</h2>
                        <h2 class="mt-0" v-if="type == 'register'">회원가입</h2>
                        <h2 class="mt-0" v-if="type == 'update'">마이페이지</h2>
                        <h2 class="mt-0" v-if="type == 'modify'">회원수정</h2>
                        <hr class="divider" />
                        <p class="text-muted mb-5" v-if="type == 'register'">회원가입하고 다양한 기능을 활용하세요!</p>
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
                            <!-- Name input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" type="text" 
                                v-model="user.username"
                                placeholder="Enter Name"
                                required />
                                <label for="name">Name</label>
                            </div>
                            <!-- ID input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="id" type="text" 
                                v-model="user.userid"
                                placeholder="Enter ID"
                                v-if="type == 'register' || type == 'add'"
                                required 
                                />
                                <label for="id">ID</label>
                            </div>

                            <div class="form-floating mb-3">
                                <input class="form-control" id="id" type="text" 
                                v-model="user.userid"
                                placeholder="Enter ID"
                                required 
                                v-if="type == 'update' || type === 'modify'"
                                readonly
                                />
                                <label for="id">ID</label>
                            </div>
                             <!-- password number input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="pwd" type="password" 
                                v-model="user.userpwd"
                                placeholder="Enter Password"
                                required />
                                <label for="pwd">Password</label>
                            </div>
                            <!-- Email address input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="email" type="email" placeholder="name@example.com" 
                                v-model="user.email"
                                required />
                                <label for="email">Email address</label>
                            </div>
                            <!-- Message input-->
                            <!-- <div class="form-floating mb-3">
                                <div class="row">
                                    <div class="col">
                                        <label for="email">시/도</label>
                                        <select class="form-control"></select>
                                    </div>
                                    <div class="col">
                                        <label for="email">구/군</label>
                                        <select class="form-control"></select>
                                    </div>
                                </div>
                            </div> -->
                            <!-- Submit success message-->
                            <!---->
                            <!-- This is what your users will see when the form-->
                            <!-- has successfully submitted-->
                            <div class="d-none" id="submitSuccessMessage">
                                <div class="text-center mb-3">
                                    <div class="fw-bolder">Form submission successful!</div>
                                    To activate this form, sign up at
                                    <br />
                                    <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
                                </div>
                            </div>
                            <!-- Submit error message-->
                            <!---->
                            <!-- This is what your users will see when there is-->
                            <!-- an error submitting the form-->
                            <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error sending message!</div></div>
                            <!-- Submit Button-->
                            <div class="d-grid"><button class="btn btn-primary btn-xl" id="registerButton" type="submit" v-if="type === 'register'">회원가입</button></div>
                            <div class="d-grid"><button class="btn btn-primary btn-xl" id="addButton" type="submit" v-if="type === 'add'">추가</button></div>
                            
                            <div class="d-grid"><button class="btn btn-primary btn-xl" id="updateButton" type="submit" v-if="type === 'update' || type === 'modify'">수정하기</button><br/>
                            <button class="btn btn-primary btn-xl" id="delButton" type="button" v-if="type == 'update'" @click="del">탈퇴하기</button></div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import http from "@/util/http-common";

const userStore = "userStore";

export default {
    name: "writeform",
    props: {
        type: { type: String },
    },
     data() {
        return {
            user: {},
        };
    },
    created() {
        if(this.type != "add" && this.type != "modify") {
            if (this.userInfo) {
            this.user = this.userInfo;
            }
        }
        if (this.type === "modify") {
            http.get(`/user/${this.$route.params.userid}`).then(({ data }) => {
            this.user = data;
      });
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
            !this.user.username &&
                ((msg = "이름을 입력해주세요"),
                (err = false),
                this.$refs.username.focus());
            err &&
                !this.user.userid &&
                ((msg = "아이디를 입력해주세요"),
                (err = false),
                this.$refs.userid.focus());
            err &&
                !this.user.userpwd &&
                ((msg = "비밀번호를 입력해주세요"),
                (err = false),
                this.$refs.userpwd.focus());
            err &&
                !this.user.email &&
                ((msg = "이메일을 입력해주세요"),
                (err = false),
                this.$refs.email.focus());

            if (!err) alert(msg);
            else {
                if (this.type === "update") {
                    this.update();
                } else if(this.type === "register") {
                    this.register();
                } else if(this.type === "add") {
                    this.add();
                } else if(this.type === "modify") {
                    this.modify();
                }
            }
        },
        register() {
            http.post("/user/register", this.user)
            .then(() => {
                alert("가입을 축하합니다.");
                this.$router.push("/");
            })
            .catch(() => {
                alert("중복된 아이디입니다!");
            });
            
        },
        update() {
            http.put("/user", this.user)
                .then(() => {
                    alert("정보가 수정되었습니다.");
                    this.$router.push("/");
                })
                .catch(() => {
                    alert("에러발생!");
                });
        },
         ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
        del() {
            if (confirm("정말로 탈퇴하시겠습니까?")) {
                http.delete("/user/" + this.user.userid, this.user)
                    .then(() => {
                        alert("탈퇴 완료");
                        this.SET_IS_LOGIN(false);
                        this.SET_USER_INFO(null);
                        sessionStorage.removeItem("access-token");
                        if (this.$route.path != "/") this.$router.push({ name: "Home" });
                        // 세션 처리해야함
                    })
                    .catch(() => {
                        alert("에러발생!");
                    });
            }
        },
        add() {
            http.post("/user/register", this.user)
            .then(() => {
                alert("회원을 추가 등록하였습니다.");
                this.$router.push({ name: "UserList" });
            })
            .catch(() => {
                alert("중복된 아이디입니다!");
            });
        },
        modify() {
            http.put("/user", this.user)
                .then(() => {
                    alert("정보가 수정되었습니다.");
                    this.$router.push({ name: "UserList" });
                })
                .catch(() => {
                    alert("에러발생!");
                });
            },
    },
};
</script>

<style scoped>

</style>
