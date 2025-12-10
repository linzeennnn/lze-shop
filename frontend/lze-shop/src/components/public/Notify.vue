<script setup>
    import { useNotifyStore } from '../../store/notify';

const notify = useNotifyStore()
</script>

<template>
  <div :class="'notify-'+notify.type+' '+'notify-'+notify.display+' '+'notify-box'">
    <span>{{ notify.msg }}</span>
  </div>
</template>

<style>
.notify-box {
  pointer-events: none;
  position: fixed;
  top: 50%;
  left: 50%;
  border-radius: 15px;
  background-color: rgba(0, 0, 0, 0.587);
  min-width: 60px;
  min-height: 40px;
  font-weight: bold;
  text-align: center;
  align-items: center;
  justify-content: center;
  display: flex;
  transform: translate(-50%, -50%);
  transition: opacity 0.5s ease;
  z-index: 9999;
  text-align: center;
  padding: 10px 20px;
}
.notify-normal {
  color: aliceblue;
}
.notify-err {
  color: red;
}
.notify-hide {
  opacity: 0;
}
.notify-show {
  opacity: 1;
}
</style>