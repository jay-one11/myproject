import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "@/assets/css/styles.css";
import vmodal from 'vue-js-modal'

Vue.use(vmodal)

import userStore from "./store/modules/userStore";

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  async beforeCreate() {
    let token = sessionStorage.getItem("access-token");
    if (userStore.state.userInfo == null && token) {
      await userStore.dispatch("getUserInfo", token);
    }
  },
  render: (h) => h(App),
}).$mount("#app");
