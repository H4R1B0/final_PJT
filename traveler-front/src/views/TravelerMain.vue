<template>
  <div>
    <!-- 배너 -->
    <section>
      <div class="main_text">
        <h1>STRESS SWITCH <span class="highlight">OFF</span></h1>
        <h1>HAPPY SWITCH <span class="highlight">ON</span></h1>
      </div>
    </section>
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
