<template>
  <tr class="review-item" @click="goInfoDetail">
    <td>{{ (page - 1) * 10 + idx + 1 }}</td>
    <td>{{ reviewComponent.title }}</td>
    <td>{{ reviewComponent.writer }}</td>
    <td>{{ reviewComponent.readCount }}</td>
    <td>{{ reviewComponent.writeDate }}</td>
  </tr>
</template>

<script>
export default {
  props: {
    reviewComponent: Object,
    idx: {
      type: Number,
    },
    page: {
      type: Number,
    },
  },
  methods: {
    goInfoDetail() {
      this.$router.push({ name: "review-detail", query: { no: this.reviewComponent.no } });
    },
  },
};
</script>

<style scoped>
.review-item > td {
  padding: 7px 1px 5px 4px;
}
</style>
