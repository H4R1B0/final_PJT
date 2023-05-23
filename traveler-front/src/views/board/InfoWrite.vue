<template>
  <section class="regist-container">
    <div class="regist-title">공지사항 등록</div>
    <br />
    <div class="regist-form">
      <div clss="regist-label" style="transform: translateX(-12rem)"><label for="title">제목 </label></div>
      <input type="text" id="title" v-model="title" ref="title" />
      <br />
      <br />
      <!-- <label for="writer">작성자</label>
      <input type="text" id="writer" v-model="writer" ref="writer" readonly /><br /> -->
      <div clss="regist-label" style="transform: translateX(-12rem)"><label for="content">내용</label></div>
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
      err && !this.content && ((msg = "내용을 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.registInfo();
    },
    registInfo() {
      let info = {
        title: this.title,
        writer: this.writer,
        content: this.changeA(this.content),
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
      this.$router.push({ path: "/info-list" });
    },
    changeA() {
      let str = document.getElementById("content").value;
      return str.replaceAll("<br/>", "\r\n");
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
.regist-container {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  width: 50%;
  height: 700px;
  margin: 0 auto;
  margin-top: 2rem;
  /* padding: 2rem 2rem 2rem 2rem; */
}
/* 등록 화면 제목 */
.regist-title {
  color: #5a5a5a;
  font-size: 3rem;
  font-weight: bold;
  padding-top: 7rem;
  text-align: center;
}
.regist-form {
  text-align: center;
  font-size: 1.3rem;
}
.regist-form > input {
  width: 27rem;
  height: 2rem;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: #fffcfc;
}
.regist-form > textarea {
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
.write-page-button {
  background-color: #0a0a23;
  color: #fff;
  font-size: 1rem;
  font-weight: 550;
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
.write-page-button:hover {
  letter-spacing: 2px;
  transform: scale(1);
  cursor: pointer;
}
#regist-button {
  margin-left: 17rem;
}
</style>
