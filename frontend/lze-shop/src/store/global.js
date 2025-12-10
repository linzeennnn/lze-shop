import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useGlobal = defineStore('global', () => {
  let api = window.location.origin+"/api"
  const username=ref("")
  let token=""
  function saveUserData(){
    localStorage.setItem("username",username)
    localStorage.setItem("token",token)
  }
  return { api ,username,token}
})