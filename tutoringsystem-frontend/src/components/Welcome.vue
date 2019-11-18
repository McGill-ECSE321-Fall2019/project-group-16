<template>
  <span class="container">
  <img src="../assets/prof.png">
  <div id="login" class="card">
    <span id="header" class="header">Tutoring System</span>
    <span id="title">Student Login:</span>
    <div v-if="showError">
      <div id="alert" class="alert alert-warning" role="alert">{{ errorMsg }}
      </div>
    </div>
    <b-container fluid>
      <b-form-input
        class="loginField"
        type="text"
        id="username"
        v-model="userName"
        placeholder="Enter Username"
        :state="usernameState"
        aria-describedby="username-live-feedback"
        v-on:keyup.enter="login(userName, pw)"
      ></b-form-input>
      <b-form-invalid-feedback id="username-live-feedback">
        Can't be empty
      </b-form-invalid-feedback>
    </b-container>
    <b-container fluid>  
      <b-form-input
        class="loginField"
        type="password"
        id="password"
        v-model="pw"
        placeholder="Enter password"
        :state="pwState"
        aria-describedby="pw-live-feedback"
        v-on:keyup.enter="login(userName, pw)"
      ></b-form-input>
      <b-form-invalid-feedback id="pw-live-feedback">
        Can't be empty
      </b-form-invalid-feedback>

      <button
        type="button"
        v-on:click="login(userName, pw)"
        class="btn btn-primary btn-lg loginField button"
      >Login</button>
      <button
        type="button"
        v-on:click="goToAccountPage()"
        class="btn btn-primary btn-lg loginField button"
      >Create an Account</button>
    </b-container>
  </div>
  </span>
</template>

<script>
import axios from "axios";
import Router from "../router";

var config = require("../../config");

// Axios config
var frontendUrl = "https://cors-anywhere.herokuapp.com/http://" + config.build.host + ":" + config.build.port;
var backendUrl =
  "https://cors-anywhere.herokuapp.com/http://" + config.build.backendHost;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  computed: {
    usernameState() {
        return this.userName.length > 0 ? true : false
    },
    pwState() {
        return this.pw.length > 0 ? true : false
    },
  },
  data() {
    return {
      student: {
        type: Object
      },
      error: "",
      pw: "",
      userName: "",
      showError: false,
      errorMsg: ""
    };
  },

  methods: {
    // Send GET request to find admin
    login: function(userName, pw) {
      this.errorMsg = "";
      this.showError = false;
      AXIOS.get(`/student/` + userName)
        .then(response => {
          this.student = response.data;
          console.log(response.data);
          if (this.student.password === pw) {
            this.goToHomePage();
          } else {
            this.errorMsg = "Password Incorrect";
            this.showError = true;
          }
        })
        .catch(e => {
          console.log(e.message);
          this.errorMsg = "Account does not exist";
          this.showError = true;

        });
    },
    goToHomePage: function() {
      Router.push({
        path: "/home",
        name: "homePage"
      });
    },
    goToAccountPage: function() {
      Router.push({
        path: "/account/",
        name: "CreateAccount"
      });
    },
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
  max-height: auto;
  min-width: 550px;
  margin: auto;
  margin-top: 15px;
  padding: 15px;
  text-align: left;
}

.loginField {
  width: 98%;
  border-radius: 4px;
  border: 0px;
  padding: 2%;
  margin: auto;
  margin-top: 15px;
}

.button {
  color: white;
}
.header {
    text-align: center;
    font-size: 40px;
    color: white;
    background-color: #007bff;
    border-radius: 5px;
    margin-bottom: 4px;
}

</style>