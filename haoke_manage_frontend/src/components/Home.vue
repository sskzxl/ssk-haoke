<template>
  <el-container class="home_container">
    <el-header>
      <div>
        <img src="../assets/logo.png" alt="" />
        <span>租房后台管理系统</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <!--一级菜单-->
        <div class="toggle-button" @click="togleCollapse">|||</div>
        <el-menu
          :collapse="isCollapse"
          :collapse-transition="false"
          background-color="#333744"
          text-color="#fff"
          active-text-color="#409FFF"
          router
        >
          <el-submenu index="1">
            <!--一级菜单模板区域-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-user"></i>
              <span>用户管理</span>
            </template>
            <!--二菜单模板-->
            <el-menu-item index="/user">
              <template slot="title">
                <!--图标-->
                <i class="el-icon-menu"></i>
                <span>用户列表</span>
              </template>
            </el-menu-item>

          </el-submenu>
        </el-menu>
        <el-menu
          :collapse="isCollapse"
          :collapse-transition="false"
          background-color="#333744"
          text-color="#fff"
          active-text-color="#409FFF"
          router
        >
          <el-submenu index="2">
            <!--一级菜单模板区域-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-s-home"></i>
              <span>房源管理</span>
            </template>
            <!--二菜单模板-->
            <el-menu-item index="/house">
              <template slot="title">
                <!--图标-->
                <i class="el-icon-menu"></i>
                <span>房源列表</span>
              </template>
            </el-menu-item>
            <el-menu-item index="/add">
              <template slot="title">
                <!--图标-->
                <i class="el-icon-menu"></i>
                <span>房源添加</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
        <el-menu
                :collapse="isCollapse"
                :collapse-transition="false"
                background-color="#333744"
                text-color="#fff"
                active-text-color="#409FFF"
                router
        >
          <el-submenu index="1">
            <!--一级菜单模板区域-->
            <template slot="title">
              <!--图标-->
              <i class="el-icon-user"></i>
              <span>看房管理</span>
            </template>
            <!--二菜单模板-->
            <el-menu-item index="/inspection">
              <template slot="title">
                <!--图标-->
                <i class="el-icon-menu"></i>
                <span>看房列表</span>
              </template>
            </el-menu-item>

          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <!--路由占位符-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    // 菜单的折叠与展开
    togleCollapse() {
      this.isCollapse = !this.isCollapse;
    }
  },
  data() {
    return {
      // 默认不折叠
      isCollapse: false
    };
  }
};
</script>
<style lang="less" scoped>
.el-container {
  height: 100%;
}
.el-header {
  background-color: #373f41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;
  > div {
    display: flex;
    align-items: center;
    img {
      height: 40px;
    }
    span {
      margin-left: 15px;
    }
  }
}
.el-aside {
  background-color: #333744;

  .el-menu {
    border: none;
  }
}
.el-main {
  background-color: #eaedf1;
}
.iconfont {
  margin-right: 10px;
}
.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  // 鼠标放上去变成小手
  cursor: pointer;
}
</style>
