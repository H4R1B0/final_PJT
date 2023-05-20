import axios from "axios";
import store from "@/store";

const instance = axios.create({
  baseURL: `http://${window.location.hostname}/traveler`,
});
//axios request마다 header Authorization 헤더 추가
instance.interceptors.request.use((config) => {
  config.headers["Authorization"] = `Bearer ${store.state.token}`;
  console.log("config:", config);
  return config;
});
export default instance;
