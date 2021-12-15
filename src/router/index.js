import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

// user
import User from "@/views/User.vue";
import Login from "@/components/User/Login.vue";
import RegisterUser from "@/views/RegisterUser.vue";
import UpdateUser from "@/views/UpdateUser.vue";

// admin
import Admin from "@/views/Admin.vue";
import UserList from "@/components/User/UserList.vue";
import AddUser from "@/views/AddUser.vue";
import ModifyUser from "@/views/ModifyUser.vue";

// board
import Board from "@/views/Board.vue";
import BoardList from "@/components/Board/BoardList.vue";
import BoardView from "@/components/Board/BoardView.vue";
import BoardWrite from "@/components/Board/BoardWrite.vue";
import BoardUpdate from "@/components/Board/BoardUpdate.vue";

// house
import SearchDong from "@/components/house/SearchDong.vue";
import SearchApt from "@/components/house/SearchApt.vue";
import GuInfo from "@/components/house/GuInfo.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/admin",
    name: "Admin",
    component: Admin,
    children: [
      {
        path: "list",
        name: "UserList",
        component: UserList,
      },
      {
        path: "add",
        name: "AddUser",
        component: AddUser,
      },
      {
        path: "modify/:userid",
        name: "ModifyUser",
        component: ModifyUser,
      },
    ],
  },
  {
    path: "/user",
    name: "User",
    component: User,
    children: [
      {
        path: "Login",
        name: "Login",
        component: Login,
      },
      {
        path: "RegisterUser",
        name: "RegisterUser",
        component: RegisterUser,
      },
      {
        path: "UpdateUser",
        name: "UpdateUser",
        component: UpdateUser,
      },
    ],
  },
  {
    path: "/board",
    name: "Board",
    component: Board,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "BoardList",
        component: BoardList,
      },
      {
        path: "detail/:articleno",
        name: "BoardView",
        component: BoardView,
      },
      {
        path: "write",
        name: "BoardWrite",
        component: BoardWrite,
      },
      {
        path: "update/:articleno",
        name: "BoardUpdate",
        component: BoardUpdate,
      },
    ],
  },
  {
    path: "/searchDong",
    component: SearchDong,
    name: "SearchDong",
  },
  {
    path: "/searchApt",
    component: SearchApt,
    name: "SearchApt",
  },
  {
    path: "/info",
    component: GuInfo,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
