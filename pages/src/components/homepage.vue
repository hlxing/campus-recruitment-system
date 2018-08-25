<template>
  <div class="container">
    <div class="search-block">
      <img src="../assets/227735.jpg" class="search-back">
      <div class="search">
        <p class="search-title">2018</p>
        <input type="text" class="search-input" placeholder="请输入关键词">
        <div class="search-btn">
          <img src="../assets/search.png" class="search-img">
        </div>
        <p class="hot">热门关键字： 前端 、 PHP 、 安卓</p>
      </div>
    </div>
    <div>
      <NewInfo></NewInfo>
    </div>
  </div>
</template>

<script>
import NewInfo from './newinfo'

export default {
  name: 'HomePage',
  components: {
    NewInfo
  },
  data () {
    return {

    }
  }
}
</script>

<style scoped>
  .container {
    width: 100%;
    min-width: 900px;
    margin: auto;
  }

  .search-block {
    position: relative;
    width: 100%;
  }

  .search-back {
    width: 100%;
  }

  .search {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    width: 500px;
    height: 280px;
  }

  .search-title {
    font-size: 36px;
    color: #fff;
  }

  .search-input {
    position: absolute;
    left: 0;
    width: 410px;
    height: 36px;
    padding: 2px 10px;
    border: none;
    border-top-left-radius: 3px;
    border-bottom-left-radius: 3px;
    font-size: 16px;
    color: #2c3e50;
    opacity: 0.85;
  }

  .search-btn {
    position: absolute;
    right: 0px;
    width: 70px;
    height: 40px;
    border-top-right-radius: 3px;
    border-bottom-right-radius: 3px;
    background-color: #e45d47;
    opacity: 0.9;
    cursor: pointer;
  }

  .search-img {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    width: 22px;
  }

  .hot {
    position: absolute;
    top: 148px;
    left: 0;
    color: #fff;
  }
</style>
