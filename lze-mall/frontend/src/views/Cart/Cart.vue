<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h3 class="pt-3">购物车</h3>
      </div>
    </div>

    <!-- 循环显示购物车里的商品 -->
    <div v-for="cartItem in cartItems" :key="cartItem.product.id" class="row mt-2 pt-3 justify-content-around">
      <div class="col-2"></div>

      <!-- 商品图片 -->
      <div class="col-md-3 embed-responsive embed-responsive-16by9">
        <router-link :to="{ name: 'ShowDetails', params: { id: cartItem.product.id } }">
          <img :src="cartItem.product.imageURL" class="w-100 card-img-top embed-responsive-item" />
        </router-link>
      </div>

      <!-- 商品信息 -->
      <div class="col-md-5 px-3">
        <div class="card-block px-3">
          <h6 class="card-title">
            <router-link :to="{ name: 'ShowDetails', params: { id: cartItem.product.id } }">
              {{ cartItem.product.name }}
            </router-link>
          </h6>
          <p id="item-price" class="mb-0 font-weight-bold">
            ￥{{ cartItem.product.price }} / 件
          </p>
          <p id="item-quantity" class="mb-0">
            数量：
            <input
              size="1"
              class="p-0 h-25 border-bottom border-top-0 border-left-0 border-right-0"
              v-model.number="cartItem.quantity"
              @change="updateItemQuantity(cartItem)"
            />
          </p>
          <p id="item-total-price" class="mb-0">
            总价：
            <span class="font-weight-bold">￥{{ (cartItem.product.price * cartItem.quantity).toFixed(2) }}</span>
          </p>

          <!-- 删除按钮 -->
          <br />
          <button class="text-right btn btn-link p-0" @click="deleteItem(cartItem.id)">
            从购物车移除
          </button>
        </div>
      </div>

      <div class="col-2"></div>
      <div class="col-12"><hr /></div>
    </div>

    <!-- 总价 -->
    <div class="total-cost pt-2 text-right">
      <h5>总计：￥{{ totalcost.toFixed(2) }}</h5>
      <button :disabled="isDisabled()" type="button" class="btn btn-primary confirm" @click="checkout">
        确认订单
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Cart",
  props: ["baseURL"],
  data() {
    return {
      cartItems: [],
      token: null,
      totalcost: 0
    };
  },
  methods: {
    isDisabled() {
      return this.cartItems.length === 0;
    },

    // 获取购物车数据
    listCartItems() {
      if (!this.token) return;
      axios
        .get(`${this.baseURL}cart/?token=${this.token}`)
        .then((res) => {
          if (res.status === 200) {
            this.cartItems = res.data.cartItems;
            this.totalcost = res.data.totalCost;
            // 同步父组件购物车数量
            this.$emit("cartUpdated", this.cartItems.length);
          }
        })
        .catch((err) => console.log(err));
    },

    // 更新数量
    updateItemQuantity(cartItem) {
      axios
        .put(`${this.baseURL}cart/update/${cartItem.id}/`, {
          token: this.token,
          quantity: cartItem.quantity
        })
        .then((res) => {
          if (res.status === 200) this.listCartItems();
        })
        .catch((err) => console.log(err));
    },

    // 删除商品
    deleteItem(itemId) {
      axios
        .delete(`${this.baseURL}cart/delete/${itemId}/?token=${this.token}`)
        .then((res) => {
          if (res.status === 200) this.listCartItems();
        })
        .catch((err) => console.log(err));
    },

    // 去结算页
    checkout() {
      this.$router.push({ name: "Checkout" });
    },

    showDetails(productId) {
      this.$router.push({ name: "ShowDetails", params: { id: productId } });
    }
  },
  mounted() {
    this.token = localStorage.getItem("token");
    this.listCartItems();
  }
};
</script>

<style scoped>
h4,
h5 {
  color: #484848;
  font-weight: 700;
}

.embed-responsive .card-img-top {
  object-fit: cover;
}

#item-price {
  color: #232f3e;
}

#item-quantity {
  float: left;
}

#item-total-price {
  float: right;
}

.confirm {
  font-weight: bold;
  font-size: larger;
}
</style>
