<template>
  <div class="address-container">
    <!-- <input type="text" v-model="postcode" placeholder="우편번호" /> -->
    <h3 class="calculate-title">소요시간 계산</h3>
    <input class="address-text" type="text" v-model="address" placeholder="주소" readonly />
    <input class="address-search" type="button" @click="execDaumPostcode()" value="주소 검색" />
    <br />
    <button class="time-calculate" v-if="address != ''" @click="getLatLng">확인하기</button>
    <!-- <div v-if="routes.length != 0"></div>
      <p>{{ Address.x }}</p>
      <p>{{ Address.y }}</p>
    </div> -->
    <div class="time-calculate-result" v-if="Object.keys(route).length > 0">
      <p>
        <label>거리</label>
        <input class="result-distance" type="text" readonly :value="route.distance" />
      </p>
      <p>
        <label>시간</label>
        <input class="result-duration" type="text" readonly :value="route.duration" />
      </p>
      <p>
        <label>택시 요금</label>
        <input class="result-taxi" type="text" readonly :value="route.taxi" />
      </p>
      <p>
        <label>톨게이트 비용</label>
        <input class="result-toll" type="text" readonly :value="route.toll" />
      </p>
    </div>
    <!-- <div v-else-if="document.querySelector('.time-calculate').disabled">
      <h3>계산 중...</h3>
    </div> -->
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
      route: {},
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
      document.querySelector(".time-calculate").disabled = true;
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
              // this.routes = res.data;
              let distance = this.getDistance(res.data.routes[0].summary.distance);
              let duration = this.getDuration(res.data.routes[0].summary.duration);
              let taxi = this.getTaxi(res.data.routes[0].summary.fare.taxi);
              let toll = this.getToll(res.data.routes[0].summary.fare.toll);

              let data = {
                distance: distance,
                duration: duration,
                taxi: taxi,
                toll: toll,
              };
              this.route = data;
              console.log(data);
              // console.log(res.data.routes[0].summary.distance, "미터");
              // console.log(res.data.routes[0].summary.duration, "초");
              // console.log("택시 요금", res.data.routes[0].summary.fare.taxi, "원");
              // console.log("톨게이트 요금", res.data.routes[0].summary.fare.toll, "원");
            });
        }
      };

      geocoder.addressSearch(this.address, callback);
    },
    getDistance(dist) {
      return dist / 1000 + "km";
    },
    getDuration(time) {
      let h = Math.floor(time / 3600);
      let m = Math.ceil((time % 3600) / 60);
      let result = m + "분";
      if (h != 0) {
        result = h + "시간 " + result;
      }
      return result;
    },
    getTaxi(taxi) {
      return taxi + "원";
    },
    getToll(toll) {
      return toll + "원";
    },
  },
  watch: {
    address() {
      document.querySelector(".time-calculate").disabled = false;
    },
  },
};
</script>

<style>
* {
  font-family: Cafe24SsurroundAir;
}
.address-container {
  width: 80%;
  height: 20rem;
  margin: 0 auto;
}
.address-text {
  width: 20rem;
  height: 2rem;
  border-radius: 5px;
  border: none;
  font-size: 1rem;
  padding-left: 0.5rem;
}
/* 검색 버튼 */
.address-search {
  position: relative;
  border: none;
  display: inline-block;
  padding: 9px 12px;
  border-radius: 10px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  transition: 0.25s;
  background: linear-gradient(-45deg, #33ccff 0%, #ff99cc 100%);
  color: white;
  font-weight: bold;
  font-size: 0.9rem;
}

.address-search:hover {
  letter-spacing: 2px;
  transform: scale(1);
  cursor: pointer;
}

.address-text:focus {
  outline: none;
}
/* 소요시간 계산 제목 */
.calculate-title {
  padding-bottom: 0.5rem;
}
.time-calculate {
  margin-top: 1rem;
  position: relative;
  border: none;
  display: inline-block;
  padding: 9px 12px;
  border-radius: 10px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  transition: 0.25s;
  background: linear-gradient(-45deg, #33ccff 0%, #ff99cc 100%);
  color: white;
  font-weight: bold;
  font-size: 0.9rem;
}
.time-calculate:hover {
  letter-spacing: 2px;
  transform: scale(1);
  cursor: pointer;
}
.time-calculate-result > p {
  margin-top: 1rem;
}
.time-calculate-result > p > input {
  width: auto;
  height: 2rem;
  border-radius: 5px;
  border: none;
  font-size: 1rem;
  margin-left: 1rem;
  background: #ffffffb3;
  text-align: center;
}
</style>
