<template>
  <div class="adminPanel container">
    <router-link :to="{ name: 'AdminProduct' }">
      <div  class="btn btn-primary btn-lg">
        <div class="btn-img" id="product-img"></div>
        <span>商品管理</span>
      </div>
    </router-link>
    <router-link :to="{ name: 'AdminCategory' }">
      <div  class="btn btn-primary btn-lg">
        <div class="btn-img" id="category-img"></div>
        <span>分类管理</span>
      </div>
    </router-link>
    <router-link :to="{ name: 'Gallery' }">
      <div  class="btn btn-primary btn-lg">
        <div class="btn-img" id="gallery-img"></div>
        
      <span>图库管理</span>
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  mounted() {
    if (!localStorage.getItem('token')) {
      this.$router.push({name : 'Signin'});
    }
  }
}
</script>

<style>
.adminPanel{
  display : flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  align-items: center;
}
.adminPanel div{
  border: none;
  background-color: #c7c7c7fb;
  font-size: 30px;
  width: fit-content;
  margin: 10px;
  transition: transform 0.3s ease ;
}
.adminPanel div:hover
{
  background-color: #787878fb;
  transform: scale(1.03);
}
.btn-img{
      position: relative;
      background-position: center;
      background-size: cover;
    object-fit: cover;
  width: 200px !important;
  height: 200px;
}
#product-img{
  background-image: url(../../assets/product.jpg);
}
#category-img{
  background-image: url(../../assets/category.jpg);
}
#gallery-img{
  background-image: url(../../assets/gallery.png);
}
</style>