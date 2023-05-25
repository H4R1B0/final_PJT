<template>
  <div class="find-content">
    <h1>비밀번호 찾기</h1>
    <br />
    <form v-on:submit.prevent="findPwd">
      아이디를 입력하세요
      <br />
      <input type="text" name="member_id" id="member_id" autofocus placeholder="id" v-model.trim="member.member_id" />
      <br />
      <input class="find-button" type="submit" value="확인" />
    </form>
  </div>
</template>

<script>
import http from "@/util/http";
export default {
  data() {
    return {
      member: {
        member_id: "",
        member_password: "",
      },
    };
  },
  methods: {
    findPwd() {
      http
        .post(`/member/tmpPwd/${this.member.member_id}`)
        .then((response) => {
          if (response.status == 200) {
            alert("메일이 발송되었습니다.");
            this.$router.push({ name: "login" });
          }
        })
        .catch(() => {
          alert("존재하지 않는 아이디입니다.");
        });
    },
  },
};
</script>

<style scoped>
* {
  font-family: Cafe24SsurroundAir;
}
.find-content {
  width: 20rem;
  height: auto;
  margin: 11rem auto 0;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 10px;
  padding: 2rem;
}
.find-content > h1 {
  text-align: center;
}
.find-content > form > * {
  margin: 0.5rem 0;
}
.find-content > form > input {
  width: 100%;
  height: 2rem;
  border-radius: 5px;
  border: none;
  font-size: 1rem;
  padding-left: 0.5rem;
}
.find-button {
  background-color: #b9cbeb;
  cursor: pointer;
}
</style>
