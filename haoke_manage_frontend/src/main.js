import Vue from "vue";
import "./plugins/axios";
import App from "./App.vue";
import router from "./router";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import "./plugins/elements.js";
//导入全局样式表
import "./assets/css/global.css";
import axios from "axios";
Vue.config.productionTip = false;
//导入字体图标
import "./assets/fonts/iconfont.css";

// axios.defaults.baseURL = "https://www.liulongbin.top:8888/api/private/v1/";
axios.defaults.baseURL = "http://127.0.0.1:18080";
axios.interceptors.request.use(config => {
  //为请求头对象
  config.headers.Authorization = window.sessionStorage.getItem("token");
  return config;
});
Vue.prototype.$http = axios;
//过滤器


Vue.use(ElementUI);
new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
