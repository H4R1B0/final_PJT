<template>
  <div>
    <!-- 좋아요 페이지 -->
    <!-- <label v-if="totalCount == 0"> 좋아요한 관광지가 없습니다. </label> -->
    <table class="like-page" align="center">
      <tr>
        <th></th>
        <th>순번</th>
        <th>제목</th>
      </tr>
      <like-page-item v-for="(attraction, index) in attractions" :key="attraction.contentId" :attraction="attraction" :index="(page - 1) * 10 + index + 1" @setInterest="setInterest"></like-page-item>
    </table>
    <p v-if="totalCount == 0" style="text-align: center">좋아요한 관광지가 없습니다.</p>
    <like-pagination :page="page" :totalCount="totalCount" @setPage="setPage"></like-pagination>
  </div>
</template>

<script>
import http from "@/util/http";
import LikePagination from "./LikePagination.vue";
import LikePageItem from "./LikePageItem.vue";
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
    LikePageItem,
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
    setInterest(contentId, interest) {
      for (let i = 0; i < this.attractions.length; i++) {
        if (this.attractions[i].contentId == contentId) {
          this.attractions[i].interest = interest;
          return;
        }
      }
    },
  },
  watch: {
    page() {
      this.setAttractions();
    },
  },
};
</script>

<style>
table,
th,
td {
  /* width: 100%; */
  /* border: 1px solid #444444; */
  border-collapse: collapse;
  height: 60%;
}
.like-page {
  width: 60%;
  margin: 5% auto 0;
  border-spacing: 0.2rem;
}
</style>
