<template>
  <tr>
    <td>{{ index+1 }}</td>
    <td>{{ userid }}</td>
    <td>{{ username }}</td>
    <td>{{ userpwd }}</td>
    <td>{{ email }}</td>
    <td>{{ joindate }}</td>
    <td><button class="btn btn-primary" id="updateButton" type="button" @click="moveModify">수정</button>
    <button class="btn btn-primary" id="delButton" type="button" @click="del">삭제</button></td>
  </tr>
</template>

<script>
// import moment from "moment";
import http from "@/util/http-common";

export default {
  name: "UserListRow",
  props: {
    index: Number,
    userid: String,
    username: String,
    userpwd: String,
    email: String,
    joindate: String,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
  methods: {
     del() {
          if (confirm("삭제하시겠습니까?")) {
            http.delete(`/user/${this.userid}`).then(({ data }) => {
                //let msg = "삭제 처리시 문제가 발생했습니다.";
                let msg = "삭제가 완료되었습니다.";
                console.log(data);
                if (data === "success") {
                    msg = "삭제가 완료되었습니다.";
                }
                alert(msg);
                this.$router.go();
            });
        }
      },
      moveModify() {
        this.$router.push({ name: 'ModifyUser', params: { userid: this.userid } });
      }
  },
};
</script>

<style>
#updateButton {
  float: right;
}

#delButton {
  float: right;
  margin-right: 5px;
}
</style>
