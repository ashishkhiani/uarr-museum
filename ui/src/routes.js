import Vue from 'vue';
import VueRouter from 'vue-router';
import Article from "@/components/Article";
import App from "@/App";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'home',
        component: App
    },
    {
        path:'/incident/:incidentId',
        name: 'incident',
        components: {
            article: Article,
        },
        props: {
            article: true,
        }
    }
];

export default new VueRouter({
    routes,
    mode: 'history',
});
