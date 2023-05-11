<template>
  <div class="detail_block">
    <h1 class="detail_title">관광 정보 조회</h1>
    <item />
  </div>
</template>

<script>
import item from "@/views/tour/TourItem.vue";
export default {
  components: {
    item,
  },
};
</script>

<style>
.detail_title {
  padding: 4rem 0rem 0rem 5rem;
}
/* 관광지 조회 페이지의 뒤에 흰 배경 */
.detail_block {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 20px;
  width: 55%;
  height: 1000px;
  margin: 0 auto;
}
</style>
