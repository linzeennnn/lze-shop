<template>
    <div>
    <input type="text" placeholder="手机号" class="auth-input" v-model="phone"
     @keydown="handleKeyDown"
     ></input>
    <input type="password" placeholder="密码" class="auth-input" v-model="password"
    @keydown="handleKeyDown"
    ></input>
    <button class="auth-btn" @click="login">登陆</button>
    </div>
</template>


<script setup>
import { ref } from 'vue';
import { useNotifyStore } from '../../store/notify';
import { useGlobal } from '../../store/global';
const notify=useNotifyStore();
const phone =ref("")
const password=ref("")
const global=useGlobal()
const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
    login();
  }
}
function login(){
    fetch(`${global.api}/user/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            phone: phone.value,
            password: password.value
        })
    })
    .then(response => response.json())
    .then(data => {
        if(data.code==200){
            notify.normal(data.msg)
            global.username=data.data.username
            global.token=data.data.token
            global.SaveUserData();
        }else{
            notify.err(data.msg)
        }
    })
    .catch(error => {
        console.error('注册出错:', error);
    });
}
</script>