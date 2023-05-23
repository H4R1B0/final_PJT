<template>
  <section class="update-container">
    <div class="update-title">후기 수정</div>
    <br />
    <div class="update-form">
      <div clss="update-label" style="transform: translateX(-12rem)"><label for="title">제목</label></div>
      <input type="text" id="title" v-model="reviewArticle.title" ref="title" />
      <br />
      <br />
      <div clss="update-label" style="transform: translateX(-12rem)"><label for="content">내용</label></div>
      <textarea id="content" v-model="reviewArticle.content" ref="content" cols="35" rows="5"></textarea>
      <br />
      <button class="update-page-button" id="update-button" @click="updateReviewArticle">완료</button>
      <button class="update-page-button" @click="moveList">목록</button>
    </div>
  </section>
</template>

<script>
import http from "@/util/http";
export default {
  data() {
    return {
      no: Number,
      reviewArticle: Object,
    };
  },
  created() {
    this.no = this.$route.query.no;
    http.get(`/board/review/${this.no}`).then((response) => {
      console.log(response);
      this.reviewArticle = response.data;
    });
  },
  methods: {
    updateReviewArticle() {
      console.log(this.reviewArticle.no + "번글 수정 하기");
      http
        .put(`/board/review`, this.reviewArticle)
        .then(() => {
          this.$router.push({ name: "review-detail", query: { no: this.reviewArticle.no } });
        })
        .catch(() => {
          let msg = "수정 불가";
          alert(msg);
        });
    },
    moveList() {
      this.$router.push({ path: "/review-list" });
    },
  },
};
</script>

<style scoped>
* {
  font-family: Cafe24SsurroundAir;
}
label {
  color: #5a5a5a;
}
.update-container {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  width: 50%;
  height: 700px;
  margin: 0 auto;
  margin-top: 2rem;
  /* padding: 2rem 2rem 2rem 2rem; */
}
/* 수정 화면 제목 */
.update-title {
  color: #5a5a5a;
  font-size: 3rem;
  font-weight: bold;
  padding-top: 7rem;
  text-align: center;
}
/* 수정 전체 div */
.update-form {
  text-align: center;
  font-size: 1.3rem;
}
.update-form > input {
  width: 27rem;
  height: 2rem;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: #fffcfc;
}
.update-form > textarea {
  width: 27rem;
  height: 13rem;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  padding-top: 10px;
  background-color: #fffcfc;
}
/* 버튼 css */
.update-page-button {
  background-color: #0a0a23;
  color: #fff;
  font-size: 1rem;
  font-weight: 600;
  border: none;
  border-radius: 10px;
  box-shadow: 0px 0px 2px 2px rgb(0, 0, 0);
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
.update-page-button:hover {
  letter-spacing: 2px;
  transform: scale(1);
  cursor: pointer;
}
#update-button {
  margin-left: 18rem;
}
</style>
