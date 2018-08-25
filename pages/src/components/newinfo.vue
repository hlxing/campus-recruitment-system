<template>
  <div class="container">
    <div class="news-head">
      <ul class="news-tab">
        <li :class="on===1?'chosed':''" v-on:click="on=1">最新消息</li>
        <li :class="on===2?'chosed':''" v-on:click="on=2">类别检索</li>
      </ul>
      <p class="more-tip" v-if="on===1">查看更多>></p>
    </div>
    <EmployInfo v-if="on===1"></EmployInfo>
    <PositionChose v-if="on===2"></PositionChose>
  </div>
</template>

<script>
import EmployInfo from './newemploymentinfo'
import PositionChose from './positionchose'

export default {
  name: 'NewInfo',
  components: {
    EmployInfo,
    PositionChose
  },
  data () {
    return {
      on: 1
    }
  }
}
</script>

<style scoped>
  .container {
    position: relative;
    width: 76%;
    margin-top: 20px;
  }

  ul li {
    float: left;
    list-style: none;
    padding: 4px 6px;
    margin-left: -38px;
    margin-right: 66px;
    color: #8b8b8b;
    border: 2px solid transparent;
    cursor: pointer;
  }

  .chosed {
    color: #e45d47;
    border: 2px solid #e45d47;
    border-radius: 6px;
  }

  .more-tip {
    position: absolute;
    top: 5px;
    right: 0;
    margin: 0;
    color: #e45d47;
    cursor: pointer;
  }
</style>
