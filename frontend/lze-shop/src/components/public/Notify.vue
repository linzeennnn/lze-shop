<script setup>
import { reactive } from 'vue';

const msg=reactive("");
const type=reactive("")
</script>
<template>
    <div id="notify-box" :class="type">
        <span>{{ msg }}</span>
    </div>
</template>
<style>
    
</style>