<template>
  <div class="offset">
    <table class="table table-bordered"></table>
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item">
          <button type="button" class="page-link" v-if="startPage >= 10" @click="setPrev">Previous</button>
        </li>
        <li class="page-item">
          <button type="button" class="page-link" v-for="pageNumber in pages.slice(startPage - 1, endPage)" :key="pageNumber" @click="setPage(pageNumber)" v-bind:disabled="pageNumber == page">
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
// import axios from "axios";
export default {
  created() {
    console.log("총 개수", this.totalCount);
  },
  props: {
    totalCount: Number,
    page: Number,
  },
  data() {
    return {
      // page: 1,
      perPage: 10,
      pages: [],
      startPage: 1,
      endPage: 10,
    };
  },
  methods: {
    setPrev() {
      this.startPage -= this.perPage;
      this.endPage = this.startPage + this.perPage;
      // this.page = this.startPage;
      this.$emit("setPage", this.startPage);
    },
    setNext() {
      this.startPage += this.perPage;
      this.endPage = this.startPage + this.perPage - 1;
      // this.page = this.startPage;
      this.$emit("setPage", this.startPage);
    },
    setPage(value) {
      console.log("페이지 변경", value);
      this.$emit("setPage", value);
    },
    setPages() {
      this.pages = [];
      let numberOfPages = Math.floor((this.totalCount + this.perPage - 1) / this.perPage);
      console.log("전체 페이지:", numberOfPages);
      for (let index = this.startPage; index < this.startPage + numberOfPages; index++) {
        // console.log(index);
        this.pages.push(index);
      }
    },
  },
  computed: {},
  watch: {
    totalCount() {
      this.$emit("setPage", 1);
      this.startPage = 1;
      this.endPage = 10;
      this.setPages();
    },
  },
  filters: {},
};
</script>

<style>
.pagination {
  display: flex;
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
  color: rgb(97, 118, 161);
  font-weight: 500;
}
button:disabled {
  background-color: rgb(185, 203, 235);
}
.page-link {
  position: relative;
  padding: 0.3rem 0.3rem;
  /* margin-left: -1px; */
  line-height: 1.25;
  background-color: #f6f6f7;
  border: 1px solid #dee2e6;
  border-radius: 15px;
  margin-right: 0.3em;
}
.page-link:not(:disabled) {
  cursor: pointer;
}
.offset {
  width: auto !important;
  display: flex;
  justify-content: center;
}
</style>
