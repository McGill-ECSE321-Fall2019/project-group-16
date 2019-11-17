<template>
<span class="container">
    
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
       <div class="welcome" >
          <b-button variant="outline-info">Log In</b-button>
         <b-button variant="outline-info">Register</b-button>
    </div>
</span>
</template>

<style scoped>
input[type=text]{
width: 50%
}
</style>