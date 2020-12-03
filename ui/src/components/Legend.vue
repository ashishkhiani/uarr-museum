<template>
  <div class="pl-2 pt-2">
    <div @click="toggleShowCardBody">
      <svg width="20" height="20" viewBox="0 0 16 16" class="bi bi-info-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd" d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM8 5.5a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
      </svg>
    </div>

    <div v-if="showCardBody" class="card">
      <div class="card-body p-2">
        <p v-for="system in systems" :key="system">
          <MapMarker :color="systemColors[system]" :width="20" :height="20" />
          <small> {{ system }}</small>
        </p>
      </div>
    </div>
  </div>

</template>

<script>
import MapMarker from "@/components/MapMarker";
import { systems, systemColors } from "@/constants";

export default {
  name: "Legend",
  components: {
    MapMarker
  },
  data() {
    return {
      showCardBody: false,
      systems,
      systemColors,

    }
  },
  methods: {
    toggleShowCardBody() {
      this.showCardBody = !this.showCardBody;
    }
  }
}
</script>

<style scoped>

</style>
