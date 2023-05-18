<template>
  <div class="join-content">
    <h1>회원가입</h1>
    <form v-on:submit.prevent="join">
      아이디
      <br />
      <input required type="text" name="member_id" id="member_id" autofocus placeholder="아이디" v-model.trim="member.member_id" @blur="idCheck" />
      <p :style="{ color: count == 0 ? 'blue' : 'red' }">{{ msg }}</p>
      이름
      <br />
      <input required type="text" name="member_name" id="member_name" autofocus placeholder="이름" v-model.trim="member.member_name" />
      <br />
      이메일
      <br />
      <input required type="email" name="email" id="email" autofocus placeholder="이메일" v-model.trim="member.email" />
      <br />
      비밀번호
      <br />
      <input required type="password" name="member_password" id="member_password" autofocus placeholder="비밀번호" v-model.trim="member.member_password" />
      <br />
      비밀번호 확인
      <br />
      <input required type="password" name="member_password_confirm" id="member_password_confirm" autofocus placeholder="비밀번호 확인" />
      <br />
      <input class="join-button" type="submit" value="회원가입" />
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
      alertMsg: "",
      count: -1,
    };
  },
  methods: {
    join() {
      console.log("회원가입 클릭");
      if (!this.isValidId() || !this.isValidPwd() || !this.isValidName()) {
        alert(this.alertMsg);
        return;
      }

      axios
        .post("http://localhost/traveler/member", this.member)
        .then((res) => {
          console.log(res.data);
          alert("회원가입이 완료되었습니다.");
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
      if (this.member.member_id.length < 6 || this.member.member_id.length > 20) {
        this.msg = "6~20자의 영문 소문자, 숫자만 사용 가능합니다.";
        this.count = -1;
      } else if (this.count == 0) {
        this.msg = "사용 가능한 아이디 입니다.";
      } else {
        this.msg = "이미 사용중인 아이디 입니다.";
      }
    },
    isValidId() {
      const id_reg = /^[a-z0-9][a-z0-9]{5,19}$/;
      const id = document.getElementById("member_id").value;
      if (id == "") {
        this.alertMsg = "아이디를 입력하세요.";
        return false;
      }
      if (id_reg.test(id) != true) {
        this.alertMsg = "6~20자의 영문 소문자, 숫자만 사용 가능합니다.";
        return false;
      }
      return true;
    },
    isValidPwd() {
      //영문, 숫자 최소 한개씩 포함하여 8글자 이상
      const pwd_reg = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$/;
      const pwd = document.getElementById("member_password").value;
      const pwd_confirm = document.getElementById("member_password_confirm").value;
      if (pwd == "") {
        this.alertMsg = "비밀번호를 입력하세요.";
        return false;
      }
      if (pwd_reg.test(pwd) != true) {
        this.alertMsg = "비밀번호 8글자 이상의 영문, 숫자를 포함하세요.";
        console.log(pwd);
        return false;
      }
      if (pwd != pwd_confirm) {
        this.alertMsg = "비밀번호를 확인하세요.";
        return false;
      }
      return true;
    },
    isValidName() {
      const name_reg = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
      const name = document.getElementById("member_name").value;
      if (name == "") {
        this.alertMsg = "이름을 입력하세요.";
        return false;
      }
      if (name_reg.test(name) != true) {
        this.alertMsg = "이름을 확인하세요";
        return false;
      }
      return true;
    },
    isValidEmail() {
      const email_reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      const email = document.getElementById("email").value;
      if (email == "") {
        this.alertMsg = "이메일을 입력하세요.";
        return false;
      }
      if (email_reg.test(email) != true) {
        this.alertMsg = "이메일을 확인하세요";
        return false;
      }
      return true;
    },
  },
};
</script>

<style>
.join-content {
  width: 20rem;
  height: auto;
  margin: 3rem auto 0;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 10px;
  padding: 2rem;
}
.join-content > h1 {
  text-align: center;
}
.join-content > form > * {
  margin: 0.5rem 0;
}
.join-content > form > input {
  width: 20rem;
  height: 2rem;
  border-radius: 5px;
  border: none;
  font-size: 1rem;
}
.join-button {
  background-color: #b9cbeb;
}
</style>
