<template>
  <div class="attraction">
    <h1 class="attraction-title">{{ attraction.title }}</h1>
    <h4>조회수 : {{ attraction.readcount }}</h4>
    <h2 class="attraction-addr">{{ attraction.addr }}</h2>
    <img class="attraction-image" :src="attraction.img" alt="관광지" style="height: 300px" />
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
    });
  },
};
</script>

<style></style>
