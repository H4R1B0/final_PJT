<template>
  <form action="" class="modify-container">
    <h1>개인정보 수정</h1>
    <h3>이름</h3>
    <input type="text" v-model="memberInfo.member_name" />
    <h3>이메일</h3>
    <input type="text" v-model="memberInfo.email" />
    <input class="modify-button" type="button" value="수정하기" @click="modifyInfo" />
  </form>
</template>

<script>
// import http from "@/util/http";
export default {
  data() {
    return {
      memberInfo: {},
    };
  },
  created() {
    this.memberInfo.member_name = this.$store.state.memberInfo.member_name;
    this.memberInfo.email = this.$store.state.memberInfo.email;
  },
  methods: {
    modifyInfo() {
      let data = {
        member_id: this.$store.state.memberInfo.member_id,
        member_name: this.memberInfo.member_name,
        email: this.memberInfo.email,
      };
      console.log(data);
      let modifyResult = this.$store.dispatch("modify", data);
      console.log(modifyResult);
      if (modifyResult) {
        alert("수정을 완료하였습니다.");
      } else {
        alert("수정에 실패하였습니다.");
      }
    },
  },
};
</script>

<style scoped>
* {
  font-family: Cafe24SsurroundAir;
}
.modify-container {
  width: 50%;
  height: auto;
  margin: 20% auto;
}
.modify-container > h1 {
  position: absolute;
  left: 45%;
  top: 45%;
}
form input {
  width: 100%;
  height: 2rem;
  border-radius: 5px;
  border: none;
  font-size: 1rem;
  margin: 0 0 2rem;
  padding-left: 0.5rem;
}
/* 수정 버튼 */
.modify-button {
  cursor: pointer;
}
</style>
