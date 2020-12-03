<template>
  <svg :width="width" :height="height" viewBox="0 0 16 16" class="bi bi-geo-alt-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg" :color="color">
    <path fill-rule="evenodd" d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10zm0-7a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
  </svg>
</template>

<script>
export default {
  name: "MapMarker",
  props: {
    color: {
      type: String,
      required: false,
      default: "black"
    },
    width: {
      type: Number,
      required: false,
      default: 30,
    },
    height: {
      type: Number,
      required: false,
      default: 30,
    }
  }
}
</script>

<style scoped>

</style>
