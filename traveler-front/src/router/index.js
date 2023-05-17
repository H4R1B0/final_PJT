import Vue from "vue";
import VueRouter from "vue-router";
// import TourMain from "@/views/tour/TourMain.vue";

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
  {
    path: "/tour",
    name: "tour",
    component: () => import("@/views/tour/TourMain.vue"),
    // component: TourMain,
  },
  {
    path: "/tour/detail",
    name: "tour-detail",
    component: () => import("@/views/tour/TourDetail.vue"),
    // component: TourMain,
  },
  {
    path: "/info-list",
    name: "info-list",
    component: () => import("@/views/board/InfoList.vue"),
  },
  {
    path: "/info-write",
    name: "info-write",
    component: () => import("@/views/board/InfoWrite.vue"),
  },
  {
    path: "/info-detail",
    name: "info-detail",
    component: () => import("@/views/board/InfoDetail.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
