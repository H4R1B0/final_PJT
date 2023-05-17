<template>
  <div>
    <!-- <input type="text" v-model="postcode" placeholder="우편번호" /> -->
    <input type="button" @click="execDaumPostcode()" value="우편번호 찾기" />
    <br />
    <input type="text" v-model="address" placeholder="주소" readonly />
    <br />
    <button v-if="address != ''" @click="getLatLng">소요시간 검색</button>
    <div v-if="Address">
      <p>{{ Address.x }}</p>
      <p>{{ Address.y }}</p>
    </div>
    <!-- <input type="text" id="detailAddress" placeholder="상세주소" /> -->
    <!-- <input type="text" id="extraAddress" placeholder="참고항목" /> -->
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      postcode: "",
      address: "",
      extraAddress: "",
    };
  },
  props: {
    attraction: Object,
  },
  computed: {
    Address() {
      return this.$store.state.Address;
    },
  },
  methods: {
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress += this.extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          // 우편번호를 입력한다.
          this.postcode = data.zonecode;
        },
      }).open();
    },
    getLatLng() {
      let geocoder = new window.kakao.maps.services.Geocoder();

      let callback = (result, status) => {
        if (status === window.kakao.maps.services.Status.OK) {
          console.log(result[0].address);
          console.log("목적지:", this.attraction);
          let address = result[0].address;
          console.log("출발지:", address);
          axios
            .get(
              `https://apis-navi.kakaomobility.com/v1/directions?priority=RECOMMEND&car_type=1&car_fuel=GASOLINE&origin=${address.x},${address.y}&destination=${this.attraction.longitude},${this.attraction.latitude}`,
              {
                headers: {
                  Authorization: "KakaoAK 0976d93c1546886fb29b1656be18cfcb",
                },
              }
            )
            .then((res) => {
              console.log(res.data);
              console.log(res.data.routes[0].summary.distance, "미터");
              console.log(res.data.routes[0].summary.duration, "초");
              console.log("택시 요금", res.data.routes[0].summary.fare.taxi, "원");
              console.log("톨게이트 요금", res.data.routes[0].summary.fare.toll, "원");
            });
        }
      };

      geocoder.addressSearch(this.address, callback);
    },
  },
};
</script>

<style></style>
