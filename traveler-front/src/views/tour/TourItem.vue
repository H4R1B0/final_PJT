<template>
  <li class="tour-list-item" @click="goTourDetail">
    <div class="item-set">
      <!-- <img class="item-image" :src="attraction.img == '' ? 'https://cdn1.iconfinder.com/data/icons/carbon-design-system-vol-6/32/no-image-512.png' : attraction.img" alt="관광지" /> -->
      <vue-img-loader class="item-image" :src="attraction.img == '' ? 'https://cdn1.iconfinder.com/data/icons/carbon-design-system-vol-6/32/no-image-512.png' : attraction.img" alt="관광지">
        <h1>로딩중...</h1>
      </vue-img-loader>

      <img class="item-heart" :src="heartState ? require('@/assets/img/fill_heart.png') : require('@/assets/img/white_heart.png')" alt="empty_heart" @click.stop="setInterest" />
    </div>
    <ul>
      <li class="item-title">{{ attraction.title }}</li>
      <li class="item-addr">{{ attraction.addr }}</li>
    </ul>
  </li>
</template>

<script>
import VueImgLoader from "vue-img-loader";
import http from "@/util/http";
export default {
  components: {
    VueImgLoader,
  },
  data() {
    return {
      heartState: false,
    };
  },
  props: {
    attraction: Object,
    // heartState: Boolean,
  },
  created() {
    this.heartState = this.attraction.interest;
  },
  methods: {
    goTourDetail() {
      console.log("상세히 보기 이동");
      this.$router.push({ name: "tour-detail", query: { content_id: this.attraction.contentId } });
    },
    setInterest() {
      console.log("좋아요 버튼 클릭");
      if (this.$store.state.memberInfo != null && this.$store.state.token != null) {
        http
          .put(`/tour/${this.$store.state.memberInfo.member_id}/${this.attraction.contentId}`)
          .then((res) => {
            console.log("좋아요 버튼 적용");
            console.log(res.data);
            this.heartState = res.data;
          })
          .catch(() => {
            console.log("좋아요 문제 발생");
          });
      } else {
        alert("로그인이 필요합니다.");
      }
    },
  },
  computed: {},
  watch: {},
};
</script>

<style scoped>
li {
  list-style: none;
}
.tour-list-item {
  display: flex;
  flex-basis: 45%;
  margin-bottom: 2em;
  padding-left: 7rem;
  /* margin-left: 10%; */
  cursor: pointer;
}
.item-set {
  position: relative;
}
.item-set > .item-heart {
  position: absolute;
  right: 10%;
  bottom: 5%;
}
.item-image {
  width: 10rem;
  height: 7rem;
  border-radius: 10px;
  margin-top: 0.5em;
  margin-right: 1em;
}
.item-heart {
  width: 24px;
  height: 24px;
}
.item-title {
  font-weight: bold;
  width: 12rem;
  padding-top: 2rem;
}
.item-addr {
  width: 14rem;
}
</style>
