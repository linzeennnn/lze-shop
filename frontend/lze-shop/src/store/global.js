import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useGlobal = defineStore('global', () => {
  const api = ref(window.location.origin+"/api")
  return { api }
})