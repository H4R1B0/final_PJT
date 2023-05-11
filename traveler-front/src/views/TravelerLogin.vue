<template>
  <div class="login-content">
    <h1>로그인</h1>
    <form v-on:submit.prevent="login">
      아이디
      <br />
      <input type="text" name="member_id" id="member_id" autofocus placeholder="아이디" v-model.trim="member.member_id" />
      <br />
      비밀번호
      <br />
      <input type="password" name="member_password" id="member_password" autofocus placeholder="비밀번호" v-model.trim="member.member_password" />
      <br />
      <input type="checkbox" v-model="member.save_id" />아이디 기억하기
      <br />
      <span>아직 회원이 아니신가요? </span>
      <router-link :to="{ name: 'join' }">회원가입 </router-link>
      <br />
      <input type="submit" value="로그인" />
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
    login() {
      console.log("로그인");
      // const url = "http://localhost/member/login";
    },
  },
};
</script>

<style></style>
