<template>
  <div id="app">
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
* {
  margin: 0;
  padding: 0;
}
body {
  background-image: url("@/assets/img/background.jpg");
  background-size: cover;
}
</style>
