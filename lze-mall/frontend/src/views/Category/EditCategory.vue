<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h4 class="pt-3">编辑分类</h4>
      </div>
    </div>

    <div class="row">
      <div class="col-3"></div>
      <div class="col-md-6 px-5 px-md-0">
        <form v-if="category">
          <div class="form-group">
            <label>分类名称</label>
            <input type="text" class="form-control" v-model="category.categoryName" required>
          </div>
          <div class="form-group">
            <label>描述</label>
            <input type="text" class="form-control" v-model="category.description" required>
          </div>
          <div class="form-group">
            <label>图片地址（URL）</label>
            <input type="url" class="form-control" v-model="category.imageUrl" required>
          </div>
          <button type="button" class="btn btn-primary" @click="editCategory">提交</button>
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
      category: null
    }
  },
  props : ["baseURL", "categories"],
  methods : {
    async editCategory() {
      delete this.category["products"]
      await axios.post(this.baseURL+"category/update/"+this.id, this.category)
      .then(res => {
          //sending the event to parent to handle
        this.$emit("fetchData");
        this.$router.push({name:'AdminCategory'});
        swal({
          text: "类别更新成功！",
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
      return;
    }
    this.id = this.$route.params.id;
    this.category = this.categories.find(category => category.id == this.id);
    console.log('category', this.category);
  }
}
</script>

<style scoped>
h4 {
  
  color: #484848;
  font-weight: 700;
}
</style>
