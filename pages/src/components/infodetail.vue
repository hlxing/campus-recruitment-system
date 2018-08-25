<template>
  <div class="container">
    <div class="content">
      <p class="reback">&lt;返回搜索页</p>
      <div class="title">
        <p>Web 前端开发工程师<span class="status">实习生/应届生</span></p>
      </div>
      <p><span class="tip">方向：</span>技术方向</p>
      <p><span class="tip">类型：</span>软件开发</p>
      <p><span class="tip">工作地点：</span>北京、上海、杭州</p>
      <p><span class="tip">公司：</span>技术方向</p>
      <p><span class="tip">岗位职责：</span>技术方向</p>
      <p><span class="tip">岗位要求：</span>技术方向</p>
      <p class="time">发布于：2018.08.20</p>
      <button class="pick">Pick</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'InfoDetail',
  data () {
    return {

    }
  }
}
</script>

<style scoped>
  .container {
    width: 100%;
    border-top: 2px solid #e35d4999;
    margin: auto;
    padding: 5px;
  }

  .content {
    width: 76%;
    min-width: 880px;
    min-height: 500px;
    margin: auto;
    text-align: left;
  }

  .reback {
    text-align: right;
    color: #e45d47;
    cursor: pointer;
  }

  .title {
    padding-left: 14px;
    line-height: 40px;
    border-left: 4px solid #e45d47;
    font-size: 26px;
  }

  .status {
    margin-left: 24px;
    font-size: 16px;
    color: #8b8b8b;
  }

  .tip {
    display: inline-block;
    width: 96px;
    margin-left: 18px;
  }

  .time {
    text-align: right;
    margin-top: 40px;
  }

  .pick {
    padding: 8px 24px;
    margin-left: 18px;
    border: none;
    font-size: 20px;
    color: #fff;
    background-color: #e45d47;
    transition: background-color .3s;
    cursor: pointer;
  }

  .pick:hover {
    background-color: #e45d47;
  }
</style>
