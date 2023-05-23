<template>
  <tr class="like-item" @click="goTourDetail">
    <td>
      <img class="item-heart" :src="attraction.interest ? require('@/assets/img/fill_heart.png') : require('@/assets/img/white_heart.png')" alt="empty_heart" @click.stop="setInterest" />
    </td>
    <td>{{ index }}</td>
    <td>{{ attraction.title }}</td>
  </tr>
</template>

<script>
import http from "@/util/http";
export default {
  data() {
    return {};
  },
  props: {
    attraction: Object,
    index: Number,
  },
  components: {},
  created() {},
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
            this.$emit("setInterest", this.attraction.contentId, !this.attraction.interest);
            // this.heartState = res.data;
          })
          .catch(() => {
            console.log("좋아요 문제 발생");
          });
      } else {
        alert("로그인이 필요합니다.");
      }
    },
  },
  watch: {},
};
</script>

<style>
.like-item {
  cursor: pointer;
}
.item-heart {
  width: 24px;
  height: 24px;
}
.like-item:hover {
  background-color: rgba(219, 231, 255, 0.3);
}
</style>
