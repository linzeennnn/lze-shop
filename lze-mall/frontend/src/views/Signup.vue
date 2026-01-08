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
        <div id="signup-div" class="flex-item border">
          <h2 class="text-center pt-4 pl-4">创建账号</h2>
          <form @submit="signup" class="text-center pt-4 pl-4 pr-4">
            <div class="form-group">
              <label>电子邮箱</label>
              <input type="email" class="form-control" v-model="email" required />
            </div>
            <div class="form-row">
              <div class="col">
                <div class="form-group">
                  <label>名字</label>
                  <input type="text" class="form-control" v-model="firstName" required />
                </div>
              </div>
              <div class="col">
                <div class="form-group">
                  <label>姓氏</label>
                  <input type="text" class="form-control" v-model="lastName" required />
                </div>
              </div>
            </div>
            <div class="form-group">
              <label>密码</label>
              <input type="password" class="form-control" v-model="password" required />
            </div>
            <div class="form-group">
              <label>确认密码</label>
              <input type="password" class="form-control" v-model="passwordConfirm" required />
            </div>
            <button type="submit" class="btn btn-primary mt-2 py-0">
              创建账号
            </button>
          </form>
          <hr />
          <small class="form-text text-muted pt-2 pl-4 text-center">已经有账号？</small>
          <p class="text-center">
            <router-link class="btn btn-dark text-center mx-auto px-5 py-1 mb-2" :to="{ name: 'Signin' }">
              立即登录
            </router-link>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: "Signup",
  props: ["baseURL"],
  data() {
    return {
      email: null,
      firstName: null,
      lastName: null,
      password: null,
      passwordConfirm: null,
    };
  },
  methods: {
    async signup(e) {
      e.preventDefault();
      if (this.password === this.passwordConfirm) {
        const user = {
          email: this.email,
          firstName: this.firstName,
          lastName: this.lastName,
          password: this.password,
        };

        // call the API
        await axios
          .post(`${this.baseURL}users/signup`, user)
          .then(() => {
            this.$router.replace("/");
            swal({
              text: "用户注册成功。请登录",
              icon: "success",
              closeOnClickOutside: false,
            });
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        swal({
          text: "错误！密码不匹配。",
          icon: "error",
          closeOnClickOutside: false,
        });
      }
    },
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
  background-color: #e87022 !important;
  color: white;
  border-color: #dc752c #b77b21 #ed912e;
  border-radius: 0;
}

#logo {
  width: 150px;
}

@media only screen and (min-width: 992px) {
  #signup-div {
    width: 40%;
  }
}
</style>
