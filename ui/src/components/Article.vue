<template>
  <div v-if="incident" class="article">
    <button type="button" class="close float-lg-right text-white pr-4" aria-label="Close" @click="closeArticle">
      <span aria-hidden="true">&times;</span>
    </button>

    <h3 class="text-secondary">
      {{ incident.title }}
    </h3>

    <h6 class="text-muted">
      {{ incident.province }}, {{ incident.region }}, {{ formattedDate }}
    </h6>

    <span class="badge badge-pill badge-info">{{ incident.incidentLevel }}</span>
    &nbsp;&nbsp;
    <span class="badge badge-pill badge-info">{{ incident.system }}</span>

    <p />

    <div>
      <h6 class="text-info">
        Description
      </h6>
      <p class="text-white">
        {{ incident.summary }}
      </p>
      <p class="text-white">
        {{ incident.notes }}
      </p>
    </div>

    <div v-if="incident.incidentResponse">
      <h6 class="text-info">
        Response to incident
      </h6>
      <p class="text-white">
        {{ incident.incidentResponse }}
      </p>
    </div>

    <div v-if="incident.policies">
      <h6 class="text-info">
        Policies and Practices in use
      </h6>
      <p class="text-white">
        {{ incident.incidentResponse }}
      </p>
    </div>

    <div v-if="incident.additionalLinks.length > 0">
      <h6 class="text-info">
        Additional Resources
      </h6>
      <ul>
        <li v-for="link in incident.additionalLinks" :key="link" class="text-white">
          <a :href="link" class="text-white">{{ link }}</a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import moment from 'moment';

export default {
  name: "Article",
  props: {
    incidentId: {
      default: '0',
      type: String,
      required: false,
    }
  },
  computed: {
    formattedDate() {
      return moment(this.incident.date, 'MMDDYYYY').format('MMMM Do YYYY')
    },
    incident() {
      return this.$store.state.incidents.find((incident) => incident.id === this.incidentId);
    }
  },
  methods: {
    closeArticle() {
      this.$router.push({ name: 'home' });
    }
  },
}
</script>

<style scoped>
.article {
  height: calc(100vh - 250px);
  overflow-y: scroll;
}
</style>
