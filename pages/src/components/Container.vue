<template>
  <div class="container">
    <div class="main">
      <transition name="fade">
        <p v-if="show === 1">首页</p>
      </transition>
      <transition name="fade">
        <p v-if="show === 2">职位筛选模块</p>
      </transition>
      <transition name="fade">
        <p v-if="show === 3">最新消息</p>
      </transition>
    </div>
    <img v-on:click="prev()" v-if="show > 1" src="../assets/prev.png" class="arrow arrow-prev">
    <img v-on:click="next()" v-if="show < 3" src="../assets/next.png" class="arrow arrow-next">
    <mainHeader></mainHeader>
  </div>
</template>

<script>
import mainHeader from './Header'

export default {
  name: 'HomePage',
  data () {
    return {
      show: 1
    }
  },
  components: {
    mainHeader
  },
  methods: {
    prev() {
      this.show -=1;
    },
    next() {
      this.show += 1;
    }
  }
}
</script>

<style scoped>
  .container {
    width: 100%;
    height: 100%;
    background: -webkit-linear-gradient(left , #5b86e5, #6190e8); /* Safari 5.1 - 6.0 */
    background: -o-linear-gradient(right, #5b86e5, #a7bfe8, #6190e8); /* Opera 11.1 - 12.0 */
    background: -moz-linear-gradient(right, #5b86e5, #6190e8); /* Firefox 3.6 - 15 */
    background: linear-gradient(to right, #5ba2e5 , #6694eb); /* 标准的语法 */
  }

  .fade-enter-active {
    transition: opacity .5s;
  }

  .fade-enter, .fade-leave-to {
    opacity: 0;
  }

  .arrow {
    position: absolute;
    top: 0;
    bottom: 0;
    margin: auto;
    width: 32px;
    opacity: 0.5;
    cursor: pointer;
    transition: opacity .5s;
  }
  .arrow:hover {
    opacity: 0.8;
  }
  .arrow-prev {
    left: 60px;
  }
  .arrow-next {
    right: 60px;
  }

  .main {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
  }
</style>
