<template>
  <div class="hk-user-layout">
    <div class="hk-user-layout__content">
      <div class="hk-user-layout__logo">
        <h2><img src="../assets/logo.png" alt />Ant Design</h2>
        <p>Ant Design 是西湖区最具影响力的 Web 设计规范</p>
      </div>
      <router-view></router-view>
    </div>
    <div class="hk-user-layout__footer"></div>
  </div>
</template>

<script>
export default {
  name: "UserLayout"
};
</script>

<style lang="scss">
.hk-user-layout {
  display: flex;
  flex-direction: column;
  height: 100vh;
  overflow: auto;
  background: #f0f2f5;
  background-image: url(https://gw.alipayobjects.com/zos/rmsportal/TVYTbAXWheQpRcWDaDMu.svg);
  background-repeat: no-repeat;
  background-position: center 110px;
  background-size: 100%;
}
.hk-user-layout__content {
  padding: 32px 0;
  flex: 1 1;
}
.hk-user-layout__logo {
  text-align: center;
  img {
    height: 55px;
  }

  h2 {
    font-size: 33px;
    color: rgba(0, 0, 0, 0.85);
    font-family: "Myriad Pro", "Helvetica Neue", Arial, Helvetica, sans-serif;
    font-weight: 600;
    position: relative;
    top: 2px;
  }

  p {
    font-size: 14px;
    color: rgba(0, 0, 0, 0.45);
    margin-top: 12px;
    margin-bottom: 40px;
  }
}
</style>
