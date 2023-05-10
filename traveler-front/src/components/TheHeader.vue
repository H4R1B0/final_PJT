<template>
  <header>
    <!-- 로고 -->
    <div class="column logo">
      <h1>
        <a href=""><img src="/asset/images/header-logo.jpg" alt="traveler" /></a>
      </h1>
    </div>
    <!-- 검색 -->
    <div class="column search">
      <input class="search-input" type="text" placeholder="검색어 입력" />
      <input type="button" class="search-image" />
    </div>
    <!-- 게시판 -->
    <div class="column board" v-on:mouseover="showHiddenBoard" v-on:mouseout="showHiddenBoard">
      <span>게시판</span>
      <ul class="hidden-board">
        <li><a href="">공지 사항</a></li>
        <li><a href="">후기 게시판</a></li>
      </ul>
    </div>
    <div class="column login">
      <a href="#">로그인</a>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      hiddenToggle: true,
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
  },
};
</script>

<style>
* {
  padding: 0;
  margin: 0;
}

li ol ul {
  list-style: none;
}

/* 헤더 */
header {
  flex-flow: row wrap;
  display: flex;
  width: 100%;
  height: auto;
  background-color: transparent;
  justify-content: space-around;
  align-items: center;
}

/* 검색 */
input {
  vertical-align: middle;
}
input.search-input {
  border: 1px solid #bcbcbc;
  height: 28px;
  outline: none;
}
input.search-image {
  background-size: 32px 32px;
  width: 32px;
  height: 32px;
  background-image: url("https://cdn1.iconfinder.com/data/icons/unicons-line-vol-5/24/search-alt-512.png");
  /* background-color: transparent; */
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
  text-decoration: none;
}
</style>
