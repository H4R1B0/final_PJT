<template>
  <tr class="info-item" @click="goInfoDetail">
    <td>{{ (page - 1) * 10 + idx + 1 }}</td>
    <td>{{ infoComponent.title }}</td>
    <td>{{ infoComponent.writer }}</td>
    <td>{{ infoComponent.readCount }}</td>
    <td>{{ infoComponent.writeDate }}</td>
  </tr>
</template>

<script>
export default {
  props: {
    infoComponent: Object,
    idx: {
      type: Number,
    },
    page: {
      type: Number,
    },
  },
  methods: {
    goInfoDetail() {
      this.$router.push({ name: "info-detail", query: { no: this.infoComponent.no } });
    },
  },
};
</script>

<style>
.info-item > td {
  padding: 7px 1px 5px 4px;
}
</style>
