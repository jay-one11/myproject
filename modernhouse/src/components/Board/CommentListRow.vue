<template>
  <tr>
    <td>{{ userid }}</td>
    <td>{{ comment }}</td>
    <td>{{ memotime }}</td>
    <td><button class="btn btn-primary" id="delButton" type="button"
    v-if="user.userid === userid"
    @click="del">삭제</button></td>
  </tr>
</template>

<script>
import { mapState } from "vuex";
import http from "@/util/http-common";

const userStore = "userStore";

export default {
  name: "CommentListRow",
  data() {
    return {
      user: {},
    }
  },
  props: {
    userid: String,
    memono: Number,
    comment: String,
    memotime: String,
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
  created() {
    if (this.userInfo) {
      this.user = this.userInfo;
    }
  },
  methods: {
      del() {
          if (confirm("댓글을 삭제하시겠습니까?")) {
            http.delete(`/memo/${this.memono}`).then(({ data }) => {
                let msg = "삭제 처리시 문제가 발생했습니다.";
                if (data === "success") {
                    msg = "삭제가 완료되었습니다.";
                }
                alert(msg);
                this.$router.go();
            });
        }
      },
  },
};
</script>

<style>

#delButton {
  float: right;
}
</style>