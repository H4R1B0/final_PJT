import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: () => import("@/views/TravelerMain.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/TravelerLogin.vue"),
  },
  {
    path: "/join",
    name: "join",
    component: () => import("@/views/TravelerJoin.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
