<template>
  <div class="container">
    <div class="news-item left">
      <div class="news-card" v-on:mouseover="mouseon=1" v-on:mouseout="mouseon=0">
        <p class="position"><img src="../assets/design.png" class="logo">设计</p>
        <div class="tab-table" v-if="mouseon===1">
          <table>
            <tr>
              <td>前端</td>
              <td>Unity</td>
            </tr>
            <tr>
              <td>PHP</td>
              <td></td>
            </tr>
            <tr>
              <td>JAVA</td>
              <td></td>
            </tr>
            <tr>
              <td>Python</td>
              <td></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div class="news-item mid-left">
      <div class="news-card" v-on:mouseover="mouseon=2" v-on:mouseout="mouseon=0">
        <p class="position"><img src="../assets/design.png" class="logo">技术</p>
        <div class="tab-table" v-if="mouseon===2">
          <table>
            <tr>
              <td>前端</td>
              <td>Unity</td>
            </tr>
            <tr>
              <td>PHP</td>
              <td></td>
            </tr>
            <tr>
              <td>JAVA</td>
              <td></td>
            </tr>
            <tr>
              <td>Python</td>
              <td></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div class="news-item mid-right">
      <div class="news-card" v-on:mouseover="mouseon=3" v-on:mouseout="mouseon=0">
        <p class="position"><img src="../assets/pm.png" class="logo">产品</p>
        <div class="tab-table" v-if="mouseon===3">
          <table>
            <tr>
              <td>前端</td>
              <td>Unity</td>
            </tr>
            <tr>
              <td>PHP</td>
              <td></td>
            </tr>
            <tr>
              <td>JAVA</td>
              <td></td>
            </tr>
            <tr>
              <td>Python</td>
              <td></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div class="news-item right">
      <div class="news-card" v-on:mouseover="mouseon=4" v-on:mouseout="mouseon=0">
        <p class="position"><img src="../assets/business.png" class="logo">人力资源</p>
        <div class="tab-table" v-if="mouseon===4">
          <table>
            <tr>
              <td>前端</td>
              <td>Unity</td>
            </tr>
            <tr>
              <td>PHP</td>
              <td></td>
            </tr>
            <tr>
              <td>JAVA</td>
              <td></td>
            </tr>
            <tr>
              <td>Python</td>
              <td></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div class="news-item left">
      <div class="news-card" v-on:mouseover="mouseon=5" v-on:mouseout="mouseon=0">
        <p class="position"><img src="../assets/supply.png" class="logo">供应链</p>
        <div class="tab-table" v-if="mouseon===5">
          <table>
            <tr>
              <td>前端</td>
              <td>Unity</td>
            </tr>
            <tr>
              <td>PHP</td>
              <td></td>
            </tr>
            <tr>
              <td>JAVA</td>
              <td></td>
            </tr>
            <tr>
              <td>Python</td>
              <td></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div class="news-item mid-left">
      <div class="news-card" v-on:mouseover="mouseon=6" v-on:mouseout="mouseon=0">
        <p class="position"><img src="../assets/supply.png" class="logo">供应链</p>
        <div class="tab-table" v-if="mouseon===6">
          <table>
            <tr>
              <td>前端</td>
              <td>Unity</td>
            </tr>
            <tr>
              <td>PHP</td>
              <td></td>
            </tr>
            <tr>
              <td>JAVA</td>
              <td></td>
            </tr>
            <tr>
              <td>Python</td>
              <td></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div class="news-item mid-right">
      <div class="news-card" v-on:mouseover="mouseon=7" v-on:mouseout="mouseon=0">
        <p class="position"><img src="../assets/supply.png" class="logo">供应链</p>
        <div class="tab-table" v-if="mouseon===7">
          <table>
            <tr>
              <td>前端</td>
              <td>Unity</td>
            </tr>
            <tr>
              <td>PHP</td>
              <td></td>
            </tr>
            <tr>
              <td>JAVA</td>
              <td></td>
            </tr>
            <tr>
              <td>Python</td>
              <td></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div class="news-item right">
      <div class="news-card" v-on:mouseover="mouseon=8" v-on:mouseout="mouseon=0">
        <p class="position"><img src="../assets/more.png" class="logo">其他</p>
        <div class="tab-table" v-if="mouseon===8">
          <table>
            <tr>
              <td>前端</td>
              <td>Unity</td>
            </tr>
            <tr>
              <td>PHP</td>
              <td></td>
            </tr>
            <tr>
              <td>JAVA</td>
              <td></td>
            </tr>
            <tr>
              <td>Python</td>
              <td></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PositionChose',
  data () {
    return {
      mouseon: 0
    }
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
    position: relative;
    width: 100%;
    height: 126px;
    border: 2px solid #adadad;
    border-radius: 4px;
    color: #666666;
    cursor: pointer;
    transition: color .2s,
                border .2s;
  }
  .news-card:hover {
    border: 2px solid #f25741;
  }

  .position {
    font-size: 35px;
    font-weight: bold;
    margin: 36px auto;
  }

  .logo {
    width: 36px;
    margin-bottom: -6px;
    margin-right: 10px;
  }

  .tab-table {
    position: absolute;
    top: 0;
    left: 2px;
    width: 90%;
    height: 98%;
    background-color: #fff;
  }

  table {
    margin: 15px 0;
    margin-left: 15%;
  }

  table tr td {
    width: 70px;
    line-height: 24px;
    text-align: left;
    color: #f25741;
  }
</style>
