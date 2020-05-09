import Vue from "vue";
import Router from "vue-router";
import Login from "./components/Login.vue";
import Home from "./components/Home.vue";
import Welcome from "./components/Welcome.vue";
import User from "./components/user/User.vue";
import House from "./components/house/House.vue";
//房源添加
import Add from "./components/house/Add.vue";
import Inspection from "./components/house/Inspection.vue";
Vue.use(Router);

const router = new Router({
  routes: [
    { path: "/", redirect: "/login" },
    { path: "/login", component: Login },
    {
      path: "/home",
      component: Home,
      redirect: "/welcome",
      children: [
        { path: "/welcome", component: Welcome },
        { path: "/user", component: User },
        { path: "/house",component:House},
          //房源添加
        { path:"/add",component:Add},
        {path:"/inspection",component:Inspection}
      ]
    },
    {path: "/house",component:House}
  ]
});
//挂载路由导航守卫
router.beforeEach((to, from, next) => {
  if (to.path == "/login") return next();

  //获取token
  const tokenstr = window.sessionStorage.getItem("token");
  if (!tokenstr) return next("/login");

  next();
});

export default router;
