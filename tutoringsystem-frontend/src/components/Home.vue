<template>
<span>
        <div>
        <h3>Welcome To Your DashBoard</h3>
        <img id="manage" src="../assets/network.png" alt="">
        <br>
        <b-button variant="outline-info" v-on:click="gotoSessions">Manage Sessions</b-button>
        <br>
        <img id="booking" src="../assets/calendar.png" alt="">
        <br>
        <b-button v-on:click="gotosearch" variant="outline-info">Book New Session</b-button>
         <b-button v-on:click="gotoReview" variant="outline-info">Review Tutor</b-button>
        </div>

        <div id='logout'>
           <b-button id="logoutButton" variant="outline-info" v-on:click="logout()">Logout</b-button> 
        </div>

</span>
</template>

<script src="./logout.js">
export default {
    methods : {
        gotosearch(){
        window.location.href = "/#/Selection"
            },

        
}
}
</script>

<style scoped>
#booking{
    margin: 10px;
    width: 10%;
    height: 10%;
}
#manage{
    margin: 10px;
    width: 10%;
    height: 10%;
}

#logout{
    margin: 10px;
}

#logoutButton {
    width: 50%;
}

</style>