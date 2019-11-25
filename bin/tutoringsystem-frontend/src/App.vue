<template>
  <div id="app">
    <Navigation></Navigation>
    <br>
    <!-- <img src="./assets/tutoringApp.jpg" width="50%"> -->
    <router-view></router-view>
  </div>
</template>

<script>
import Navigation from './components/Navigation'
export default {
  name: 'app',
  components: { 
  'Navigation': Navigation 
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
  margin-top: 0px;
  /* background-image: url("./assets/bg.jpg"); */
  background-color: #ffffff;
  background-size: 100%
}
</style>
