<template>
  <div class="search-page container">
    <h2 class="pt-3"><strong>{{'"'+ keyword +'"'}}</strong> 搜索结果</h2>
    <p v-if="!keyword">请输入搜索关键词</p>
    <div v-if="loading">正在搜索...</div>
    <div v-else-if="results.length === 0 && keyword">
      没有找到相关商品
    </div>
    <div v-else class="row">
      <div
        v-for="index in results.length"
        :key="results[index - 1].id"
        class="col-md-6 col-xl-4 col-12 pt-3 d-flex justify-content-around"
      >
        <ProductBox :product="results[index - 1]" />
      </div>
    </div>
  </div>
</template>

<script>
import ProductBox from "../../components/Product/ProductBox";
import axios from "axios";

export default {
  name: "Search",
  components: { ProductBox },
  props: ["products"], // 从 Home 传入的完整商品列表
  data() {
    return {
      keyword: "",
      searchIds: [], // 搜索结果的 ID 数组
      results: [], // 搜索结果的商品对象数组
      loading: false,
    };
  },
  created() {
    this.keyword = this.$route.query.keyword || "";
    if (this.keyword) {
      this.search();
    }
  },
  watch: {
    "$route.query.keyword"(newVal) {
      this.keyword = newVal || "";
      if (this.keyword) {
        this.search();
      } else {
        this.searchIds = [];
        this.results = [];
      }
    }
  },
  methods: {
    async search() {
      this.loading = true;
      this.searchIds = [];
      this.results = [];

      try {
        // 1. 调用后端搜索接口，获取 ID 数组
        const response = await axios.get(`/product/search`, {
          params: { keyword: this.keyword },
        });
        this.searchIds = response.data; // [1, 5, 12]

        if (this.searchIds.length === 0) {
          this.results = [];
          return;
        }

        // 2. 根据 ID 从已有 products 数组里匹配商品对象
        this.results = this.products.filter(product =>
          this.searchIds.includes(product.id)
        );

      } catch (error) {
        console.error("搜索失败:", error);
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style scoped>
.search-page {
  padding: 20px;
}
</style>
