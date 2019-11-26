<template>
<span>
        <div>
        <h3>Welcome to your DashBoard</h3>

 <div class="flex-container">
  <div> 
      <img id="manage" src="../assets/network.png" alt="" class="img">
        <br>
        <b-button variant="outline-info" v-on:click="gotoSessions">Manage Sessions</b-button>
        <br>
</div>
  <div>
      <img id="booking" src="../assets/calendar.png" alt="" class="img">
        <br>
        <b-button v-on:click="gotosearch" variant="outline-info">Book New Session</b-button>
        <br>
        <!--<b-button v-on:click="gotoReview" variant="outline-info">Review Tutor</b-button>
        <b-button v-on:click="gotoCourseRequest" variant="outline-info">Request a New Course</b-button>
        </div>
        -->
        <div id='logout'>
           <b-button id="logoutButton" variant="outline-info" v-on:click="logout()">Logout</b-button> 
        </div>
</div>
  <div>
      <img src="../assets/review.png" alt="" class="img">
      <br>
       <b-button v-on:click="gotoReview" variant="outline-info">Review Tutor</b-button>
  </div>
  
</div>
        <!-- <img id="manage" src="../assets/network.png" alt="">
        <br>
        <b-button variant="outline-info" v-on:click="gotoSessions">Manage Sessions</b-button>
        <br>
        <img id="booking" src="../assets/calendar.png" alt="">
        <br>
        <b-button v-on:click="gotosearch" variant="outline-info">Book New Session</b-button>
        <br>
        <br>
         -->
         

         
        </div>

</span>
</template>

<script src="./logout.js">
export default {
    data(){
        return{
            name: ""
        }
    },
    // beforeMount(){
    //      student = getLoggedStudent()
    // },
    // methods : {
    //     gotosearch(){
    //     window.location.href = "/#/Selection"
    //         },
    //     gotoSessions(){
    //     window.location.href ="/#/viewsessions"
    //     },
    methods : {
        gotosearch(){
            window.location.href = "/#/Selection"
        },
        gotoSessions(){
            window.location.href ="/#/viewsessions"
        },
        gotoCourseRequest(){
            window.location.href = "/#/requestCourse"
        },        
    }
}

</script>

<style scoped>
.flex-container {
  display: flex;
  justify-content: center;
}
.img { 
  object-fit: contain;
}

#logout{
    margin: 10px;
}

#logoutButton {
    width: 50%;
}

.img {
    height: 40%;
    margin: 20px;
}

</style>
