<template>
  <section class="update-container">
    <div class="regist-title">공지사항 수정</div>
    <br />
    <div class="regist-form">
      <div clss="regist-label" style="transform: translateX(-11rem)"><label for="title">제목 </label></div>
      <input type="text" id="title" v-model="title" ref="title" />
      <br />
      <br />
      <!-- <label for="writer">작성자</label>
      <input type="text" id="writer" v-model="writer" ref="writer" readonly /><br /> -->
      <div clss="regist-label" style="transform: translateX(-11rem)"><label for="content">내용</label></div>
      <textarea id="content" v-model="content" ref="content" cols="35" rows="5"></textarea>
      <br />
      <br />
      <button class="write-page-button" id="regist-button" @click="checkValue">등록</button>
      <button class="write-page-button" @click="moveList">목록</button>
    </div>
  </section>
</template>

<script>
import http from "@/util/http";
export default {
  data() {
    return {
      no: Number,
      infoArticle: Object,
    };
  },
  created() {
    this.no = this.$route.query.no;
    http.get(`/board/info/${this.no}`).then((response) => {
      console.log(response);
    });
  },
};
</script>

<style></style>
