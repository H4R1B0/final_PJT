<template>
  <section class="info-list-container">
    <h1 class="info-title"><img class="info-img" src="https://cdn-icons-png.flaticon.com/128/9498/9498078.png" />공지사항</h1>
    <div>
      <table class="info-position">
        <colgroup class="info-item">
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
        <info-list-item class="info-item" v-for="(info, idx) in infos" :key="info.no" :infoComponent="info" :idx="idx" :page="page"></info-list-item>
      </table>
      <div class="info-regist">
        <button @click="goRegist" class="info-regist-button">공지 등록</button>
      </div>
    </div>
    <board-pagination :page="page" :totalCount="totalCount" @setPage="setPage"></board-pagination>
    <!-- <div>게시글이 없습니다.</div> -->
  </section>
</template>

<script>
import http from "@/util/http";
import InfoListItem from "@/views/board/InfoListItem";
import BoardPagination from "./BoardPagination.vue";

export default {
  name: "InfoList",
  components: {
    InfoListItem,
    BoardPagination,
  },
  data() {
    return {
      infos: [],
      page: 1,
      totalCount: 0,
    };
  },
  created() {
    http.get("/board/info/total").then((response) => {
      // console.log(response);
      this.totalCount = response.data;
      // console.log(this.infos);
    });
    this.setInfos();
  },
  methods: {
    setInfos() {
      http.get(`/board/info?page=${this.page}`).then((response) => {
        console.log(response);
        this.infos = response.data;
        console.log(this.infos);
      });
    },
    async goRegist() {
      let cnt = await this.$store.dispatch("isAdmin");
      console.log(cnt);
      if (cnt == 1) {
        this.$router.push({ name: "info-write" });
      } else {
        alert("관리자만 작성할 수 있습니다.");
      }
    },
    setPage(value) {
      // console.log("자식에게 받은 page:", value);
      this.page = value;
    },
  },
  watch: {
    page() {
      this.setInfos();
    },
  },
};
</script>

<style scoped>
* {
  font-family: Cafe24SsurroundAir;
}
.info-title {
  padding: 6rem 6rem 2rem 9rem;
  font-size: 2rem;
}
/* 확성기 사진 */
.info-img {
  width: 2.5rem;
  height: 3rem;
  /* padding-top: 1rem; */
  margin-right: 1rem;
}
.info-list-container {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  width: 60%;
  height: 900px;
  margin: 0 auto;
}
/* .info-position,
.info-position th,
.info-position td {
  border: 1px solid black;
} */
/* table class */
.info-position {
  border-collapse: collapse;
  width: 70%;
  text-align: center;
  margin: auto;
}
.info-item {
  cursor: pointer;
}
.info-item:hover {
  background: #f3f3ff;
}
.info-regist {
  text-align: right;
}
.info-regist-button {
  margin: 1rem 11rem 0 0;
  /* background-color: #0a0a23;
  color: #fff;
  border: none;
  border-radius: 10px;
  box-shadow: 0px 0px 2px 2px rgb(0, 0, 0); */
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
.info-regist-button:hover {
  letter-spacing: 2px;
  transform: scale(1);
  cursor: pointer;
}
</style>
