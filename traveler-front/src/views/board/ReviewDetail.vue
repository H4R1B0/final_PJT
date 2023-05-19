<template>
  <section class="detail-container">
    <div class="detail-title">
      {{ reviewArticle.title }}
    </div>
    <div class="detail-set">
      <div class="detail-writer"><label class="detail-set-label">작성자 </label> {{ reviewArticle.writer }}</div>
      <div class="detail-readCnt"><label class="detail-set-label">조회수</label> {{ reviewArticle.readCount }}</div>
      <div class="detail-time"><label class="detail-set-label">작성 시각</label> {{ reviewArticle.writeDate }}</div>
    </div>
    <br />
    <label class="content"> 내용</label><br />
    <div class="content-box" v-html="reviewArticle.content"></div>
    <br />
    <div>
      <button class="btn" id="update-btn" @click="moveModifyArticle">수정</button>
      <button class="btn" id="delete-btn" @click="deleteArticle">삭제</button>
      <button class="btn" id="list-btn" @click="moveList">목록</button>
    </div>
  </section>
</template>

<script>
import http from "@/util/http";
export default {
  data() {
    return {
      reviewArticle: Object,
    };
  },
  created() {
    console.log(this.$route);
    let no = this.$route.query.no;
    http.get(`/board/review/${no}`).then((res) => {
      console.log(res.data);
      this.reviewArticle = res.data;
      this.reviewArticle.content = this.changeB(this.reviewArticle.content);
    });
  },
  methods: {
    moveList() {
      this.$router.push({ path: "/review-list" });
    },
    deleteArticle() {
      let no = this.reviewArticle.no;
      http.delete(`/board/review/${no}`).then((response) => {
        console.log(response);
        this.$router.push({ path: "/review-list" });
      });
    },
    changeB(value) {
      return value.replace(/(?:\r\n|\r|\n)/g, "<br/>");
    },
    moveModifyArticle() {
      this.$router.push({ path: "/review-update", query: { no: this.reviewArticle.no } });
    },
  },
};
</script>

<style>
* {
  font-family: Cafe24SsurroundAir;
}
.detail-container {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  width: 50%;
  height: 50rem;
  margin: 0 auto;
  margin-top: 2rem;
  /* padding: 2rem 2rem 2rem 2rem; */
}
/* 상세보기 제목 */
.detail-title {
  color: #5a5a5a;
  font-size: 2.3rem;
  font-weight: bold;
  padding-top: 5rem;
  text-align: center;
}
/*제목 밑에 3종 세트 (작성자, 조회수, 작성 시각) */
.detail-set {
  color: #5a5a5a;
  font-size: 1.2rem;
}
.detail-set-label {
  font-weight: bold;
}
.detail-writer {
  text-align: left;
  margin-top: 2rem;
  padding-left: 15rem;
}
.detail-readCnt {
  padding-left: 15rem;
  margin-top: 0.7rem;
}
.detail-time {
  padding-left: 15rem;
  margin-top: 0.7rem;
}
.content {
  color: #5a5a5a;
  font-size: 1.2rem;
  font-weight: bold;
  padding-left: 15rem;
}
.content-box {
  background-color: #fffcfc;
  width: 49%;
  height: 45%;
  margin: 0 auto;
  border-radius: 10px;
  padding: 1rem 1rem 1rem 1rem;
}
/* 버튼 css*/
.btn {
  background-color: #0a0a23;
  color: #fff;
  font-size: 0.8rem;
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
.btn:hover {
  letter-spacing: 2px;
  transform: scale(1);
  cursor: pointer;
}
/* 수정 버튼 */
#update-btn {
  float: right;
  margin-right: 15rem;
}
/* 삭제 버튼 */
#delete-btn {
  float: right;
  /* margin-right: 16rem; */
}
/*목록 버튼*/
#list-btn {
  float: right;
  /* margin-right: 1rem; */
}
</style>
