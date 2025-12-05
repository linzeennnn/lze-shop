<script setup>
import { ref } from 'vue';
import Login from '../components/auth/Login.vue';
import Register from '../components/auth/Register.vue';

const winType = ref('login');

function switchShow(showType) {
  winType.value = showType;
}
</script>

<template>
  <div id="account-win">
    <div id="switch-bar">
      <span @click="switchShow('login')" 
      :class="winType === 'login' ? 'active' : ''">登陆</span>
      <span @click="switchShow('register')"
      :class="winType === 'register' ? 'active' : ''"
      >注册</span>
    </div>

  <Register v-show="winType === 'register'"/>

  <Login v-show="winType === 'login'"/>

  </div>
</template>

<style>
#account-win{
    width: 300px;
    height: 400px;
    display:flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    border-radius: 15px;
    background-color: aliceblue;
}
#switch-bar{
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}
#switch-bar span{
    cursor: pointer;
    color: gray;
    font-weight: bold;
    margin: 10px;
}
#switch-bar span:hover{
    color: black;
}
/* ////////公共css///////// */
.auth-input{
    border-radius: 15px;
    width: 80%;
    height: 35px;
    margin: 10px auto;
    display: block;
    padding: 5px;
    font-size: 16px;
}
.auth-btn{
    width: 80%;
    height: 40px;
    margin: 10px auto;
}
.active{
    color: black !important;
}
</style>
