<template>
  <div id="app" v-bind:style="{ transform: `rotate(${this.$store.state.deg}deg)` }">
    <the-header></the-header>
    <router-view :key="$route.fullPath" />
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader.vue";
export default {
  name: "App",
  components: {
    TheHeader,
  },
};
</script>

<style>
li {
  list-style: none;
}
* {
  margin: 0;
  padding: 0;
}
a:link {
  color: pink;
}
a:visited {
  color: black;
}
a:hover {
  color: rgb(185, 203, 235);
}
a:active {
  color: #b7b7f5;
}
body {
  background-image: url("@/assets/img/background.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  /* background-size: 1920px; */
}
/* test */
#app {
  transition: transform 2s ease-in-out;
}
</style>
