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
    path: "/mypage",
    name: "mypage",
    component: () => import("@/views/member/MyPage.vue"),
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
  //공지사항 게시판
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
  {
    path: "/info-update",
    name: "info-update",
    component: () => import("@/views/board/InfoUpdate.vue"),
  },
  //후기 게시판
  {
    path: "/review-list",
    name: "review-list",
    component: () => import("@/views/board/ReviewList.vue"),
  },
  {
    path: "/review-write",
    name: "review-write",
    component: () => import("@/views/board/ReviewWrite.vue"),
  },
  {
    path: "/review-detail",
    name: "review-detail",
    component: () => import("@/views/board/ReviewDetail.vue"),
  },
  {
    path: "/review-update",
    name: "review-update",
    component: () => import("@/views/board/ReviewUpdate.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
