import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    //검색 데이터
    SearchData: {
      keyword: "title",
      content: "",
      code: 0,
      page: 1,
    },
  },
  getters: {
    //검색 데이터 getter
    searchData: (state) => {
      return state.SearchData;
    },
    //검색 page getter
    page: (state) => {
      return state.SearchData.page;
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
        page: 1,
      };
    },
    setPage: (state, page) => {
      state.SearchData.page = page;
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
    //검색 페이지 세팅
    setPage: ({ commit, state }, page) => {
      console.log(state);
      commit("setPage", page);
    },
  },
  modules: {},
});
