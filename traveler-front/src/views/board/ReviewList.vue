<template>
  <section class="review-list-container">
    <h1 class="review-title">후기 게시판</h1>
    <div>
      <table class="review-position">
        <colgroup class="review-item">
          <col style="width: 7%" />
          <col style="width: 15%" />
          <col style="width: 7%" />
          <col style="width: 7%" />
          <col style="width: 10%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>작성일</th>
          </tr>
        </thead>
        <review-list-item class="review-item" v-for="(review, idx) in reviews" :key="review.no" :reviewComponent="review" :idx="idx"></review-list-item>
      </table>
      <div class="review-regist">
        <button @click="goRegist" class="review-regist-button">후기 등록</button>
      </div>
    </div>
  </section>
</template>

<script>
import http from "@/util/http";
import ReviewListItem from "@/views/board/ReviewListItem";

export default {
  name: "ReviewList",
  components: {
    ReviewListItem,
  },
  data() {
    return {
      reviews: [],
    };
  },
  created() {
    http.get(`/board/review`).then((response) => {
      console.log(response);
      this.reviews = response.data;
      console.log(this.reviews);
    });
  },
  methods: {
    goRegist() {
      if (this.$store.state.token == null) alert("로그인 후 이용 가능한 서비스입니다.");
      else this.$router.push({ name: "review-write" });
    },
  },
};
</script>

<style>
* {
  font-family: Cafe24SsurroundAir;
}
.review-title {
  padding: 6rem 6rem 2rem 9rem;
  font-size: 2rem;
}
.review-list-container {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  width: 60%;
  height: 900px;
  margin: 0 auto;
}
/* table class */
.review-position {
  /* border-collapse: collapse; */
  width: 70%;
  text-align: center;
  margin: auto;
}
.review-item {
  cursor: pointer;
}
.review-regist {
  text-align: right;
}
.review-regist-button {
  margin: 1rem 11rem 0 0;
  position: relative;
  border: none;
  display: inline-block;
  padding: 10px 20px;
  border-radius: 15px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  transition: 0.25s;
  background: linear-gradient(-45deg, #33ccff 0%, #ff99cc 100%);
  color: white;
}
.review-regist-button:hover {
  letter-spacing: 2px;
  transform: scale(1);
  cursor: pointer;
}
</style>
