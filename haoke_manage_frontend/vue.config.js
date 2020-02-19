module.exports = {
  devServer: {
    proxy: {
      "/haoke": {
        target: "http://127.0.0.1:18080",
        changeOrigin: true,
        pathRewrite: { "^/haoke": "" }
      }
    }
  }
};
