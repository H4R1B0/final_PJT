import axios from "axios";
import store from "@/store";

const instance = axios.create({
  baseURL: `http://${window.location.hostname}/traveler`,
});
//axios request마다 header Authorization 헤더 추가
instance.interceptors.request.use((config) => {
  //토큰이 없는 경우 그대로 전달
  if (`${store.state.token}` == null) return config;
  // //토큰 만료 확인
  // const currentTime = new Date().getTime() / 1000;
  console.log("현재 시간:", new Date().getTime());
  console.log("토큰 만료 시간:", `${store.state.exp}` * 1000);
  if (new Date().getTime() > `${store.state.exp}` * 1000) {
    console.log("토큰 만료");
    alert("세션이 만료되었습니다.");
    store.commit("CLEAR_MEMBER");
  } else {
    console.log("토큰 유효");
  }

  //헤더에 토큰 담기
  config.headers["Authorization"] = `Bearer ${store.state.token}`;
  console.log("config:", config);
  return config;
});
export default instance;
