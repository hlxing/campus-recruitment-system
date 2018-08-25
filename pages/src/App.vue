<template>
  <div id="app">
    <MyHeader></MyHeader>
    <router-view/>
    <Footer></Footer>
  </div>
</template>

<script>
import MyHeader from './components/head'
import Footer from './components/footer'

export default {
  name: 'App',
  components: {
    MyHeader,
    Footer
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
</style>
