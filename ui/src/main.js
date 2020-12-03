import Vue from 'vue';
import App from './App.vue';
import 'bootstrap';
import store from './store';
import router from './routes';
import mapboxgl from "mapbox-gl";
import { BootstrapVue } from 'bootstrap-vue';

mapboxgl.accessToken = 'pk.eyJ1IjoiYXNoaXNoazM2NSIsImEiOiJja2g3dDZnZDYwYnIyMnhtN2F5cHY0cWZsIn0.P1AMpS3LE51Khhbi2I-37Q';
Vue.config.productionTip = false

Vue.use(BootstrapVue)

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
