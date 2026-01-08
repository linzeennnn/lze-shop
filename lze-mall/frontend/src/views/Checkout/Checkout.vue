<template>
  <div class="div_class">
    <h3>您将被重定向到支付页面</h3>

    <div class="alert alert-primary" role="alert">
      点击“进行付款”后，系统会直接下单并跳转到支付成功页面。
    </div>

    <button
      class="checkout_button"
      id="proceed-to-checkout"
      :disabled="checkoutBodyArray.length === 0"
      @click="goToCheckout"
    >
      进行付款
    </button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Checkout",
  props: ["baseURL"],
  data() {
    return {
      token: null,
      checkoutBodyArray: [],
    };
  },
  methods: {
    // 获取购物车商品并构造订单数据
    getAllItems() {
      axios
        .get(`${this.baseURL}cart/?token=${this.token}`)
        .then((response) => {
          if (response.status === 200) {
            const products = response.data.cartItems;
            this.checkoutBodyArray = products.map((item) => ({
              imageUrl: item.product.imageURL,
              productName: item.product.name,
              quantity: item.quantity,
              price: item.product.price,
              productId: item.product.id,
              userId: item.userId,
            }));
          }
        })
        .catch((err) => {
          console.log("获取购物车商品失败:", err);
        });
    },

    // 点击付款
    goToCheckout() {
      if (!this.checkoutBodyArray.length) return;

      // 1. 创建 checkout session
      axios
        .post(`${this.baseURL}order/checkout-session`, this.checkoutBodyArray)
        .then((response) => {
          const sessionId = response.data; // 后端返回的 sessionId
          localStorage.setItem("sessionId", sessionId);

          // 2. 下单接口
          return axios.post(`${this.baseURL}order/add`, null, {
            params: { token: this.token, sessionId: sessionId },
          });
        })
        .then(() => {
          // 3. 下单成功，通知父组件清空购物车数量
          this.$emit("cartUpdated", 0);

          // 4. 跳转到订单页
          this.$router.push({ name: "Order" });
        })
        .catch((err) => {
          console.error("下单失败:", err);
          // 失败也跳转到订单页
          this.$router.push({ name: "Order" });
        });
    },
  },
  mounted() {
    this.token = localStorage.getItem("token");
    this.getAllItems();
  },
};
</script>

<style scoped>
.alert {
  width: 50%;
}

.div_class {
  margin-top: 5%;
  margin-left: 30%;
}

.checkout_button {
  background-color: #fba123;
  border: none;
  color: white;
  margin-left: 15%;
  padding: 15px 30px;
  text-align: center;
  display: inline-block;
  font-size: 15px;
  font-weight: bold;
  border-radius: 15px;
  cursor: pointer;
}

.checkout_button:focus {
  outline: none;
  box-shadow: none;
}

.checkout_button:disabled {
  background-color: #9b86f7;
  cursor: not-allowed;
}
</style>
