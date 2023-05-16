<template>
  <div class="join-content">
    <h1>회원가입</h1>
    <form v-on:submit.prevent="join">
      아이디
      <br />
      <input type="text" name="member_id" id="member_id" autofocus placeholder="아이디" v-model.trim="member.member_id" @blur="idCheck" />
      <p :style="{ color: count == 1 ? 'red' : 'blue' }">{{ msg }}</p>
      이름
      <br />
      <input type="text" name="member_name" id="member_name" autofocus placeholder="이름" v-model.trim="member.member_name" />
      <br />
      이메일
      <br />
      <input type="email" name="email" id="email" autofocus placeholder="이메일" v-model.trim="member.email" />
      <br />
      비밀번호
      <br />
      <input type="password" name="member_password" id="member_password" autofocus placeholder="비밀번호" v-model.trim="member.member_password" />
      <br />
      비밀번호 확인
      <br />
      <input type="password" name="member_password_confirm" id="member_password_confirm" autofocus placeholder="비밀번호 확인" />
      <br />
      <input type="submit" value="회원가입" />
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      member: {
        member_id: "",
        member_name: "",
        member_password: "",
        email: "",
      },
      msg: "",
      count: -1,
    };
  },
  methods: {
    join() {
      console.log("회원가입");
      axios
        .post("http://localhost/traveler/member", this.member)
        .then((res) => {
          console.log(res.data);
          this.$router.push("/login");
        })
        .catch((err) => {
          console.log("회원가입 실패", err);
        });
      // const url = "http://localhost/member/login";
    },
    async idCheck() {
      console.log("아이디 체크");
      this.count = (await axios.get(`http://localhost/traveler/member/${this.member.member_id}`)).data;
      // console.log("아이디 중복 개수:", count);
      if (this.count == 0) {
        this.msg = "사용 가능한 아이디 입니다.";
      } else {
        this.msg = "이미 사용중인 아이디 입니다.";
      }
    },
  },
};
</script>

<style></style>
