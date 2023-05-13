<template>
  <li class="tour-list-item">
    <img class="item-image" :src="attraction.img" alt="관광지" />
    <ul>
      <li class="item-title">{{ attraction.title }}</li>
      <li class="item-addr">{{ attraction.addr }}</li>
    </ul>
  </li>
</template>

<script>
export default {
  props: {
    attraction: Object,
  },
};
</script>

<style>
.tour-list-item {
  display: flex;
  flex-basis: 30%;
  margin-bottom: 2em;
  margin-left: 10%;
}
.item-image {
  width: 10em;
  border-radius: 10px;
  margin-top: 0.5em;
  margin-right: 1em;
}
.item-title {
  font-weight: bold;
  width: 120px;
}
.item-addr {
  width: 100px;
}
</style>
