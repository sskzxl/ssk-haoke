import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  // app
  {
    path: "/",
    component: () =>
      import(/* webpackChunkName: "basic-layout" */ "../layouts/BasicLayout"),
    Routes: ["src/pages/Authorized"],
    authority: ["admin", "user"],
    children: [{ path: "/", redirect: "/user" }]
  },
  // user
  {
    path: "/user",
    component: () =>
      import(/* webpackChunkName: "user-layout" */ "../layouts/UserLayout"),
    children: [
      { path: "/user", redirect: "/user/login" },
      {
        path: "/user/login",
        component: () =>
          import(/* webpackChunkName: "login" */ "../views/user/Login")
      },
      {
        path: "/user/register",
        component: () =>
          import(/* webpackChunkName: "register" */ "../views/user/Register")
      },
      {
        path: "/user/register-result",
        component: () =>
          import(
            /* webpackChunkName: "register-result" */ "../views/user/RegisterResult"
          )
      }
    ]
  },
  {
    path: "*",
    component: () => import(/* webpackChunkName:"404" */ "../views/NotFound")
  }
];

const router = new VueRouter({
  routes
});

export default router;
