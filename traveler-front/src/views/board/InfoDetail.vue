<template>
  <section class="detail-container">
    <div class="detail-title">
      {{ infoArticle.title }}
    </div>
    <div class="detail-set">
      <div class="detail-writer"><label class="detail-set-label">작성자 </label> {{ infoArticle.writer }}</div>
      <div class="detail-readCnt"><label class="detail-set-label">조회수</label> {{ infoArticle.readCount }}</div>
      <div class="detail-time"><label class="detail-set-label">작성 시각</label> {{ infoArticle.writeDate }}</div>
    </div>
    <br />
    <label class="content"> 내용</label><br />
    <div class="content-box" v-html="infoArticle.content"></div>
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
      infoArticle: Object,
    };
  },
  created() {
    console.log(this.$route);
    let no = this.$route.query.no;
    http.get(`/board/info/${no}`).then((res) => {
      console.log(res.data);
      this.infoArticle = res.data;
      this.infoArticle.content = this.changeB(this.infoArticle.content);
    });
  },
  methods: {
    moveList() {
      this.$router.push({ path: "/info-list" });
    },
    async deleteArticle() {
      let cnt = await this.$store.dispatch("isAdmin");
      if (cnt == 1) {
        let no = this.infoArticle.no;
        http.delete(`/board/info/${no}`).then((response) => {
          console.log(response);
          this.$router.push({ path: "/info-list" });
        });
      } else {
        alert("관리자만 삭제할 수 있습니다.");
      }
    },
    changeB(value) {
      return value.replace(/(?:\r\n|\r|\n)/g, "<br/>");
    },
    async moveModifyArticle() {
      let cnt = await this.$store.dispatch("isAdmin");
      if (cnt == 1) {
        this.$router.push({ path: "/info-update", query: { no: this.infoArticle.no } });
      } else {
        alert("관리자만 수정할 수 있습니다.");
      }
    },
  },
};
</script>

<style scoped>
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
  padding-left: 16rem;
}
.detail-readCnt {
  padding-left: 16rem;
  margin-top: 0.7rem;
}
.detail-time {
  padding-left: 16rem;
  margin-top: 0.7rem;
}
.content {
  color: #5a5a5a;
  font-size: 1.2rem;
  font-weight: bold;
  padding-left: 16rem;
}
.content-box {
  background-color: #fffcfc;
  width: 26.5rem;
  height: 22.5rem;
  margin: 0 auto;
  border-radius: 10px;
  padding: 1rem 1rem 1rem 1rem;
}
/* 버튼 css*/
.btn {
  background-color: #0a0a23;
  color: #fff;
  font-size: 0.8rem;
  font-weight: 500;
  border: none;
  border-radius: 10px;
  box-shadow: 0px 0px 2px 2px rgb(0, 0, 0);
  position: relative;
  border: none;
  display: inline-block;
  padding: 8px 15px;
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
