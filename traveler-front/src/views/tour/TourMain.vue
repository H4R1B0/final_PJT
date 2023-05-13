<template>
  <section class="tour-list-container" v-cloak>
    <!-- <component-to-re-render key="router.state" /> -->
    <h1 class="tour-list-title">관광지 조회</h1>
    <ul class="tour-list">
      <tour-item v-for="attraction in attractions" :key="attraction.contentId" :attraction="attraction"></tour-item>
    </ul>
    <Pagination />
  </section>
</template>

<script>
import axios from "axios";
// import { eventBus } from "@/main";
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
      keyword: "",
      content: "",
      code: "0",
    };
  },
  created() {
    // this.keyword = "title";
    // this.content = "";
    // this.code = 0;
    console.log(this.content);
    console.log(this.$store.getters.content);
    this.setAttractions();
  },
  methods: {
    // 페이지 전체 조회 개수 저장
    getTotalPage() {
      axios.get(`http://localhost/traveler/tour/total?keyword=${this.keyword}&content=${this.content}&code=${this.code}`).then((res) => {
        console.log(res.data);
        this.totalCount = res.data;
      });
    },
    // 관광지 변경
    setAttractions() {
      let searchData = this.$store.getters.searchData;
      console.log("setAttractions 호출");
      console.log(searchData);
      console.log(`http://localhost/traveler/tour/search?keyword=${searchData.keyword}&content=${searchData.content}&code=${this.code}`);
      axios.get(`http://localhost/traveler/tour/search?keyword=${searchData.keyword}&content=${searchData.content}&code=${this.code}`).then((res) => {
        console.log(res.data);
        this.attractions = res.data;
      });
    },
  },
  updated() {},
  computed: {
    checkSearchData() {
      return this.$store.getters.searchData;
    },
    checkKeyword() {
      return this.$store.getters.keyword;
    },
    checkContent() {
      return this.$store.getters.content;
    },
    checkCode() {
      return this.$store.getters.code;
    },
  },
  watch: {
    checkKeyword() {
      this.keyword = this.checkSearchData.keyword;
      this.setAttractions();
    },
    checkContent() {
      this.content = this.checkSearchData.content;
      this.setAttractions();
    },
    checkCode() {
      this.code = this.checkSearchData.code;
      this.setAttractions();
    },
  },
};
</script>

<style scoped>
li,
ol,
ul {
  list-style: none;
}
.tour-list-title {
  padding: 4rem 0rem 0rem 5rem;
}
/* 관광지 조회 페이지의 뒤에 흰 배경 */
.tour-list-container {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  width: 60%;
  height: 1000px;
  margin: 0 auto;
}
.tour-list {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
</style>
