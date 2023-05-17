<template>
  <li class="tour-list-item" @click="goTourDetail">
    <img class="item-image" :src="attraction.img" alt="관광지" />
    <img class="item-heart" :src="heartState ? require('@/assets/img/fill_heart.png') : require('@/assets/img/empty_heart.png')" alt="empty_heart" @click.stop="setInterest" />
    <ul>
      <li class="item-title">{{ attraction.title }}</li>
      <li class="item-addr">{{ attraction.addr }}</li>
    </ul>
  </li>
</template>

<script>
import http from "@/util/http";
export default {
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

<style>
.tour-list-item {
  display: flex;
  flex-basis: 30%;
  margin-bottom: 2em;
  margin-left: 10%;
  cursor: pointer;
}
.item-image {
  width: 10em;
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
  width: 120px;
}
.item-addr {
  width: 100px;
}
</style>
