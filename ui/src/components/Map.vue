<template>
  <div>
    <Filters />
    <Legend class="position-absolute" style="z-index: 1"/>
    <div id="map" />

    <!--These will be attached to the map-->
    <div v-for="incident in incidents" :key="`mapMarker_${incident.id}`" :ref="`mapMarker_${incident.id}`" @click="onMarkerClick(incident)">
      <MapMarker :color="systemColors[incident.system]" />
    </div>
  </div>
</template>

<script>
import { Map, Marker, Popup } from "mapbox-gl";
import { mapState, mapActions } from 'vuex';
import MapMarker from "@/components/MapMarker";
import moment from 'moment';
import Filters from "@/components/Filters";
import Legend from "@/components/Legend";
import { systemColors } from "@/constants";

const LONGITUDE_OFFSET = 0.035;

export default {
  name: "Map",
  components: {
    Legend,
    Filters,
    MapMarker,
  },
  data() {
    return {
      map: null,
      systemColors,
    }
  },
  computed: {
    ...mapState({
      incidents: state => state.incidents,
      incidentsLoaded: state => !state.loading,
    })
  },
  watch: {
    incidents() { //whenever the state object for incidents changes, the markers will be re-plotted
      this.$nextTick(() => {
        this.plotMarkers();
      });
    },
    incidentsLoaded(value) {
      if (value && this.map === null) {
        if (this.$route.params.incidentId) {
          const incident = this.incidents.find((incident) => incident.id === this.$route.params.incidentId);
          if (incident) {
            this.initializeMap([incident.longitude + LONGITUDE_OFFSET, incident.latitude], 13);
            return;
          }
        }

        this.initializeMap([-102.196113, 58.464570], 3);
      }
    }
  },
  async created() {
    await this.loadIncidents();
  },
  methods: {
    ...mapActions({
      loadIncidents: 'GET_CONTENT'
    }),
    initializeMap(center, zoom) {
      this.map = new Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/outdoors-v11',
        center,
        zoom,
        maxBounds: [ [-179.715712, 25.627298], [29.577531, 84.667725] ]
      });
    },
    plotMarkers() {
      this.incidents.forEach((incident) => {
        const popup = new Popup({
          offset: 25,
          closeButton: false,
        }).setHTML(this.popupHtml(incident));

        new Marker(this.$refs[`mapMarker_${incident.id}`][0])
            .setLngLat([incident.longitude, incident.latitude])
            .setPopup(popup)
            .addTo(this.map);
      });
    },
    onMarkerClick(incident) {
      // center map at pin
      this.map.flyTo({
        center: [incident.longitude + LONGITUDE_OFFSET, incident.latitude],
        zoom: 13,
        bearing: 0,
        speed: 1,
        curve: 1
      });

      this.$emit('toggle-article', incident);
    },
    popupHtml(incident) {
      return `
          <div class="p-1">
            <h5 class="card-title">${incident.title}</h5>
            <h6 class="card-subtitle mb-2 text-muted">${moment(incident.date, 'MMDDYYYY').format('MMMM Do YYYY')}</h6>
            <span class="badge badge-pill badge-info">${incident.system}</span>
            <p>${incident.address}</p>
          </div>
      `
    },
  }
}
</script>

<style scoped>
#map {
  height: calc(100vh - 250px);
}
</style>
