<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3">订单号: {{orderID}}</h4>
            </div>
        </div>

        <!-- 遍历订单项显示每个商品 -->
        <div v-for="(orderItem, index) in orderItems" :key="index" class="row mt-2 pt-3 justify-content-around">
            <div class="col-1"></div>

            <!-- 左侧商品图片 -->
            <div class="col-md-3 embed-responsive embed-responsive-16by9">
                <img :src="orderItem.product.imageURL" class="w-100 card-img-top embed-responsive-item">
                <hr/>
            </div>

            <!-- 右侧商品信息 -->
            <div class="col-md-5 px-3">
                <div class="card-block px-3">
                    <h6 class="card-title">{{orderItem.product.name}}</h6>
                    <p id="item-price" class="mb-0 font-weight-bold">单价: ¥{{orderItem.product.price}}</p>
                    <p id="item-quantity" class="mb-0">数量: {{orderItem.quantity}}</p>
                    <p id="item-total-price" class="mb-0">
                        总价: ¥<span class="font-weight-bold">{{ orderItem.price * orderItem.quantity }}</span>
                    </p>
                </div>
            </div>

            <div class="col-1"></div>
        </div>

        <!-- 总价显示 -->
        <div class="total-cost pt-2 text-right">
            <h5>订单总价: ¥ {{order.totalPrice}}</h5>
        </div>
    </div>
</template>


<script>

export default {
    name:'OrderItems',
    props:["orderID","baseURL"],

data() {
    return {
        orderItems:[],
        order: {},
        token: null,
        orderID: 0
    }
},

methods:{
    getOrder(){
        axios.get(`${this.baseURL}order/${this.orderID}?token=${this.token}`).then((response) => {
            if(response.status === 200) {
                this.order = response.data
                this.orderItems = this.order.orderItems
            }
            },
            (err)=>{
                console.log(err)
            })
    }

},

mounted(){
    this.orderID = this.$route.params.id;
    this.token = localStorage.getItem("token")
    this.getOrder()
}
}

</script>

<style scoped>

    h4 {
        
        color: #484848;
        font-weight: 700;
    }

    .embed-responsive .card-img-top {
        object-fit: cover;
    }

</style>
