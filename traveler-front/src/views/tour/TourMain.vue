<template>
  <section class="tour-list-container" v-cloak>
    <!-- <component-to-re-render key="router.state" /> -->
    <p class="tour-list-title">관광지 조회</p>
    <!-- 12 자연/휴양림, 14 문화/박물관, 15 축제, 25 여행 프로그램, 28 스포츠/레저, 32 숙박, 38 시장, 39 맛집 -->
    <ul class="category">
      <li>
        <button @click="setCode(0)" :disabled="checkSearchData.code == 0">
          <img src="https://cdn1.iconfinder.com/data/icons/unicons-line-vol-4/24/home-512.png" alt="" />
          <label>전체</label>
        </button>
      </li>
      <li @click="setCode(12)">
        <button @click="setCode(12)" :disabled="checkSearchData.code == 12">
          <img src="https://cdn2.iconfinder.com/data/icons/spring-flat-11/272/spring-tree-plant-garden-nature-wood-forest-1024.png" alt="" />
          <label>자연</label>
        </button>
      </li>
      <li @click="setCode(14)">
        <button @click="setCode(14)" :disabled="checkSearchData.code == 14">
          <img src="https://cdn-icons-png.flaticon.com/512/554/554099.png" alt="" />
          <label>문화</label>
        </button>
      </li>
      <li @click="setCode(15)">
        <button @click="setCode(15)" :disabled="checkSearchData.code == 15">
          <img src="https://cdn3.iconfinder.com/data/icons/new-year-2102/200/new_year-gr-02-1024.png" alt="" />
          <label>축제</label>
        </button>
      </li>
      <li @click="setCode(25)">
        <button @click="setCode(25)" :disabled="checkSearchData.code == 25">
          <img src="https://cdn-icons-png.flaticon.com/512/2831/2831972.png" alt="" />
          <label>여행</label>
        </button>
      </li>
      <li @click="setCode(28)">
        <button @click="setCode(28)" :disabled="checkSearchData.code == 28">
          <img src="https://cdn-icons-png.flaticon.com/512/1944/1944142.png" alt="" />
          <label>스포츠</label>
        </button>
      </li>
      <li @click="setCode(32)">
        <button @click="setCode(32)" :disabled="checkSearchData.code == 32">
          <img src="https://cdn-icons-png.flaticon.com/512/9296/9296428.png" alt="" />
          <label>숙박</label>
        </button>
      </li>
      <li @click="setCode(38)">
        <button @click="setCode(38)" :disabled="checkSearchData.code == 38">
          <img src="https://cdn-icons-png.flaticon.com/512/9420/9420511.png" alt="" />
          <label>시장</label>
        </button>
      </li>
      <li @click="setCode(39)">
        <button @click="setCode(39)" :disabled="checkSearchData.code == 39">
          <img src="https://cdn-icons-png.flaticon.com/512/281/281631.png" alt="" />
          <label>맛집</label>
        </button>
      </li>
    </ul>
    <br />
    <ul class="tour-list">
      <tour-item v-for="attraction in attractions" :key="attraction.contentId" :attraction="attraction"></tour-item>
    </ul>
    <Pagination :page="page" :totalCount="totalCount" @setPage="setPage" />
  </section>
</template>

<script>
import http from "@/util/http";
import TourItem from "@/views/tour/TourItem.vue";
import Pagination from "@/components/Pagination.vue";
export default {
  components: {
    TourItem,
    Pagination,
  },
  data() {
    return {
      attractions: [],
      totalCount: 0,
      page: 1,
      // keyword: "",
      // content: "",
      // code: "0",
    };
  },
  created() {
    // this.keyword = "title";
    // this.content = "";
    // this.code = 0;
    // console.log(this.content);
    // console.log("TourMain", this.$store.getters.searchData.keyword);
    this.getTotalPage();
    this.setAttractions();
  },
  methods: {
    // 페이지 전체 조회 개수 저장
    getTotalPage() {
      this.totalCount = 0;
      let searchData = this.$store.state.SearchData;
      console.log(searchData);
      http.get(`/tour/search.total?keyword=${searchData.keyword}&content=${searchData.content}&code=${searchData.code}`).then((res) => {
        console.log(res.data);
        this.totalCount = res.data;
      });
    },
    //관광지 변경
    setAttractions() {
      let searchData = this.$store.getters.searchData;
      console.log("setAttractions 호출");
      console.log(searchData);
      // console.log(`http://localhost/traveler/tour/search?keyword=${searchData.keyword}&content=${searchData.content}&code=${this.code}`);
      http.get(`/tour/search?keyword=${searchData.keyword}&content=${searchData.content}&code=${searchData.code}&page=${this.page}`).then((res) => {
        console.log(res.data);
        this.attractions = res.data;
      });
    },
    setCode(code) {
      this.page = 1;
      this.$store.commit("SET_CODE", { code });
    },
    setPage(value) {
      this.page = value;
    },
  },
  updated() {},
  computed: {
    checkSearchData() {
      let searchData = this.$store.getters.searchData;
      let data = {
        keyword: searchData.keyword,
        content: searchData.content,
        code: searchData.code,
      };
      return data;
    },
    // checkPage() {
    //   return this.$store.getters.searchData.page;
    // },
  },
  watch: {
    checkSearchData() {
      // this.keyword = this.checkSearchData.keyword;
      this.page = 1;
      this.getTotalPage();
      this.setAttractions();
    },
    page() {
      this.setAttractions();
    },
  },
};
</script>

<style scoped>
* {
  font-family: Cafe24SsurroundAir;
}
li,
ol,
ul {
  list-style: none;
}
.tour-list-title {
  font-size: 2rem;
  font-weight: bold;
  margin-left: 1.2rem;
}
.category {
  display: flex;
  margin: 1rem 5rem;
  justify-content: space-around;
}
.category > li > button:hover:enabled {
  transform: scale(1.1);
  cursor: pointer;
}
.category > li > button > img {
  width: 1.5rem;
}
.category > li > button {
  background-color: transparent;
  border: 1px solid rgb(97, 118, 161);
  border-radius: 10px;
  padding: 0.3rem;
}
.category > li > button > * {
  vertical-align: middle;
}
.category > li > button > label {
  font-weight: bold;
  font-size: 0.9rem;
}
.tour-list-title {
  padding: 4rem 0rem 0rem 5rem;
}
/* 관광지 조회 페이지의 뒤에 흰 배경 */
.tour-list-container {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  width: 60%;
  height: auto;
  margin: 0 auto;
  padding-bottom: 2rem;
}
.tour-list {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
</style>
