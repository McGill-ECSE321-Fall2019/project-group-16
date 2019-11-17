<template>
<span class="container">
    <img src="../assets/teacher.png" alt="" width="200" height="200">
    <h1>{{msg}}</h1>
    <div class="userlogo">
      <img src="../assets/660611.svg" alt="logo" width="200" height="200">
      <br>
    </div>
    <div class="form">
      <form action="">
        <input type="text" name="username" id="username" placeholder="Username">
        <br>
        <br>
        <input type="text" name="password" id="password" placeholder="Password">
        <br>
        <br>
        <input type="text" name="name" id="name" placeholder="Name">
        <br>       
        <br>
      </form>

    </div>
    <div class="buttons" >
        <b-button v-on:click="gotosearch" variant="outline-info">Log In</b-button>
         <br>
         <br>
         <h6>Don't have an account?</h6>
         <b-button variant="outline-info">Register</b-button>
    </div>
</span>
</template>
<script>
export default {
    name: 'welcome',
  data () {
    return {
      msg: 'Welcome to Your Tutoring App'
    }
},
methods : {
        gotosearch(){
        window.location.href = "/#/Selection"
            }
}
}
</script>

<style scoped>
input[type=text]{
margin-top: 10px;
width: 50%;
border-radius: 6px;
background-color: rgb(247, 247, 247);
}
</style>