<template>
  <tr class="info-item">
    <td>{{ infoComponent.no }}</td>
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
  },
};
</script>

<style>
.info-item > td {
  padding: 7px 1px 5px 4px;
}
</style>
