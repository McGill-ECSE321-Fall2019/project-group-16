<template>
    <div id="login">
    <button 
        class="btn btn-success logout" 
        v-on:click="logout()"
        type="button"
    >Logout</button>

    <div class="row">
        <span id="title" class="textField">ipsum</span>
        <button class="btn btn-info dropdown-toggle buttonField" type="button" id="school" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Select School
        </button>
        <div class="dropdown-menu" aria-labelledby="school">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <a class="dropdown-item" href="#">Something else here</a> 
        </div>
    </div>
    <div v-if="showError">
      <div id="alert" class="alert alert-warning" role="alert">{{ errorMsg }}
      </div>
    </div>
    <b-container fluid>
        <div class="row">
            <input
                type="text"
                class="textField"
                id="courseCode"
                placeholder="Course Code"
            >
            <button
                type="button"
                v-on:click="search()"
                class="btn btn-primary btn-lg buttonField button"
            >Search Course</button>
            <input
                type="text"
                class="textField"
                id="tutorName"
                placeholder="Tutor Name"
            >
            <button
                type="button"
                v-on:click="search(tutorName)"
                class="btn btn-primary btn-lg buttonField button"
            >Search Tutor</button>
            <button
                type="button"
                v-on:click="login(userName, pw)"
                class="btn btn-primary btn-lg buttonField button"
            >View Review sessions</button>
        </div>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";

var config = require("../../config");

// Axios config
var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl =
  "http://" + config.build.backendHost + ":" + config.build.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  data() {
    return {
      university: {
          type: Object
      },
      course: {
          type: Object
      },
      tutor: {
          type: Object
      },
      error: "",
      pw: "",
      userName: "",
      showError: false,
      errorMsg: "",
      tutorName: "",
      courseCode: "",
      
    };
  },

  methods: {
    // Send GET request to find admin
    logout: function(){
        Router.push({
            path: '/',
            name: 'login'
        })
    },
    search: function(tutor){
        AXIOS.get(`/tutors/` + tutor)
            .then(response => {
                this.tutor = response.data;
                
            })
            .catch( e => {
                this.errorMsg = "No tutors exist with that name";
                this.showError = true;
            });
    }
  },
};
</script>

<style>
#title {
  text-align: left;
  color: black;
  font-size: 30px;
  padding-left: 15px;
}

#send {
  align-content: right;
}

#name {
  text-align: left;
  color: white;
  font-size: 26px;
  padding-left: 15px;
}

#login {
  width: 30%;
  max-height: 480px;
  min-width: 550px;
  margin: auto;
  margin-top: 15px;
  padding: 15px;
  text-align: left;
}

.buttonField {
  width: 30%;
  border-radius: 4px;
  border: 0px;
  padding: 2%;
  margin: auto;
  margin-top: 15px;
}
.textField {
  width: 65%;
  border-radius: 4px;
  border: 0px;
  padding: 2%;
  margin: auto;
  margin-top: 15px;
}
.button {
  color: white;
}
.logout {
    
}
</style>