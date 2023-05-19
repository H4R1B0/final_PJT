<template>
  <form action="" class="modify-container">
    <h3>이름</h3>
    <input type="text" v-model="memberInfo.member_name" />
    <h3>이메일</h3>
    <input type="text" v-model="memberInfo.email" />
    <input type="button" value="수정하기" @click="modifyInfo" />
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
.modify-container {
  width: 50%;
  height: auto;
  /* margin: 20% auto; */
}
form input {
  width: 100%;
  height: 2rem;
  border-radius: 5px;
  border: none;
  font-size: 1rem;
  margin: 0 0 2rem;
}
</style>
