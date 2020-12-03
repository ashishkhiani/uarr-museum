<template>
  <Museum />
</template>

<script>
import Museum from "@/views/Museum";

export default {
  name: 'App',
  components: {
    Museum
  }
}
</script>

<style lang="scss">
@import '~mapbox-gl/dist/mapbox-gl.css';
@import '~bootstrap/dist/css/bootstrap.css';
@import '~bootstrap-vue/dist/bootstrap-vue.css';
@import '~animate.css';
</style>
