<template>
  <div>
    <image-slider :attractions="attractions"></image-slider>
  </div>
</template>

<script>
import ImageSlider from "@/views/ImageSlider.vue";
import http from "@/util/http";
export default {
  data() {
    return {
      attractions: [],
    };
  },
  components: {
    ImageSlider,
  },
  created() {
    http.get("/tour/random").then((res) => {
      this.attractions = res.data;
    });
  },
};
</script>

<style>
/* body {
  background-image: url("@/assets/img/background.jpg");
  background-size: cover;
} */
</style>
