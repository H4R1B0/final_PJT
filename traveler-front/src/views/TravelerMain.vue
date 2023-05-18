<template>
  <div>
    <!-- 배너 -->
    <section>
      <div class="main-banner-text">
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
/* 배너 */
.main-banner-text {
  font-family: Cafe24Ssurround;
  position: relative;
  text-align: center;
  color: white;
  font-size: 2rem;
  margin-top: 2rem;
}
/* 베너 하이라이트 */
.highlight {
  color: yellow;
}
</style>
