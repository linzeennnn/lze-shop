<template>
    <div>
    <input type="text" placeholder="手机号" class="auth-input" 
    v-model="phone" @keydown="handleKeyDown"></input>
    <input type="text" placeholder="用户名" class="auth-input"
     v-model="username" @keydown="handleKeyDown"></input>
    <input type="password" placeholder="密码" class="auth-input" 
    v-model="password" @keydown="handleKeyDown"></input>
    <button class="auth-btn" @click="register">注册</button>
    </div>
</template>
<script setup>
import { ref } from 'vue';
import { useGlobal } from '../../store/global';
import { useNotifyStore } from '../../store/notify';
const notify=useNotifyStore()
const phone = ref('');
const username = ref('');
const password = ref('');
const global = useGlobal();
const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
    register();
  }
}
function register() {
    fetch(`${global.api}/user/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            phone: phone.value,
            username: username.value,
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