<template>
  <div class="offset">
    <table class="table table-bordered"></table>
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item">
          <button type="button" class="page-link" v-if="startPage >= 10" @click="setPrev">Previous</button>
        </li>
        <li class="page-item">
          <button type="button" class="page-link" v-for="pageNumber in pages.slice(startPage, endPage)" :key="pageNumber" @click="page = pageNumber" v-bind:disabled="pageNumber == page">
            {{ pageNumber }}
          </button>
        </li>
        <li class="page-item">
          <button type="button" @click="setNext" v-if="endPage < pages.length" class="page-link">Next</button>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import axios from "axios";
export default {
  created() {
    let searchData = this.$store.getters.searchData;
    axios.get(`http://localhost/traveler/tour/total?keyword=${searchData.keyword}&content=${searchData.content}&code=${searchData.code}`).then((res) => {
      console.log("Pagination", res.data);
      this.totalCount = res.data;
    });
  },
  data() {
    return {
      // posts: [""],
      page: 1,
      perPage: 10,
      pages: [],
      startPage: 0,
      endPage: 10,
      totalCount: 0,
    };
  },
  methods: {
    setTotalCount() {
      let searchData = this.$store.getters.searchData;
      console.log("searchData:", searchData);
      axios.get(`http://localhost/traveler/tour/total?keyword=${searchData.keyword}&content=${searchData.content}&code=${searchData.code}`).then((res) => {
        console.log("Pagination", res.data);
        this.totalCount = res.data;
        // return res.data;
      });
      this.page = 1;
      this.startPage = 0;
      this.endPage = 10;
    },
    setPages() {
      this.pages = [];
      let numberOfPages = (this.totalCount + this.perPage - 1) / this.perPage;
      console.log("전체 페이지:", numberOfPages);
      for (let index = this.startPage + 1; index < this.startPage + numberOfPages; index++) {
        this.pages.push(index);
      }
    },
    setPrev() {
      this.startPage -= this.perPage;
      this.endPage = this.startPage + this.perPage;
      this.page = this.startPage + 1;
    },
    setNext() {
      this.startPage += this.perPage;
      this.endPage = this.startPage + this.perPage;
      this.page = this.startPage + 1;
    },
  },
  computed: {
    // displayedPosts() {
    //   return this.paginate(this.posts);
    // },
    checkSearchData() {
      let searchData = this.$store.getters.searchData;
      let data = {
        keyword: searchData.keyword,
        content: searchData.content,
        code: searchData.code,
      };
      return data;
    },
  },
  watch: {
    // posts() {
    //   this.setPages();
    // },
    checkSearchData() {
      // this.keyword = this.checkSearchData.keyword;
      this.setTotalCount();
    },
    totalCount() {
      this.setPages();
    },
    page() {
      // let searchData = this.$store.getters.searchData;
      // let data = {
      //   keyword: searchData.keyword,
      //   content: searchData.content,
      //   page: this.page,
      // };
      console.log("페이지 변경");
      console.log("현재 페이지", this.page);
      // this.$store.commit("setSearchData", data);
      this.$store.commit("setPage", this.page);
      console.log(this.$store.getters.searchData.page);
      console.log(this.$store.getters.page);
    },
  },
  filters: {
    // trimWords(value) {
    //   return value.split(" ").splice(0, 20).join(" ") + "...";
    // },
  },
};
</script>

<style>
.pagination {
  display: flex;
  padding-left: 0;
  border-radius: 10px;
}
*,
::after,
::before {
  box-sizing: border-box;
}
button.page-link {
  display: inline-block;
  font-size: 20px;
  color: #29b3ed;
  font-weight: 500;
}
button:disabled {
  background-color: #e9ecef;
}
.page-link {
  position: relative;
  padding: 0.5rem 0.75rem;
  margin-left: -1px;
  line-height: 1.25;
  background-color: #fff;
  border: 1px solid #dee2e6;
}
.page-link:not(:disabled) {
  cursor: pointer;
}
.offset {
  width: 1000px !important;
  /* height: 100px; */
  margin: 20px auto;
}
</style>
