<template>
  <div class="mypage-container">
    <div class="left-container">
      <button @click="rendering('modify')" :disabled="renderView == 'modify'">개인 정보 변경</button>
      <button @click="rendering('like')" :disabled="renderView == 'like'">좋아요한 관광지</button>
      <button @click="withdrawal">회원 탈퇴</button>
    </div>
    <div class="right-container">
      <modify-page v-if="renderView === 'modify'"></modify-page>
      <like-page v-else-if="renderView === 'like'"></like-page>
    </div>
  </div>
</template>

<script>
import http from "@/util/http";
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
    withdrawal() {
      this.rendering("withdrawal");
      var result = confirm("회원 탈퇴 하시겠습니까");
      // console.log(result);
      if (result) {
        http
          .delete(`/member/${this.$store.state.memberInfo.member_id}`)
          .then(() => {
            this.$store.commit("CLEAR_MEMBER");
            console.log("탈퇴 완료");
            this.$router.push({ name: "main" });
          })
          .catch(() => {
            console.log("탈퇴 오류");
          });
      } else {
        this.rendering("modify");
      }
    },
  },
};
</script>

<style>
.mypage-container {
  display: flex;
  width: 60%;
  height: 30rem;
  border-radius: 10px;
  overflow: hidden;
  margin: 0 auto;
}
/* 왼쪽 컨테이너 */
.left-container {
  display: flex;
  flex-direction: column;
  background-color: rgba(255, 255, 255, 0.6);
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
  background-color: rgba(185, 203, 235, 0.6);
  height: 100%;
  width: 70%;
}
</style>
