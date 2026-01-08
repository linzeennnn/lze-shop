// vue.config.js
module.exports = {
  devServer: {
    allowedHosts: 'all',
    port: 8080,
    proxy: {
      '^/(?!static|js|css|img|favicon\\.ico).*': {
        target: 'http://localhost:8000',
        changeOrigin: true,
        ws: false, 
      },
    },
  },
}
