<template>
  <div class="mypage-container">
    <div class="left-container">
      <ul>
        <li @click="rendering('modify')">개인 정보 변경</li>
        <li @click="rendering('like')">좋아요한 관광지</li>
      </ul>
    </div>
    <div class="right-container">
      <modify-page v-if="renderView === 'modify'"></modify-page>
      <like-page v-else-if="renderView === 'like'"></like-page>
    </div>
  </div>
</template>

<script>
import ModifyPage from "./ModifyPage.vue";
import LikePage from "./LikePage.vue";
export default {
  components: {
    ModifyPage,
    LikePage,
  },
  data() {
    return {
      renderView: "modify",
    };
  },
  methods: {
    rendering(value) {
      this.renderView = value;
    },
  },
};
</script>

<style>
.mypage-container {
  display: flex;
  width: 80%;
  height: 30rem;
  border-radius: 10px;
  overflow: hidden;
}
/* 왼쪽 컨테이너 */
.left-container {
  display: flex;
  background-color: blue;
  height: 100%;
  width: 30%;
  align-items: center;
}
.left-container > ul {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  width: 100%;
}
.left-container li {
  display: flex;
  cursor: pointer;
  margin: 10% auto;
  border: 1px solid black;
  background-color: white;
  padding: 5% 0;
  width: 70%;
  justify-content: center;
}
/* 오른쪽 컨테이너 */
.right-container {
  display: block;
  background-color: grey;
  height: 100%;
  width: 70%;
}
</style>
