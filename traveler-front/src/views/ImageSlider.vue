<template>
  <div>
    <!-- 슬라이더 -->
    <section>
      <div class="content slider-content">
        <div class="slider-wrap">
          <!-- <h6>slider 2 <span>(center)</span></h6> -->
          <swiper class="swiper" :options="swiperOption2">
            <swiper-slide v-for="attraction in attractions" :key="attraction.contentId">
              <router-link class="attraction" :to="{ name: 'tour-detail', query: { content_id: attraction.contentId } }">
                <img class="attraction-image" :src="attraction.img" alt="" />
                <p class="attraction-text">{{ attraction.title }}</p>
              </router-link>
            </swiper-slide>
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
  props: {
    attractions: Array,
  },
  data() {
    return {
      swiperOption2: {
        centeredSlides: false,
        autoplay: {
          delay: 4000,
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
<style lang="scss" scoped>
.slider-content {
  padding: 2rem 1rem 5rem;
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
      opacity: 1;
    }
    .gallery-thumbs .swiper-slide-active {
      opacity: 1;
    }
  }
}
.swiper {
  width: 100%;
  height: 400px;
  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    font-weight: bold;
    // background: #fdde88;
    opacity: 1;
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
.attraction {
  position: relative;
  height: 300px;
  cursor: pointer;
}
.attraction > .attraction-text {
  position: absolute;
  bottom: 10%;
  width: 100%;
  // left: 50%;
  // text-align: center;
  color: white;
  font-size: 120%;
  text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;
  font-family: Cafe24Ssurround;
  // writing-mode: horizontal-tb;
}
.attraction-image {
  height: 300px;
  width: 200px;
  border-radius: 10px;
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
