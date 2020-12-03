<template>
  <div>
    <div class="row">
      <b-dropdown id="system-dropdown" text="Systems" class="m-md-2 pl-2">
        <b-form-group id="system-group">
          <b-form-checkbox-group v-for="(item, index) in allSystems" :key="`systemCheckbox-${index}`" v-model="filterParams.system" class="form-check">
            <b-form-checkbox :id="`systemCheckbox-${index}`" :value="item">
              {{ item }}
            </b-form-checkbox>
          </b-form-checkbox-group>
        </b-form-group>
      </b-dropdown>

      <!-- Date Filter   -->
      <b-dropdown id="date-dropdown" text="Dates" class="m-md-2">
        <label for="fromDateCalendar" class="date-label">From Date Calendar:</label>
        <b-calendar id="fromDateCalendar" @context="onContext" locale="en-CA" label-help="" v-model="filterParams.fromDate">
          <div class="d-flex" dir="ltr">
            <b-button
              size="sm"
              variant="outline-danger"
              v-if="filterParams.fromDate"
              @click="fromDateClearDate"
            >
              Clear date
            </b-button>
          </div>
        </b-calendar>

        <b-dropdown-divider></b-dropdown-divider>
        <div class="divider"></div>
        <b-dropdown-divider></b-dropdown-divider>

        <label for="toDateCalendar" class="date-label">To Date Calendar:</label>
        <b-calendar id="toDateCalendar" @context="onContext" locale="en-CA" label-help="" v-model="filterParams.toDate">
          <div class="d-flex" dir="ltr">
            <b-button
              size="sm"
              variant="outline-danger"
              v-if="filterParams.toDate"
              @click="toDateClearDate"
            >
              Clear date
            </b-button>
            <b-button
              size="sm"
              variant="outline-primary"
              class="ml-auto"
              @click="toDateSetToday"
            >
              Set Today
            </b-button>
          </div>
        </b-calendar>
      </b-dropdown>

      <!-- Province Filter   -->
      <b-dropdown id="province-dropdown" text="Location" class="m-md-2">
        <b-form-group id="province-group">
          <b-form-checkbox-group v-for="(value, key, index) in provinces" :key="`provinceCheckbox-${index}`" v-model="filterParams.province" class="form-check">
            <b-form-checkbox :id="`provinceCheckbox-${index}`" :value="value">
              {{ key }}
            </b-form-checkbox>
          </b-form-checkbox-group>
        </b-form-group>
      </b-dropdown>

      <!-- Incident Level Filter   -->
      <b-dropdown id="incident-level-dropdown" text="Incident Levels" class="m-md-2">
        <b-form-group id="incident-level-group">
          <b-form-radio-group v-for="(item, index) in incidentLevels" :key="`incidentLevelCheckbox-${index}`" v-model="filterParams.incidentLevel" class="form-check">
            <b-form-radio :id="`incidentLevelCheckbox-${index}`" :value="item">
              {{ item }}
            </b-form-radio>
          </b-form-radio-group>
        </b-form-group>
      </b-dropdown>

      <button type="submit" class="btn btn-info m-2" @click="onApplyFilter">
        Apply Filters
      </button>
      <button class="btn btn-white m-2" @click="onReset">
        Clear Filters
      </button>
    </div>
  </div>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "Filters",
  data() {
    return {
      allSystems: ['Community', 'Education', 'Employment', 'Fire Department', 'Health Care', 'Housing', 'Law & Justice', 'Police', 'Politics', 'Transportation'],
      incidentLevels: ['Macro', 'Mezzo', 'Micro'],
      provinces: {
        'ON': 'Ontario',
        'QC': 'Quebec',
        'NS': 'Nova Scotia',
        'NB': 'New Brunswick',
        'MB': 'Manitoba',
        'BC': 'British Columbia',
        'PE': 'Prince Edward Island',
        'SK': 'Saskatchewan',
        'AB': 'Alberta',
        'NL': 'Newfoundland and Labrador',
        'NU': 'Nunavut',
        'YT': 'Yukon',
        'NT': 'Northwest Territories',
      },
      filterParams: {
        system: [],
        province: [],
        incidentLevel: '',
        fromDate: '',
        toDate: '',
      },
      context: null
    }
  },
  methods: {
    ...mapActions({
      loadIncidents: 'GET_CONTENT'
    }),
    onApplyFilter() {
      this.loadIncidents(this.filterParams);
    },
    onReset(event) {
      event.preventDefault();
      this.filterParams.system = [];
      this.filterParams.province = [];
      this.filterParams.incidentLevel = '';
      this.filterParams.fromDate = '';
      this.filterParams.toDate = '';
    },
    onContext(ctx) {
      this.context = ctx
    },
    fromDateClearDate() {
      this.filterParams.fromDate = ''
    },
    toDateSetToday() {
      const now = new Date()
      this.filterParams.toDate = new Date(now.getFullYear(), now.getMonth(), now.getDate())
    },
    toDateClearDate(event) {
      event.preventDefault();
      this.filterParams.toDate = ''
    }
  }
}
</script>

<style scoped lang="scss">
.date-label {
  font-weight: bold;
  font-size: large;
}
.divider {
  height: 10px;
  background-color: $uarr-secondary;
}
</style>
