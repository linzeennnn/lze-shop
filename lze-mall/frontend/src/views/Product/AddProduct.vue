<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h4 class="pt-3">新增商品</h4>
      </div>
    </div>

    <div class="row">
      <div class="col-3"></div>
      <div class="col-md-6 px-5 px-md-0">
        <form>
          <div class="form-group">
            <label>所属分类</label>
            <select class="form-control" v-model="categoryId" required>
              <option
                v-for="category of categories"
                :key="category.id"
                :value="category.id"
              >
                {{ category.categoryName }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label>商品名称</label>
            <input type="text" class="form-control" v-model="name" required>
          </div>
          <div class="form-group">
            <label>商品描述</label>
            <input type="text" class="form-control" v-model="description" required>
          </div>
          <div class="form-group">
            <label>图片地址（URL）</label>
            <input type="url" class="form-control" v-model="imageURL" required>
          </div>
          <div class="form-group">
            <label>价格</label>
            <input type="number" class="form-control" v-model="price" required>
          </div>
          <button
            type="button"
            class="btn btn-primary"
            @click="addProduct"
          >
            提交
          </button>
        </form>
      </div>
      <div class="col-3"></div>
    </div>
  </div>
</template>


<script>
export default {
  data(){
    return {
      id : null,
      categoryId : null,
      name : null,
      description : null,
      imageURL : null,
      price : null
    }
  },
  props : ["baseURL", "products", "categories"],
  methods : {
    async addProduct() {
      const newProduct = {
        id : this.id,
        categoryId : this.categoryId,
        name : this.name,
        description : this.description,
        imageURL : this.imageURL,
        price : this.price
      }

      await axios({
        method: 'post',
        url: this.baseURL+"product/add",
        data : JSON.stringify(newProduct),
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(res => {
        //sending the event to parent to handle
        this.$emit("fetchData");
        this.$router.push({name : 'AdminProduct'});
        swal({
          text: "产品添加成功！",
          icon: "success",
          closeOnClickOutside: false,
        });
      })
      .catch(err => console.log(err));
    }
  },
  mounted() {
    if (!localStorage.getItem('token')) {
      this.$router.push({name : 'Signin'});
    }
  }
}
</script>

<style scoped>
h4 {
  
  color: #484848;
  font-weight: 700;
}

</style>
