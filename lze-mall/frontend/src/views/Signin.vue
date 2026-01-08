<template>
  <div class="container">
    <!--    Logo Div-->
    <div class="row">
      <div class="col-12 text-center mt-3">
        <router-link :to="{ name: 'Home' }">
          <img id="logo" src="../assets/icon.jpg" />
        </router-link>
      </div>
    </div>

    <div class="row">
      <div class="col-12 justify-content-center d-flex flex-row pt-5 mt-2">
        <div id="signin-div" class="flex-item border">
          <h2 class="text-center pt-4 pl-4">登录</h2>
          <form @submit="signin" class="text-center pt-4 pl-4 pr-4">
            <div class="form-group">
              <label>电子邮箱</label>
              <input type="email" class="form-control" v-model="email" required />
            </div>
            <div class="form-group">
              <label>密码</label>
              <input type="password" class="form-control" v-model="password" required />
            </div>
            <small class="form-text text-muted">继续即表示您同意 Simplecoding 的使用条款及隐私政策。</small>
            <button type="submit" class="btn btn-primary mt-2 py-0">
              登录
              <div v-if="loading" class="spinner-border spinner-border-sm" role="status">
                <span class="sr-only">加载中...</span>
              </div>
            </button>
          </form>
          <hr />
          <small class="form-text text-muted pt-2 pl-4 text-center">还没有 Simplecoding 账号？</small>
          <p class="text-center">
            <router-link :to="{ name: 'Signup' }" class="btn btn-dark text-center mx-auto px-5 py-1 mb-2">
              创建您的 Simplecoding 账号
            </router-link>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: "Signin",
  props: ["baseURL"],
  data() {
    return {
      email: null,
      password: null,
      loading: null,
    };
  },
  methods: {
    async signin(e) {
      e.preventDefault();
      this.loading = true;

      const user = {
        email: this.email,
        password: this.password,
      };

      await axios
        .post(`${this.baseURL}users/signin`, user)
        .then((res) => {
          localStorage.setItem("token", res.data.token);
          this.$emit("fetchData");
          this.$router.push({ name: "Home" });
        })
        .catch((err) => {
          swal({
            text: "无法登录您的帐户!",
            icon: "error",
            closeOnClickOutside: false,
          });
          console.log(err);
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
  mounted() {
    this.loading = false;
  },
};
</script>

<style scoped>
.btn-dark {
  background-color: #e7e9ec;
  color: #000;
  font-size: smaller;
  border-radius: 0;
  border-color: #adb1b8 #a2a6ac #a2a6ac;
}

.btn-primary {
  background-color: #e87022;
  color: white;
  border-color: #dc752c #b77b21 #ed912e;
  border-radius: 0;
}

#logo {
  width: 150px;
}

@media only screen and (min-width: 992px) {
  #signin-div {
    width: 40%;
  }
}
</style>
