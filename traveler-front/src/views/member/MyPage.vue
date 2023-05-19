<template>
  <div class="mypage-container">
    <div class="left-container">
      <button @click="rendering('modify')" :disabled="renderView == 'modify'">개인 정보 변경</button>
      <button @click="rendering('like')" :disabled="renderView == 'like'">좋아요한 관광지</button>
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
  flex-direction: column;
  background-color: blue;
  height: 100%;
  width: 30%;
  align-items: center;
  justify-content: center;
}
.left-container > button {
  display: flex;
  /* margin: 0 auto; */
  width: 100%;
  background-color: white;
  padding: 5% 0;
  border: 0;
  font-size: 1rem;
}
button:not(:disabled) {
  cursor: pointer;
}
/* 오른쪽 컨테이너 */
.right-container {
  display: block;
  background-color: grey;
  height: 100%;
  width: 70%;
}
</style>
