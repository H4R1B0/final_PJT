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
      <input
        class="search-input"
        type="text"
        placeholder="검색어 입력"
        v-model="content"
        @input="submitAutoComplete"
        @keyup.up="searchKeyUp"
        @keyup.down="searchKeyDown"
        @keyup.enter="commitSearchData"
      />
      <input type="button" class="search-image" v-on:click="commitSearchData" />
      <select class="auto-complete disabled" :size="Math.min(5, result.length)">
        <option v-if="result.length == 0">검색된 내용이 없습니다.</option>
        <option style="cursor: pointer; margin: 1px" v-for="(res, i) in result" :key="i" :value="res" @click="searchContentAdd(res, i)">
          {{ res }}
        </option>
      </select>
      <!-- <input type="button" class="search-image" /> -->
    </div>
    <!-- 게시판 -->
    <div class="column board" v-on:mouseover="showHiddenBoard" v-on:mouseout="showHiddenBoard">
      <span>게시판</span>
      <ul class="hidden-board">
        <li><router-link :to="{ name: 'info-list' }">공지 사항</router-link></li>
        <li><router-link :to="{ name: 'review-list' }">후기 게시판</router-link></li>
      </ul>
    </div>
    <div class="column member">
      <router-link v-if="token == null" :to="{ name: 'login' }"> 로그인 </router-link>
      <div class="login-after" v-else>
        <router-link :to="{ name: 'mypage' }">마이페이지</router-link>
        <span @click="logout">로그아웃</span>
      </div>
    </div>
  </header>
</template>

<script>
// import jwtDecode from "jwt-decode";
import titles from "@/util/titles";
import addresses from "@/util/addresses";
export default {
  created() {
    // 테스트
    console.log("aaaaa");

    // console.log($storage.getters.getTwoPowerCounter);
    // console.log(this.$store.getters.keyword);
    // console.log(this.$store.getters.content);
    this.keyword = "title";
    this.content = "";
    this.code = 0;
    this.page = 1;
    window.addEventListener("click", function (e) {
      // console.log(e.target.classList.value == "search-input");
      if (e.target.classList.value != "search-input") {
        window.document.querySelector(".auto-complete").classList.add("disabled");
      } else {
        window.document.querySelector(".auto-complete").classList.remove("disabled");
      }
    });
  },
  data() {
    return {
      // deg: 0,
      hiddenToggle: true,
      keyword: "title",
      content: "",
      result: [],
      page: 1,
      code: 0,
      selectList: [
        { name: "제목", value: "title" },
        { name: "주소", value: "addr1" },
      ],
      searchIndex: -1,
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
      window.document.querySelector(".auto-complete").classList.add("disabled");

      console.log("commitSearchData");
      //회전 이스터에그
      if (this.content == "돌려라~") {
        console.log("회전");
        // this.deg += 360;
        this.$store.commit("SET_DEG");
        return;
      }
      //삐딱한 이스터에그
      if (this.content == "삐딱하게") {
        document.body.style.transform = "rotate(2deg)";
        return;
      }
      document.body.style.transform = "rotate(0)";
      let searchData = {
        keyword: this.keyword,
        content: this.content,
        page: this.page,
        code: this.code,
      };
      this.$store.commit("setSearchData", searchData);
      this.$router.push({ name: "tour" }).catch(() => {
        console.log("reload");
        // window.location.reload;
      });
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
    submitAutoComplete() {
      this.searchIndex = -1;
      const autoComplete = document.querySelector(".auto-complete");
      if (this.content != "") {
        autoComplete.classList.remove("disabled");
        if (this.keyword == "title") {
          this.result = titles.filter((title) => {
            return title.match(new RegExp("^" + this.content, "i"));
          });
        } else {
          this.result = addresses.filter((address) => {
            return address.match(new RegExp("^" + this.content, "i"));
          });
        }
      } else {
        autoComplete.classList.add("disabled");
      }
      // console.log(this.result);
    },
    searchKeyUp() {
      console.log("key up");
      const autoComplete = document.querySelector(".auto-complete");
      let autoCompleteCount = autoComplete.childElementCount;
      console.log("자동완성 개수:", autoCompleteCount);
      if (this.searchIndex == -1) this.searchIndex = 0;
      this.searchIndex = (this.searchIndex + autoCompleteCount - 1) % autoCompleteCount;
      console.log("현재 인덱스:", this.searchIndex);
      autoComplete.selectedIndex = this.searchIndex;
      this.content = this.result[this.searchIndex];
    },
    searchKeyDown() {
      console.log("key down");
      const autoComplete = document.querySelector(".auto-complete");
      let autoCompleteCount = autoComplete.childElementCount;
      console.log("자동완성 개수:", autoCompleteCount);
      this.searchIndex = (this.searchIndex + 1) % autoCompleteCount;
      console.log("현재 인덱스:", this.searchIndex);
      autoComplete.selectedIndex = this.searchIndex;
      this.content = this.result[this.searchIndex];
    },
    searchContentAdd(content, idx) {
      console.log(content);
      this.content = content;
      document.querySelector(".auto-complete").selectedIndex = idx;
      this.searchIndex = idx;
    },
  },
  computed: {
    token() {
      return this.$store.state.token;
    },
  },
  watch: {
    keyword() {
      this.submitAutoComplete();
    },
  },
};
</script>

<style scoped>
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
  margin: 1rem auto 1rem;
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
.search {
  display: flex;
  position: relative;
  height: 2.5rem;
  /* border-radius: 20px; */
  /* overflow: hidden; */
}
.search > .search-image {
  position: absolute;
  right: 5px;
  top: 2px;
}
.search-keyword {
  height: 100%;
  border: 1px solid #bcbcbc;
  border-radius: 10px 0 0 10px;
}
input {
  vertical-align: middle;
}
.search > .search-input {
  border: 1px solid #bcbcbc;
  height: 100%;
  width: 20rem;
  outline: none;
  border-radius: 0 10px 10px 0;
  padding-left: 0.5rem;
}
.search-image {
  background-size: 38px 38px;
  width: 35px;
  height: 35px;
  background-image: url("https://cdn1.iconfinder.com/data/icons/unicons-line-vol-5/24/search-alt-512.png");
  background-color: transparent;
  background-repeat: no-repeat;
  border: none;
  cursor: pointer;
}
/* 자동완성 */
.auto-complete {
  position: absolute;
  width: 23.2rem;
  height: 6rem;
  font-size: 0.8rem;
  /* overflow: scroll; */
  background-color: rgba(255, 255, 255, 0.5);
  /* opacity: 0.6; */
  border-radius: 10px 10px 10px 10px;
  /* padding-left: 0.5rem; */
  top: 3rem;
}
.auto-complete > * {
  padding-left: 1rem;
  font-size: 1rem;
}
/* .auto-complete::-webkit-scrollbar {
  display: none;
}
.auto-complete > * {
  margin-top: 10px;
} */
.disabled {
  display: none;
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
  position: relative;
}
.board > .hidden-board > li {
  background-color: transparent;
  border-top: 0 white solid;
}
/* 멤버 */
.login-after > * {
  margin: 0 0.5rem;
}
input,
select {
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
}
</style>
