<template>
  <div class="container">
    <div class="news-item left">
      <div :class="mouseon===1?'news-card mouseon':'news-card'" v-on:mouseover="mouseon=1" v-on:mouseout="mouseon=0">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item mid-left">
      <div :class="mouseon===2?'news-card mouseon':'news-card'" v-on:mouseover="mouseon=2" v-on:mouseout="mouseon=0">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item mid-right">
      <div :class="mouseon===3?'news-card mouseon':'news-card'" v-on:mouseover="mouseon=3" v-on:mouseout="mouseon=0">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item right">
      <div :class="mouseon===4?'news-card mouseon':'news-card'" v-on:mouseover="mouseon=4" v-on:mouseout="mouseon=0">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item left">
      <div :class="mouseon===5?'news-card mouseon':'news-card'" v-on:mouseover="mouseon=5" v-on:mouseout="mouseon=0">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item mid-left">
      <div :class="mouseon===6?'news-card mouseon':'news-card'" v-on:mouseover="mouseon=6" v-on:mouseout="mouseon=0">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item mid-right">
      <div :class="mouseon===7?'news-card mouseon':'news-card'" v-on:mouseover="mouseon=7" v-on:mouseout="mouseon=0">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item right">
      <div :class="mouseon===8?'news-card mouseon':'news-card'" v-on:mouseover="mouseon=8" v-on:mouseout="mouseon=0">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'EmployInfo',
  data () {
    return {
      mouseon: 2
    }
  },
  methods: {

  }
}
</script>

<style scoped>
  .container {
    display: flex;
    flex-flow: row wrap;
    align-content: flex-start;
    width: 100%;
    padding-top: 20px;
  }

  .news-item {
    box-sizing: border-box;
    flex: 0 0 25%;
    height: 126px;
    margin-bottom: 24px;
  }

  .left {
    padding-right: 18px;
  }

  .mid-left {
    padding-left: 6px;
    padding-right: 12px;
  }

  .mid-right {
    padding-left: 12px;
    padding-right: 6px;
  }

  .right {
    padding-left: 18px;
  }

  .news-card {
    width: 100%;
    height: 126px;
    border: 1px solid #f25741;
    border-radius: 4px;
    cursor: pointer;
  }

  .position {
    font-size: 18px;
    font-weight: bold;
    margin: 14px;
  }

  .logo {
    width: 22px;
    margin-bottom: -4px;
    margin-right: 12px;
  }

  .info {
    text-align: left;
    margin: -2px 0 12px 20px;
    color: #8b8b8b;
  }

  .mouseon {
    color: #f25741;
  }
</style>
