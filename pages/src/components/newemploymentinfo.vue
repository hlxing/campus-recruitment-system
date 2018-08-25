<template>
  <div class="container">
    <div class="news-item left">
      <router-link  to="/detail">
        <div class="news-card">
          <p class="position">Web前端开发工程师</p>
          <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
          <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
        </div>
      </router-link>
    </div>
    <div class="news-item mid-left">
      <router-link  to="/detail">
        <div class="news-card">
          <p class="position">Web前端开发工程师</p>
          <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
          <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
        </div>
      </router-link>
    </div>
    <div class="news-item mid-right">
      <div class="news-card">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item right">
      <div class="news-card">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item left">
      <div class="news-card">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item mid-left">
      <div class="news-card">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item mid-right">
      <div class="news-card">
        <p class="position">Web前端开发工程师</p>
        <p class="info"><img src="../assets/locad.png" class="logo">地址：上海</p>
        <p class="info"><img src="../assets/company.png" class="logo">京东集团</p>
      </div>
    </div>
    <div class="news-item right">
      <div class="news-card">
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
    border: 2px solid #adadad;
    border-radius: 4px;
    cursor: pointer;
    transition: color .2s,
                border .2s;
  }
  .news-card:hover {
    color: #e45d47;
    border: 2px solid #e45d47;
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

  a {
    text-decoration: none;
    color: #2c3e50;
  }
</style>
