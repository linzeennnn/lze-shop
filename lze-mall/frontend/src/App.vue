<template>
  <Navbar
    :cartCount="cartCount"
    @resetCartCount="resetCartCount"
    v-if="!['Signup', 'Signin'].includes($route.name)"
  />
  
  <div style="min-height: 60vh">
    <router-view
      v-if="products && categories"
      :baseURL="baseURL"
      :products="products"
      :categories="categories"
      @fetchData="fetchData"
      @cartUpdated="updateCartCount"
    >
    </router-view>
  </div>

  <Footer v-if="!['Signup', 'Signin'].includes($route.name)" />
</template>

<script>
import axios from 'axios';
import Navbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';

export default {
  name: "App",
  data() {
    return {
      baseURL: `${window.location.protocol}//${window.location.hostname}:8000/`,
      products: null,
      categories: null,
      token: null,
      cartCount: 0, // 购物车数量
    };
  },
  components: { Footer, Navbar },
  methods: {
    async fetchData() {
      try {
        const prodRes = await axios.get(this.baseURL + 'product/');
        this.products = prodRes.data;

        const catRes = await axios.get(this.baseURL + 'category/');
        this.categories = catRes.data;

        this.token = localStorage.getItem('token');
        if (this.token) {
          const cartRes = await axios.get(`${this.baseURL}cart/?token=${this.token}`);
          if (cartRes.status === 200) {
            this.cartCount = cartRes.data.cartItems.length;
          }
        }
      } catch (err) {
        console.log(err);
      }
    },

    resetCartCount() {
      this.cartCount = 0;
    },

    // Cart 组件删除或结算后调用
    updateCartCount(newCount) {
      this.cartCount = newCount;
    }
  },
  mounted() {
    this.token = localStorage.getItem('token');
    this.fetchData();
  }
};
</script>

<style>
html {
  overflow-y: scroll;
}
body {
  background-color: rgb(255, 246, 234);
}
</style>
