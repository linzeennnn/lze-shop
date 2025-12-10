import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useGlobal = defineStore('global', () => {
  let api = window.location.origin+"/api"
  const username=ref("")
  let token=ref("")
  function SaveUserData(){
    localStorage.setItem("username",username.value)
    localStorage.setItem("token",token.value)
  }
  return { api ,username,token,SaveUserData}
})