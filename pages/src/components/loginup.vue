<template>
  <div class ="container">
    <img src="../assets/close.png" class="closebtn" @click="close">
    <p class="title">欢迎注册<a class="to-loginup" @click="loginIn">立即登录？</a></p>
    <form>
      <div class="input-container">
        <img src="../assets/email.png" class="icon">
        <input type="text" class="input" placeholder="邮箱">
      </div>
      <div class="input-container">
        <img src="../assets/user.png" class="icon">
        <input type="text" class="input" placeholder="用户名">
      </div>
      <div class="input-container">
        <img src="../assets/password.png" class="icon">
        <input type="password" class="input" placeholder="密码">
      </div>
      <div class="input-container">
        <img src="../assets/identifying.png" class="icon">
        <input type="text" class="input identifying" placeholder="验证码">
        <img src="../assets/ts.jpg" class="identifyingimg">
      </div>
    </form>
    <button class="loginup-btn">注册</button>
  </div>
</template>

<script>
export default {
  name: 'LoginUp',
  data () {
    return {
      login: 0
    }
  },
  methods: {
    close: function() {
      this.login = 0
      this.$emit('loginSignal', this.login)
    },
    loginIn: function() {
      this.login =1
      this.$emit('loginSignal', this.login)
    }
  }
}
</script>

<style scoped>
  .container {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    width: 330px;
    height: 450px;
    border-radius: 4px;
    background: #fff;
  }

  .closebtn {
    position: absolute;
    top: 15px;
    right: 17px;
    width: 18px;
    padding: 4px;
    background-color: transparent;
    border-radius: 50%;
    opacity: 0.7;
    transition: opacity .2s;
    cursor: pointer;
  }

  .closebtn:hover {
    opacity: 1;
  }

  .title {
    width: 90%;
    margin: 52px auto;
    margin-bottom: 20px;
    padding-left: 10px;
    font-size: 24px;
  }

  .to-loginup {
    margin-left: 102px;
    font-size: 14px;
    color: #e45d47;
    cursor: pointer;
  }

  .input-container {
    position: relative;
    width: 262px;
    height: 34px;
    margin: 26px auto;
  }

  .input {
    position: absolute;
    top: 0;
    left: 0;
    width: 220px;
    height: 24px;
    padding: 4px;
    padding-top: 5px;
    padding-left: 36px;
    border: 1px solid #bebebe;
    border-radius: 2px;
    font-size: 15px;
    background-color: transparent;
  }

  .input::-webkit-input-placeholder {
    color: #aaa;
  }

  .identifying {
    width: 118px;
    border-right: none;
  }

  .identifyingimg {
    position: absolute;
    top: 0;
    right: 1px;
    width: 100px;
    height: 33px;
    border: 1px solid #bebebe;
    cursor: pointer;
  }

  .icon {
    position: absolute;
    top: 7px;
    left: 8px;
    width: 21px;
  }

  .loginup-btn {
    position: absolute;
    top: 362px;
    left: 0;
    right: 0;
    margin: auto;
    width: 150px;
    height: 40px;
    border-radius: 2px;
    border: none;
    font-size: 16px;
    color: #fff;
    background-color: #e45d47;
    cursor: pointer;
  }
</style>
