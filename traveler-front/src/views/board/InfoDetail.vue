<template>
  <section class="detail-container">
    <div class="detail-all">
      {{ infoArticle.title }}
      <br />
      <label>작성자</label>{{ infoArticle.writer }}
      <br />
      <label> 작성 시간</label>{{ infoArticle.writeDate }}
      <br />
      <label> 조회수</label>{{ infoArticle.readCount }}
      <br />
      <label> 내용</label>{{ infoArticle.content }}

      <!-- <div style="padding-top: 15px">
        <button class="btn" @click="moveModifyArticle">수정</button>
        <button class="btn" @click="deleteArticle">삭제</button>
        <button class="btn" @click="moveList">목록</button>
      </div> -->
    </div>
  </section>
</template>

<script>
import http from "@/util/http";
export default {
  data() {
    return {
      infoArticle: Object,
    };
  },
  created() {
    console.log(this.$route);
    let no = this.$route.query.no;
    http.get(`/board/info/${no}`).then((res) => {
      console.log(res.data);
      this.infoArticle = res.data;
    });
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
  height: 700px;
  margin: 0 auto;
  margin-top: 2rem;
  /* padding: 2rem 2rem 2rem 2rem; */
}
.detail-all {
  text-align: center;
}
</style>
