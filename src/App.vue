<template>
  <div id="app">
    <nav-bar />
    <router-view />
    <Footer />
  </div>
</template>

<script>
import NavBar from "./components/layout/NavBar.vue";
import Footer from "./components/layout/Footer.vue";

export default {
  name: "App",
  components: {
    NavBar,
    Footer,
  },
};
</script>

<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);

#app {
  font-family: 'Jeju Gothic', sans-serif ;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
