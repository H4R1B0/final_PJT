import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    keyword: "_",
    content: "_",
    code: 0,
  },
  getters: {
    keyword: (state) => {
      return state.keyword;
    },
    content: (state) => {
      return state.content;
    },
    code: (state) => {
      return state.code;
    },
    searchData: (state) => {
      let data = {
        keyword: state.keyword,
        content: state.content,
        code: state.code,
      };
      return data;
    },
  },
  mutations: {
    setKeyword: (state, keyword) => {
      state.keyword = keyword;
    },
    setContent: (state, content) => {
      state.content = content;
    },
    setCode: (state, code) => {
      state.code = code;
    },
  },
  actions: {
    setKeyword: ({ commit, state }, keyword) => {
      console.log(state);
      commit("setKeyword", keyword);
    },
    setContent: ({ commit, state }, content) => {
      console.log(state);
      commit("setContent", content);
    },
    setCode: ({ commit, state }, code) => {
      console.log(state);
      commit("setCode", code);
    },
  },
  modules: {},
});
