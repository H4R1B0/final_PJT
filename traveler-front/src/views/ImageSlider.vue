<template>
  <div>
    <!-- 배너 -->
    <section>
      <div class="main_text">
        <h1>STRESS SWITCH <span class="highlight">OFF</span></h1>
        <h1>HAPPY SWITCH <span class="highlight">ON</span></h1>
      </div>
    </section>
    <!-- 슬라이더 -->
    <section>
      <div class="content slider-content">
        <div class="slider-wrap">
          <!-- <h6>slider 2 <span>(center)</span></h6> -->
          <swiper class="swiper" :options="swiperOption2">
            <swiper-slide>Slide 1</swiper-slide>
            <swiper-slide>Slide 2</swiper-slide>
            <swiper-slide>Slide 3</swiper-slide>
            <swiper-slide>Slide 4</swiper-slide>
            <swiper-slide>Slide 5</swiper-slide>
            <swiper-slide>Slide 6</swiper-slide>
            <swiper-slide>Slide 7</swiper-slide>
            <swiper-slide>Slide 8</swiper-slide>
            <swiper-slide>Slide 9</swiper-slide>
            <div class="swiper-pagination" slot="pagination"></div>
            <div class="swiper-button-prev" slot="button-prev"></div>
            <div class="swiper-button-next" slot="button-next"></div>
          </swiper>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import "swiper/css/swiper.css";

export default {
  name: "slider",
  components: {
    Swiper,
    SwiperSlide,
  },
  props: {},
  data() {
    return {
      swiperOption2: {
        centeredSlides: true,
        autoplay: {
          delay: 3000,
        },
        slidesPerView: 3,
        spaceBetween: 30,
        loop: true,
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      },

      startProgress: false,
    };
  },
  computed: {},
  methods: {
    init() {
      this.startProgress = true;
    },
    stopAutoplay() {
      try {
        this.swiper.autoplay.stop();
        this.startProgress = false;
      } catch (err) {
        //console.error(err)
      }
    },
    startAutoplay() {
      try {
        this.swiper.autoplay.start();
        this.startProgress = true;
      } catch (err) {
        //console.error(err)
      }
    },
  },
  watch: {},
};
</script>
<style lang="scss">
.slider-content {
  padding-bottom: 170px;
}
.slider-wrap {
  h6 {
    font-size: 24px;
    margin-bottom: 20px;
  }
  & + .slider-wrap {
    margin-top: 40px;
  }
  &.thumb-example {
    height: 480px;
    .swiper-slide {
      display: flex;
      justify-content: center;
      align-items: center;
      text-align: center;
      font-weight: bold;
      background-size: cover;
      background-position: center;
      &.slide-1 {
        background-image: url("@/assets/img/banner_tour_img/tour0001.png");
      }
      &.slide-2 {
        background-image: url("@/assets/img/banner_tour_img/tour0002.png");
      }
      &.slide-3 {
        background-image: url("@/assets/img/banner_tour_img/tour0001.png");
      }
      &.slide-4 {
        background-image: url("@/assets/img/banner_tour_img/tour0002.png");
      }
      &.slide-5 {
        background-image: url("@/assets/img/banner_tour_img/tour0001.png");
      }
      &.slide-6 {
        background-image: url("@/assets/img/banner_tour_img/tour0002.png");
      }
    }
    .gallery-top {
      height: 80%;
      width: 100%;
    }
    .gallery-thumbs {
      height: 20%;
      margin-top: 20px;
    }
    .gallery-thumbs .swiper-slide {
      width: 25%;
      height: 100%;
      opacity: 0.4;
    }
    .gallery-thumbs .swiper-slide-active {
      opacity: 1;
    }
  }
}
.swiper {
  width: 100%;
  height: 300px;
  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    font-weight: bold;
    background: #fdde88;
    opacity: 0.7;
    img {
      width: 100%;
    }
    &.swiper-slide-active {
      opacity: 1;
    }
  }
  .swiper-pagination {
    bottom: 20px;
    &.num {
      display: inline-block;
      width: auto;
      left: 20px;
      padding: 7px 15px;
      background: #fff;
      border-radius: 20px;
      color: #666;
      .swiper-pagination-current {
        font-weight: 900;
        color: #ff7600;
      }
      .swiper-pagination-total {
        color: #666;
      }
    }
  }
  .swiper-progress {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    z-index: 1;
    background: #333;
    .bar {
      width: 100%;
      height: 4px;
      transform: translateX(-100%);
      background: #ff7600;
    }
    &.start {
      .bar {
        animation-duration: 5s;
        animation-iteration-count: infinite;
        animation-name: progress;
      }
    }
  }
}

@keyframes progress {
  from {
    transform: translateX(-100%);
  }
  to {
    transform: translateX(0);
  }
}
</style>
