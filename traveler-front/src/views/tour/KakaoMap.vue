<template>
  <div class="map-container">
    <h3>위치</h3>
    <div class="kakao-map"></div>
  </div>
</template>

<script>
import { KAKAO_API_KEY } from "@/util/api";
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
    };
  },
  props: {
    latitude: Number,
    longitude: Number,
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      // 카카오 객체가 있고, 카카오 맵그릴 준비가 되어 있다면 맵 실행
      this.loadMap();
    } else {
      // 없다면 카카오 스크립트 추가 후 맵 실행
      this.loadScript();
    }
  },
  methods: {
    loadScript() {
      const script = document.createElement("script");
      // &autoload=false api를 로드한 후 맵을 그리는 함수가 실행되도록 구현
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${KAKAO_API_KEY}&autoload=false&libraries=services`;
      script.onload = () => window.kakao.maps.load(this.loadMap); // 스크립트 로드가 끝나면 지도를 실행될 준비가 되어 있다면 지도가 실행되도록 구현

      document.head.appendChild(script); // html>head 안에 스크립트 소스를 추가
    },
    loadMap() {
      const container = document.querySelector(".kakao-map"); // 지도를 담을 DOM 영역
      const options = {
        // 지도를 생성할 때 필요한 기본 옵션
        center: new window.kakao.maps.LatLng(this.latitude, this.longitude), // 지도의 중심좌표
        level: 3, // 지도의 레벨(확대, 축소 정도)
      };

      this.map = new window.kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

      // 마커가 표시될 위치입니다
      var markerPosition = new window.kakao.maps.LatLng(this.latitude, this.longitude);

      // 마커를 생성합니다
      var marker = new window.kakao.maps.Marker({
        position: markerPosition,
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);
    },
  },
};
</script>

<style scoped>
/* 맵 출력 */
.map-container {
  width: 100%;
  height: 25rem;
  padding-top: 5%;
}
.map-container > h3 {
  margin-left: 10%;
  margin-bottom: 1%;
}
.kakao-map {
  width: 80%;
  height: 80%;
  margin: 0 10%;
  border-radius: 10px;
}
</style>
