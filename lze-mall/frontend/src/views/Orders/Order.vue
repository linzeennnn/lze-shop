<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h4 class="pt-3">你的订单</h4>
      </div>
    </div>

    <!-- 遍历每个订单显示 -->
    <div v-for="order in orderList" :key="order.pid" class="row mt-2 pt-3 justify-content-around">
      <div class="col-2"></div>

      <!-- 左侧显示订单第一件商品图片 -->
      <div class="col-md-3 embed-responsive embed-responsive-16by9">
        <img v-bind:src="order.imageURL" class="w-100 card-img-top embed-responsive-item">
      </div>

      <!-- 右侧显示订单信息 -->
      <div class="col-md-5 px-3">
        <div class="card-block px-3">
          <h6 class="card-title">
            <router-link v-bind:to="'/order/'+order.id">订单号: {{order.id}}</router-link>
          </h6>
          <p class="mb-0">{{order.totalItems}} 件商品</p>
          <p id="item-price" class="mb-0 font-weight-bold">总价: ¥ {{order.totalCost}}</p>
          <p id="item-total-price">下单日期: {{order.orderdate}}</p>
        </div>
      </div>

      <div class="col-2"></div>
      <div class="col-12"><hr></div>
    </div>
  </div>
</template>


<script>
  const axios = require('axios')
  export default {

    data() {
      return {
        token: null,
        orderList : []
      }
    },
    props:["baseURL"],
    name: 'Order',
    methods: {
      // list of order histories
      listOrders(){
        axios.get(`${this.baseURL}order/?token=${this.token}`)
          .then((response) => {
              if(response.status==200){
                this.orders = response.data
                // for each order populate orderList
               this.orders.forEach((order) => {
              const firstItem = order.orderItems && order.orderItems.length > 0 ? order.orderItems[0] : null;
              this.orderList.push({
                id: order.id,
                totalCost: order.totalPrice,
                orderdate: order.createdDate ? order.createdDate.substring(0, 10) : '',
                imageURL: firstItem && firstItem.product ? firstItem.product.imageURL : 'default-image.jpg',
                totalItems: order.orderItems ? order.orderItems.length : 0
              });
            });

              }
            },
            (error)=>{
              console.log(error)
            });
      },
    },
    mounted() {
      this.token = localStorage.getItem("token");
      this.listOrders();
    },
  };

</script>

<style scoped>
  h4, h5 {
    
    color: #484848;
    font-weight: 700;
  }

  .embed-responsive .card-img-top {
    object-fit: cover;
  }
</style>
