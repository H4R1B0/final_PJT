<template>
  <section class="regist-container">
    <h1 class="underline">공지사항 등록</h1>
    <div class="regist_form">
      <label for="title">제목</label>
      <input type="text" id="title" v-model="title" ref="title" /><br />
      <label for="writer">작성자</label>
      <input type="text" id="writer" v-model="writer" ref="writer" readonly /><br />
      <label for="content">내용</label>
      <br />
      <textarea id="content" v-model="content" ref="content" cols="35" rows="5"></textarea><br />
      <button @click="checkValue">등록</button>
      <button @click="moveList">목록</button>
    </div>
  </section>
</template>

<script>
import http from "@/util/http";
export default {
  name: "infoWrite",
  data() {
    return {
      title: null,
      writer: this.$store.state.memberInfo.member_id,
      content: null,
    };
  },
  methods: {
    //입력 값 체크 - 체크가 성공하면 registArticle 호출
    checkValue() {
      let err = true;
      let msg = "";
      !this.title && ((msg = "제목을 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.registInfo();
    },
    registInfo() {
      let info = {
        title: this.title,
        writer: this.writer,
        content: this.content,
      };
      http
        .post(`/board/info`, info)
        .then(() => {
          this.moveList();
        })
        .catch(() => {
          let msg = "관리자만 작성할 수 있는 게시판입니다.";
          alert(msg);
        });
    },
    moveList() {
      this.$router.push({ path: "info-list" });
    },
  },
};
</script>

<style>
.regist-container {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  width: 50%;
  height: 700px;
  margin: 0 auto;
  margin-top: 2rem;
}
</style>
