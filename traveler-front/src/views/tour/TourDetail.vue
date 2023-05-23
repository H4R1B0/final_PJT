<template>
  <div class="attraction">
    <div class="attraction-title">
      <img v-if="Object.keys(attraction).length > 0" class="icon-image" :src="iconSrc" alt="아이콘" />
      <h1>{{ attraction.title }}</h1>
    </div>
    <h4 class="attraction-readcount">조회수 : {{ attraction.readcount }}</h4>
    <div class="attraction-image">
      <img :src="attraction.img == '' ? 'https://cdn1.iconfinder.com/data/icons/carbon-design-system-vol-6/32/no-image-512.png' : attraction.img" alt="관광지" />
      <h3 class="attraction-addr">{{ attraction.addr }}</h3>
    </div>
    <KakaoMap v-if="Object.keys(attraction).length > 0" :latitude="attraction.latitude" :longitude="attraction.longitude" />
    <Address :attraction="attraction" />
  </div>
</template>

<script>
import http from "@/util/http";
import KakaoMap from "@/views/tour/KakaoMap.vue";
import Address from "@/components/Address.vue";
export default {
  components: {
    KakaoMap,
    Address,
  },
  data() {
    return {
      attraction: {},
    };
  },
  created() {
    console.log(this.$route.query.content_id);
    let content_id = this.$route.query.content_id;
    http.get(`/tour/${content_id}`).then((res) => {
      this.attraction = res.data;
      // console.log(this.attraction);
    });
    // console.log(this._data.atttraction);
  },
  computed: {
    iconSrc() {
      if (this.attraction.contentTypeId == 12) {
        return "https://cdn2.iconfinder.com/data/icons/spring-flat-11/272/spring-tree-plant-garden-nature-wood-forest-1024.png";
      }
      if (this.attraction.contentTypeId == 14) {
        return "https://cdn-icons-png.flaticon.com/512/554/554099.png";
      }
      if (this.attraction.contentTypeId == 15) {
        return "https://cdn3.iconfinder.com/data/icons/new-year-2102/200/new_year-gr-02-1024.png";
      }
      if (this.attraction.contentTypeId == 25) {
        return "https://cdn-icons-png.flaticon.com/512/2831/2831972.png";
      }
      if (this.attraction.contentTypeId == 28) {
        return "https://cdn-icons-png.flaticon.com/512/1944/1944142.png";
      }
      if (this.attraction.contentTypeId == 32) {
        return "https://cdn-icons-png.flaticon.com/512/9296/9296428.png";
      }
      if (this.attraction.contentTypeId == 38) {
        return "https://cdn-icons-png.flaticon.com/512/9420/9420511.png";
      }
      if (this.attraction.contentTypeId == 39) {
        return "https://cdn-icons-png.flaticon.com/512/281/281631.png";
      }
      return "-1";
    },
  },
};
</script>

<style scoped>
* {
  font-family: Cafe24SsurroundAir;
}
/* 컨테이너 */
.attraction {
  height: auto;
  width: 60%;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  margin: 0 auto;
  padding-bottom: 2rem;
}
/* 제목 */
.attraction-title {
  display: flex;
  padding-top: 3rem;
  justify-content: center;
}
.attraction-title > .icon-image {
  width: 34px;
  margin-left: 2%;
}
/* 조회수 */
.attraction-readcount {
  float: right;
  padding-top: 2rem;
  padding-right: 10%;
}
/* 사진 */
.attraction-image {
  display: flex;
  width: 100%;
  flex-direction: column;
  padding-top: 5%;
}
.attraction-image > img {
  width: 80%;
  margin: 0 auto;
  border-radius: 10px;
}
.attraction-image > h3 {
  text-align: center;
}
.attraction-addr {
  padding-top: 1rem;
}
</style>
