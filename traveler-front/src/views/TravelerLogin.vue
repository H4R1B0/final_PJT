<template>
  <div class="login-content">
    <h1>로그인</h1>
    <br />
    <form v-on:submit.prevent="login">
      아이디
      <br />
      <input class="login-text" type="text" name="member_id" id="member_id" autofocus placeholder="아이디" v-model.trim="member.member_id" />
      <br />
      비밀번호
      <br />
      <input type="password" name="member_password" id="member_password" autofocus placeholder="비밀번호" v-model.trim="member.member_password" />
      <br />
      <!-- <input type="checkbox" v-model="member.save_id" />아이디 기억하기
      <br /> -->
      <br />
      <span>아직 회원이 아니신가요? </span>
      <router-link :to="{ name: 'join' }">회원가입 </router-link>
      <br />
      <span> 비밀번호를 잊으셨나요?</span>
      <router-link :to="{ name: 'member-forgot' }">비밀번호 찾기 </router-link>
      <br />

      <input class="login-button" type="submit" value="로그인" />
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      member: {
        member_id: "",
        member_password: "",
        save_id: false,
      },
    };
  },
  methods: {
    async login() {
      console.log("로그인");
      let loginResult = await this.$store.dispatch("login", this.member);
      console.log(loginResult);
      if (loginResult) {
        this.$router.push("/");
      } else {
        alert("아이디 또는 비밀번호를 다시 확인 해주세요.");
      }
      // const url = "http://localhost/member/login";
    },
  },
};
</script>

<style scoped>
* {
  font-family: Cafe24SsurroundAir;
}
.login-content {
  width: 20rem;
  height: auto;
  margin: 11rem auto 0;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 10px;
  padding: 2rem;
}
.login-content > h1 {
  text-align: center;
}
.login-content > form > * {
  margin: 0.5rem 0;
}
.login-content > form > input {
  width: 100%;
  height: 2rem;
  border-radius: 5px;
  border: none;
  font-size: 1rem;
  padding-left: 0.5rem;
}
.login-button {
  background-color: #b9cbeb;
  cursor: pointer;
}
</style>
