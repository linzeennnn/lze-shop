<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-light">
    <!-- Logo -->
    <router-link class="navbar-brand" :to="{ name: 'Home' }">
      <img id="logo" src="../assets/icon.jpg" />
    </router-link>

    <!-- Burger Button -->
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="切换导航"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <!-- Search Bar -->
      <form class="form-inline ml-auto mr-auto" @submit.prevent="goSearch">
        <div class="input-group">
          <input
            size="100"
            type="text"
            class="form-control"
            placeholder="搜索商品"
            v-model="keyword"
          />
          <div class="input-group-prepend">
            <button type="submit" class="input-group-text" id="search-button-navbar">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                   fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path
                  d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001
                     c.03.04.062.078.098.115l3.85 3.85
                     a1 1 0 0 0 1.415-1.414l-3.85-3.85
                     a1.007 1.007 0 0 0-.115-.1zM12
                     6.5a5.5 5.5 0 1 1-11 0
                     5.5 5.5 0 0 1 11 0z"/>
              </svg>
            </button>
          </div>
        </div>
      </form>

      <!-- Navbar Links -->
      <ul class="navbar-nav ml-auto">
        <!-- 浏览下拉 -->
        <li class="nav-item dropdown">
          <a class="nav-link text-dark dropdown-toggle" href="#" role="button"
             data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            浏览
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <router-link class="dropdown-item" :to="{ name: 'Home' }">首页</router-link>
            <router-link class="dropdown-item" :to="{ name: 'Product' }">商品</router-link>
            <router-link class="dropdown-item" :to="{ name: 'Category' }">分类</router-link>
          </div>
        </li>

        <!-- 账户下拉 -->
        <li class="nav-item dropdown">
          <a class="nav-link text-dark dropdown-toggle" href="#" role="button"
             data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            账户
          </a>
          <div class="dropdown-menu text-dark">
            <router-link class="dropdown-item" v-if="!token" :to="{ name: 'Signin' }">愿望清单</router-link>
            <router-link class="dropdown-item" v-else :to="{ name: 'Wishlist' }">愿望清单</router-link>
            <router-link class="dropdown-item" :to="{ name: 'Admin' }">管理后台</router-link>
            <router-link class="dropdown-item" v-if="!token" :to="{ name: 'Signin' }">登录</router-link>
            <router-link class="dropdown-item" v-if="!token" :to="{ name: 'Signup' }">注册</router-link>
            <a class="dropdown-item" v-if="token" href="#" @click.prevent="signout">登出</a>
          </div>
        </li>

        <!-- 订单链接 -->
        <li class="nav-item">
          <router-link class="nav-link text-dark" :to="{ name: 'Order' }">订单</router-link>
        </li>

        <!-- 购物车 -->
        <li class="nav-item">
          <div id="cart">
            <span id="nav-cart-count">{{ cartCount }}</span>
            <router-link class="color-cart" :to="{ name: 'Cart' }">
              <i class="fa" style="font-size:36px">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                     stroke-width="2" stroke-linecap="round" stroke-linejoin="round" width="48" height="48">
                  <path d="M6 6h15l-1.5 9H6z" />
                  <path d="M6 6L4 2" />
                  <circle cx="9" cy="20" r="2" />
                  <circle cx="18" cy="20" r="2" />
                </svg>
              </i>
            </router-link>
          </div>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  name: "Navbar",
  props: ["cartCount"],
  data() {
    return {
      token: localStorage.getItem("token"),
      keyword: ""
    };
  },
  methods: {
    signout() {
      localStorage.removeItem("token");
      this.token = null;
      this.$emit("resetCartCount"); // 通知父组件清空购物车数量
      this.$router.push({ name: "Home" });
      swal({
        text: "您已成功退出登录。欢迎再次访问。",
        icon: "success",
        closeOnClickOutside: false,
      });
    },

    goSearch() {
      if (!this.keyword.trim()) return;

      this.$router.push({
        path: "/search",
        query: { keyword: this.keyword }
      });
    }
  }
};
</script>

<style scoped>
#logo {
  width: 150px;
  margin-left: 20px;
  margin-right: 20px;
}

.nav-link {
  color: #d08720;
}

.color-cart {
  color: #e87022;
}

#search-button-navbar {
  cursor: pointer;
  background-color: #e66f25;
  border-color: #e66f25;
  border-top-right-radius: 2px;
  border-bottom-right-radius: 2px;
}

#nav-cart-count {
  background-color: red;
  color: white;
  border-radius: 50%;
  width: 15px;
  height: 15px;
  font-size: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  margin-left: 10px;
}

#cart {
  position: relative;
}
</style>
