<template>
  <header>
    <!-- 로고 -->
    <div class="column logo">
      <h1>
        <router-link to="/" @click="this.$store.commit('initSearchData')">
          <img src="https://cdn-icons-png.flaticon.com/512/3541/3541533.png" alt="traveler" />
          <img src="@/assets/img/traveler.png" alt="traveler" style="width: 10rem; height: 4rem" />
          <!-- Traveler -->
        </router-link>
        <!-- <a href="/">
          
        </a> -->
      </h1>
    </div>
    <!-- 검색 -->
    <div class="column search">
      <select class="search-keyword" name="keyword" v-model="keyword">
        <option v-for="(item, index) in selectList" :key="index" :value="item.value">{{ item.name }}</option>
      </select>
      <input class="search-input" type="text" placeholder="검색어 입력" v-model="content" />
      <router-link :to="{ name: 'tour' }">
        <input type="button" class="search-image" v-on:click="commitSearchData" />
      </router-link>
      <!-- <input type="button" class="search-image" /> -->
    </div>
    <!-- 게시판 -->
    <div class="column board" v-on:mouseover="showHiddenBoard" v-on:mouseout="showHiddenBoard">
      <span>게시판</span>
      <ul class="hidden-board">
        <li><router-link :to="{ name: 'info-list' }">공지 사항</router-link></li>

        <li><a href="">후기 게시판</a></li>
      </ul>
    </div>
    <div class="column login">
      <router-link v-if="token == null" :to="{ name: 'login' }"> 로그인 </router-link>
      <!-- <button v-else @click="logout">로그아웃</button> -->
      <span v-else @click="logout">로그아웃</span>
    </div>
  </header>
</template>

<script>
export default {
  created() {
    // 테스트
    // console.log(this.$store.state.SearchData.keyword);

    // console.log($storage.getters.getTwoPowerCounter);
    // console.log(this.$store.getters.keyword);
    // console.log(this.$store.getters.content);
    this.keyword = "title";
    this.content = "";
    this.code = 0;
    this.page = 1;
  },
  data() {
    return {
      hiddenToggle: true,
      keyword: "title",
      content: "",
      page: 1,
      code: 0,
      selectList: [
        { name: "제목", value: "title" },
        { name: "주소", value: "addr1" },
      ],
    };
  },
  methods: {
    showHiddenBoard() {
      //   const board = document.querySelector(".board");
      const hiddenBoard = document.querySelector(".board > .hidden-board");
      if (this.hiddenToggle) {
        hiddenBoard.style.height = "100px";
        this.hiddenToggle = !this.hiddenToggle;
      } else {
        hiddenBoard.style.height = "0px";
        this.hiddenToggle = !this.hiddenToggle;
      }
    },
    // commitKeywordContent() {
    //   console.log("commitKeywordContent");
    //   // keyword vuex에 저장
    //   this.$store.commit("setKeyword", this.keyword);
    //   // content vuex에 저장
    //   this.$store.commit("setContent", this.content);
    //   this.$store.commit("setCode", this.code);
    // },
    commitSearchData() {
      console.log("commitSearchData");
      let searchData = {
        keyword: this.keyword,
        content: this.content,
        page: this.page,
        code: this.code,
      };
      this.$store.commit("setSearchData", searchData);
      // keyword vuex에 저장
      // this.$store.commit("setKeyword", this.keyword);
      // content vuex에 저장
      // this.$store.commit("setContent", this.content);
      // this.$store.commit("setCode", this.code);
    },
    resetSearchData() {
      this.keyword = "title";
      this.content = "";
      this.code = 0;
      this.page = 1;
      // let searchData = {
      //   keyword: this.keyword,
      //   content: this.content,
      // };
      this.$store.commit("initSearchData");
    },
    logout() {
      console.log("로그아웃");
      this.$store.commit("CLEAR_MEMBER");
      this.$router.push("/").catch((err) => {
        console.log(err);
      });
    },
  },
  computed: {
    token() {
      return this.$store.state.token;
    },
  },
};
</script>

<style>
* {
  padding: 0;
  margin: 0;
}

li,
ol,
ul {
  list-style: none;
}
a {
  text-decoration-line: none;
}

/* 헤더 */
header {
  flex-flow: row wrap;
  display: flex;
  width: 60%;
  height: auto;
  margin: 0 auto;
  background-color: transparent;
  justify-content: space-around;
  align-items: center;
  font-family: Cafe24SsurroundAir;
  font-weight: 600;
  font-size: 1.2em;
  /* box-shadow: 0 0 4px black; */
}

.column {
  cursor: pointer;
}

/* 로고 */
.logo img {
  background-size: 100px 100px;
  width: 100px;
  height: 100px;
  vertical-align: middle;
}

/* 검색 */
.search-keyword {
  height: 30px;
  border: 1px solid #bcbcbc;
}
input {
  vertical-align: middle;
}
input.search-input {
  border: 1px solid #bcbcbc;
  height: 28px;
  outline: none;
}
.search-image {
  background-size: 32px 32px;
  width: 32px;
  height: 32px;
  background-image: url("https://cdn1.iconfinder.com/data/icons/unicons-line-vol-5/24/search-alt-512.png");
  background-color: transparent;
  background-repeat: no-repeat;
  border: none;
  cursor: pointer;
}

/* 게시판 */
.board {
  height: 50px;
  line-height: 50px;
  text-align: center;
}
.board > .hidden-board {
  height: 0;
  list-style: none;
  overflow: hidden;
  transition: height ease-out 0.2s 0s;
  /* text-align: center; */
}
.board > .hidden-board > li {
  background-color: transparent;
  border-top: 0 white solid;
}
</style>
