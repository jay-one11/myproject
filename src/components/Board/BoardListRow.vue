<template>
  <tr>
    <td>{{ articleno }}</td>
    <th>
      <router-link
        :to="{ name: 'BoardView', params: { articleno: articleno } }"
        >{{ subject }}</router-link>
    </th>
    <td>{{ hit }}</td>
    <td>{{ userid }}</td>
    <td>{{ regtime }}</td>
  </tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "BoardListRow",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style></style>
