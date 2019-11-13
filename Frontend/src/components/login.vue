<template>
  <div id="login" class="card">
    <b-button pill
      v-on:click="goToHomePage()"
    >FakeLogin</b-button>
    <span id="title">Student Login:</span>
    <div v-if="showError">
      <div id="alert" class="alert alert-warning" role="alert">{{ errorMsg }}
      </div>
    </div>
    <b-container fluid>
      <input
        class="loginField"
        type="text"
        id="username"
        v-model="userName"
        placeholder="Enter Username"
        v-on:keyup.enter="login(userName, pw)"
      >
      
      <input
        class="loginField"
        type="password"
        id="password"
        v-model="pw"
        placeholder="Enter password"
        v-on:keyup.enter="login(userName, pw)"
      >
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
      AXIOS.get(`/students/` + userName)
        .then(response => {
          this.student = response.data;
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
        name: "createAccount"
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
  max-height: 480px;
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
</style>