<script setup>
import AuthPage from './view/AuthPage.vue';
</script>

<template>
  <AuthPage />
</template>
<style>
html, body {
   touch-action: manipulation;
  height: 100%;
  position: relative;
  margin: 0;
  padding: 0;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  scrollbar-width: none;         
  -ms-overflow-style: none; 
}
#app{
  width: 100%;
  height: 100%;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
}
button{
}
button:hover{
  opacity: 0.7 !important;
}
</style>