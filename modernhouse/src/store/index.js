import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import houseStore from "@/store/modules/houseStore.js";

Vue.use(Vuex);

import userStore from "@/store/modules/userStore.js";

export default new Vuex.Store({
  modules: {
    userStore,
    houseStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
  state: {

  },
  getters: {

  },
  mutations: {
  },
  actions: {
 
  },
});
