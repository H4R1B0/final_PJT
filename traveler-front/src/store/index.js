import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http";
import jwtDecode from "jwt-decode";
import { createVuexPersistedState } from "vue-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createVuexPersistedState()],
  state: {
    //검색 데이터
    SearchData: {
      keyword: "title",
      content: "",
      code: 0,
    },
    //jwt 토큰
    token: null,
    exp: "",
    //사용자 정보
    memberInfo: {},
    //회전정보
    deg: 0,
  },
  getters: {
    //검색 데이터 getter
    searchData: (state) => {
      return state.SearchData;
    },
  },
  mutations: {
    //검색 데이터 세팅
    setSearchData: (state, SearchData) => {
      state.SearchData = SearchData;
    },
    //검색 데이터 초기화
    initSearchData: (state) => {
      state.SearchData = {
        keyword: "title",
        content: "",
        code: 0,
      };
    },
    SET_MEMBER_INFO(state, payload) {
      console.log(payload);
      state.memberInfo = payload;
    },
    SET_TOKEN(state, payload) {
      console.log(payload);
      state.token = payload.token;
    },
    SET_EXP(state, payload) {
      console.log(payload);
      state.exp = payload.exp;
    },
    CLEAR_MEMBER(state) {
      state.memberInfo = {};
      state.token = null;
    },
    SET_CODE(state, payload) {
      console.log(payload);
      console.log(payload.code);
      state.SearchData.code = payload.code;
    },
    SET_DEG(state) {
      state.deg += 360;
    },
  },
  actions: {
    //검색 데이터 commit
    setSearchData: ({ commit, state }, SearchData) => {
      console.log(state);
      commit("setSearchData", SearchData);
    },
    //검색 데이터 초기화 commit
    initSearchData: ({ commit, state }) => {
      console.log(state);
      commit("initSearchData");
    },
    // loginInfo = {member_id:"", member_password:""}
    login(context, loginInfo) {
      //아이디 패스워드 정보 가지고 로그인
      //로그인 성공시 userInfo, token 정보 세팅
      http
        .post("/member/login", loginInfo)
        .then((res) => {
          console.log("로그인 응답 데이터:", res.data);
          const token = res.data.token;
          context.commit("SET_TOKEN", { token });

          //token decode
          const decodeToken = jwtDecode(token);
          console.log("decode token:", decodeToken);
          context.commit("SET_MEMBER_INFO", {
            member_id: decodeToken.member_id,
            member_name: decodeToken.member_name,
            email: decodeToken.email,
          });
          console.log(this.state.memberInfo);
          //만료 기간 설정
          context.commit("SET_EXP", { exp: decodeToken.exp });
          // this.$router.push("/");
          return true;
        })
        .catch(() => {
          console.log("로그인 실패");
          return false;
          // alert("아이디 또는 비밀번호를 다시 확인 해주세요.");
        });
    },
    modify(context, modifyInfo) {
      http
        .put("/member", modifyInfo)
        .then((res) => {
          //토큰 재발행
          const token = res.data.token;
          context.commit("SET_TOKEN", { token });

          //token decode
          const decodeToken = jwtDecode(token);
          console.log("decode token:", decodeToken);
          context.commit("SET_MEMBER_INFO", {
            member_id: decodeToken.member_id,
            member_name: decodeToken.member_name,
            email: decodeToken.email,
          });
          console.log(this.state.memberInfo);
          //만료 기간 설정
          context.commit("SET_EXP", { exp: decodeToken.exp });
          return true;
        })
        .catch(() => {
          console.log("수정 실패");
          return false;
        });
    },
  },
  modules: {},
});
