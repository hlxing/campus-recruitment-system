<template>
  <div class="hearder">
    <div class="container">
      <img src="../assets/fzu-logo.png" class="logo">
      <div class="tab">
        <ul>
          <router-link  to="/">
            <li :class="on === 1?'chosed':''" v-on:click="on=1">首页</li>
          </router-link>
          <router-link  to="/detail">
            <li :class="on === 2?'chosed':''" v-on:click="on=2">招聘专场</li>
          </router-link>
          <li :class="on === 3?'chosed':''" v-on:click="on=3">招聘信息</li>
        </ul>
      </div>
      <button class="btn login-in" @click="loginIn">登录</button>
      <button class="btn login-up" @click="loginUp">注册</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyHeader',
  data () {
    return {
      on: 1,
      login: 0
    }
  },
  methods: {
    loginIn: function () {
      this.login = 1
      this.$emit('loginSignal', this.login)
    },
    loginUp: function () {
      this.login = 2
      this.$emit('loginSignal', this.login)
    }
  }
}
</script>

<style scoped>
  .hearder {
    width: 100%;
  }

  .container {
    position: relative;
    width: 76%;
    min-width: 880px;
    height: 50px;
    margin: auto;
  }

  .logo {
    position: absolute;
    top: 10px;
    left: 0;
    width: 240px;
  }

  .tab {
    position: absolute;
    left: 0;
    right: 0;
    width: 345px;
    height: 44px;
    margin: auto;
  }

  ul li {
    float: left;
    list-style: none;
    margin: 0 15px;
    margin-top: -4px;
    font-size: 16px;
    transition: color .1s;
    cursor: pointer;
  }

  ul li:hover {
    color: #e45d47;
  }

  .chosed {
    color: #e45d47;
    padding-bottom: 2px;
    border-bottom: 2px solid #e45d47;
  }

  .btn {
    position: absolute;
    top: 12px;
    padding: 1px 10px;
    font-size: 16px;
    border: 1px solid #e45d47;
    border-radius: 2px;
    background-color: transparent;
    cursor: pointer;
  }

  .login-in {
    right: 70px;
    color: #e45d47;
  }

  .login-up {
    right: 0;
    color: #fff;
    background-color: #e45d47;
  }
</style>
