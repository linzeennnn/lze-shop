import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    host: true,       // 监听所有网卡
    port: 4321,       // 开发服务器端口
    strictPort: true, // 如果端口被占用则直接报错
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:4322', // 目标后端
        changeOrigin: true
      },
    }
  }
})
