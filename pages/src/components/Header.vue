<template>
  <div class="header">
    <ul>
      <li class="clickon">首页</li>
      <li>校招日历</li>
      <li>宣讲会</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'mainHeader',
  data () {
    return {

    }
  },
  methods: {

  }
}
</script>

<style scoped>
  .header {
    position: absolute;
    top: 50px;
    left: 0;
    right: 0;
    margin: auto;
  }

  ul li {
    float: left;
    list-style: none;
    margin: 0 15px;
    padding-bottom: 10px;
    color: rgba(255, 255, 255, 0.555);
    cursor: pointer;
    transition: color .3s;
  }
  ul li:hover {
    color: #fff;
  }
  .clickon {
    font-weight: bold;
    color: #fff;
    border-bottom: 2px solid #fff;
  }
</style>
