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
export default {
  data() {
    return {
      posts: [""],
      page: 1,
      perPage: 10,
      pages: [],
      startPage: 0,
      endPage: 10,
    };
  },
  methods: {
    getPosts() {
      // let data = [];
      for (let i = 0; i < 500; i++) {
        this.posts.push({ first: "John", last: "Doe", suffix: "#" + (i + 1) });
      }
    },
    setPages() {
      let numberOfPages = (this.posts.length + this.perPage - 1) / this.perPage;
      for (let index = this.startPage + 1; index < this.startPage + numberOfPages; index++) {
        this.pages.push(index);
      }
    },
    paginate(posts) {
      let page = this.page;
      let perPage = this.perPage;
      let from = page * perPage - perPage;
      let to = page * perPage;
      return posts.slice(from + 1, to + 1);
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
    displayedPosts() {
      return this.paginate(this.posts);
    },
  },
  watch: {
    posts() {
      this.setPages();
    },
  },
  created() {
    this.getPosts();
  },
  filters: {
    trimWords(value) {
      return value.split(" ").splice(0, 20).join(" ") + "...";
    },
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
