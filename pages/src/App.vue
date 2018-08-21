<template>
  <div id="app">
    <MyHeader></MyHeader>
    <router-view/>
  </div>
</template>

<script>
import MyHeader from './components/head'

export default {
  name: 'App',
  components: {
    MyHeader
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
