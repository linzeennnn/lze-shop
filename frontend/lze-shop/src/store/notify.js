// store/notify.js
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useNotifyStore = defineStore('notify', () => {
  const msg = ref('')
  const type = ref('')
  const display = ref('hide')

  function normal(text) {
    msg.value = text
    type.value = 'normal'
    display.value = 'show'
    setTimeout(() => display.value = 'hide', 1000)
  }

  function err(text) {
    msg.value = text
    type.value = 'err'
    display.value = 'show'
    setTimeout(() => display.value = 'hide', 1000)
  }

  return { msg, type, display, normal, err }
})