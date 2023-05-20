<template>
  <div>
    좋아요 페이지
    <table>
      <tr>
        <th>순번</th>
        <th>제목</th>
      </tr>
      <tr v-for="(attraction, index) in attractions" :key="attraction.contentId">
        <td>{{ index + 1 }}</td>
        <td>{{ attraction.title }}</td>
      </tr>
    </table>
    <like-pagination :page="page" :totalCount="totalCount" @setPage="setPage"></like-pagination>
  </div>
</template>

<script>
import http from "@/util/http";
import LikePagination from "./LikePagination.vue";
export default {
  data() {
    return {
      page: 1,
      totalCount: 0,
      attractions: [],
    };
  },
  components: {
    LikePagination,
  },
  created() {
    //좋아요한 관광지 총 개수
    http
      .get(`/member/interest.total?member_id=${this.$store.state.memberInfo.member_id}`)
      .then((res) => {
        this.totalCount = res.data;
        console.log(this.totalCount);
      })
      .catch(() => {
        console.log("오류 발생");
      });
    this.setAttractions();
  },
  methods: {
    setPage(value) {
      // console.log("자식에게 받은 page:", value);
      this.page = value;
    },
    setAttractions() {
      //좋아요한 관광지 불러오기
      http
        .get(`/member/interest?member_id=${this.$store.state.memberInfo.member_id}&page=${this.page}`)
        .then((res) => {
          this.attractions = res.data;
          console.log(this.attractions);
        })
        .catch(() => {
          console.log("오류 발생");
        });
    },
  },
  watch: {
    page() {
      this.setAttractions();
    },
  },
};
</script>

<style></style>
