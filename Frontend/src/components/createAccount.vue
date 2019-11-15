<template>
    <div id="createAccount" class="card">
        <span id="header" class="header">Tutoring System</span>
        <b-button pill 
            variant="outline-danger"
            v-on:click="returnToLogin()"
            class="btn button btn-secondary rtrn-btn"
        >Return</b-button>
        <span id="title">Create Account:</span>
        <div v-if="showError">
            <div id="alert" class="alert alert-warning" role="alert">{{ errorMsg }}</div>
        </div>
        <b-container fluid>
            <input
                class="loginField"
                type="FullName"
                id="fullName"
                v-model="fullName"
                placeholder="Full Name"
                v-on:keyup.enter="createAccount(userName, pw, fullName)"
            > 
            <input
                class="loginField"
                type="FullName"
                id="userName"
                v-model="userName"
                placeholder="Username"
                v-on:keyup.enter="createAccount(userName, pw, fullName)"
            >
            <input
                class="loginField"
                type="text"
                id="username"
                v-model="email"
                placeholder="Enter email"
                v-on:keyup.enter="createAccount(userName, pw, fullName)"
            >
            <input
                class="loginField"
                type="password"
                id="password"
                v-model="pw"
                placeholder="Enter password"
                v-on:keyup.enter="createAccount(userName, pw, fullName)"
            >
            <input
                class="loginField"
                type="password"
                id="confirmPW"
                v-model="confirmPW"
                placeholder="Confirm password"
                v-on:keyup.enter="createAccount(userName, pw, fullName)"
            >
            <button
                type="button"
                v-on:click="createAccount(userName, pw, fullName)"
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
        data(){
            return {
                student: {
                    type: Object
                },
                errorMsg: "",
                showError: false,
                pw: "",
                email: "",
                confirmPW: "",
                fullName: "",
                userName: ""
            };
        },
        methods: {
            returnToLogin: function(){
                Router.push({
                    path: "/",
                    name: "login"
                });
            },
            goToHomePage: function(){
                Router.push({
                    path: "/home",
                    name: "homePage"
                });
            },
            createAccount: function(userName, pw, fullName) {
                this.errorMsg = "";
                this.showError = false;
                if(this.pw == this.confirmPW){
                    AXIOS.post(`/student/` + userName + `/` + pw + `/` + fullName)
                    .then(response =>{
                        this.student = response.data
                        this.goToHomePage();
                    })
                    .catch( e => {
                        this.errorMsg = e;
                        this.showError = true;
                    });
                } else {
                    this.errorMsg = "Passwords don't match";
                    this.showError = true;
                }

            }
        }
    }
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
  color: black;
  font-size: 26px;
  padding-left: 15px;
}

#createAccount {
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
.rtrn-btn {
    margin-left: 85%;
    width: 15%;
}
.header {
    text-align: center;
    font-size: 40px;
    color: white;
    background-color: #007bff;
    border-radius: 5px;
}
</style>