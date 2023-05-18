<template>
  <section class="update-container">안뇽</section>
</template>

<script>
import http from "@/util/http";
export default {
  data() {
    return {
      no: Number,
      infoArticle: Object,
    };
  },
  created() {
    this.no = this.$route.query.no;
    http.get(`/board/info/${this.no}`).then((response) => {
      console.log(response);
    });
  },
};
</script>

<style></style>
