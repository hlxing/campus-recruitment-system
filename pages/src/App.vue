<template>
  <div id="app">
    <MyHeader v-on:loginSignal="loginSignal"></MyHeader>
    <router-view/>
    <Footer></Footer>
    <div class="shade" v-show="Signal !== 0">
      <LoginIn v-show="Signal === 1" v-on:loginSignal="loginSignal"></LoginIn>
      <LoginUp v-show="Signal === 2" v-on:loginSignal="loginSignal"></LoginUp>
    </div>
  </div>
</template>

<script>
import MyHeader from './components/head'
import Footer from './components/footer'
import LoginIn from './components/loginin'
import LoginUp from './components/loginup'

export default {
  name: 'App',
  components: {
    MyHeader,
    Footer,
    LoginIn,
    LoginUp
  },
  data () {
    return {
      Signal: 0
    }
  },
  methods: {
    loginSignal: function(inupsignal) {
      this.Signal = inupsignal
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.shade {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #000000b3;
}
</style>
