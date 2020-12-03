import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';
import qs from 'qs';

Vue.use(Vuex)

const initialState = {
  // incidents: [],
  incidents: [],
  loading: false,
  incidentError: false,
};

// Actions
export const GET_CONTENT = 'GET_CONTENT';

// Mutations
export const RESPONSE_SUCCESS = 'RESPONSE_SUCCESS';
export const RESPONSE_ERROR = 'RESPONSE_ERROR';
export const UPDATE_LOADING_STATUS = 'UPDATE_LOADING_STATUS'


const actions = {
  [GET_CONTENT]({ commit }, filterParams  ) {
    commit(UPDATE_LOADING_STATUS, true);
    axios
      .get('http://localhost:8086/uarr/museum/incidents', {
        params: filterParams,
        paramsSerializer: (params) => {
          return qs.stringify(params, { arrayFormat: 'repeat' })
        },
      })
      .then(response => {
        commit(RESPONSE_SUCCESS, response);
        commit(UPDATE_LOADING_STATUS, false);
      })
      .catch(error => {
        commit(RESPONSE_ERROR);
        commit(UPDATE_LOADING_STATUS, false);
      })
  },
};

const mutations = {
  [RESPONSE_SUCCESS](state, payload) {
    if (payload && payload.data) {
      state.incidents = payload.data;
    }
    state.incidentError = false;
  },
  [RESPONSE_ERROR](state) {
    state.incidentError = true;
  },
  [UPDATE_LOADING_STATUS](state, value) {
    state.loading = value;
  }
};

export default new Vuex.Store({
  state: initialState,
  actions,
  mutations,
});
