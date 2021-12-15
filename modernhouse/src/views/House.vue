<template>
  <div>
    <header class="userhead">
      <div class="container px-4 px-lg-5 h-100">
        <div class="row gx-4 gx-lg-5 h-100 align-items-center justify-content-center text-center">
        </div>
      </div>
    </header>
  <router-view></router-view>

    <div class="mt-3 text-center">
      <h3 class="mt-0">
        House Service
        <hr class="divider mt-3" />
      </h3>
      <table>
        <tr>
          <td>
            <house-search-bar></house-search-bar>
          </td>
        </tr>
        <tr>
          <td colspan="6" align="left">
            <house-list />
          </td>
          <td colspan="6">
            <house-detail />
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
import HouseSearchBar from "@/components/house/SearchBar.vue";
import HouseList from "@/components/house/list.vue";
import HouseDetail from "@/components/house/Maps.vue";


export default {
  name: "House",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
