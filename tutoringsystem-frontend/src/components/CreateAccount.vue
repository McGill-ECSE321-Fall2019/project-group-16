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
            <b-form-input
                class="loginField"
                type="FullName"
                id="fullName"
                v-model="fullName"
                placeholder="Full Name"
                :state="fullNameState"
                aria-describedby="fullname-live-feedback"
                v-on:keyup.enter="createAccount(userName, pw, fullName)"
            ></b-form-input>
            <b-form-invalid-feedback id="fullname-live-feedback">
                Can't be empty
            </b-form-invalid-feedback>
        </b-container>
        <b-container fluid>
            <b-form-input
                class="loginField"
                type="FullName"
                id="userName"
                v-model="userName"
                placeholder="Username"
                :state="usernameState"
                aria-describedby="username-live-feedback"
                v-on:keyup.enter="createAccount(userName, pw, fullName)"
            ></b-form-input>
            <b-form-invalid-feedback id="username-live-feedback">
                Can't be empty
            </b-form-invalid-feedback>
        </b-container>
        <b-container fluid>
            <b-form-input
                class="loginField"
                type="text"
                id="email"
                v-model="email"
                placeholder="Enter email"
                :state="emailState"
                v-on:keyup.enter="createAccount(userName, pw, fullName)"
            ></b-form-input>
            <b-form-invalid-feedback id="email-live-feedback">
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
                :state="passwordState"
                aria-describedby="pw-live-feedback"
                v-on:keyup.enter="createAccount(userName, pw, fullName)"
            ></b-form-input>
            <b-form-invalid-feedback id="pw-live-feedback">
                Password must be at least 8 characters
            </b-form-invalid-feedback>
        </b-container>
        <b-container fluid>
            <b-form-input
                class="loginField"
                type="password"
                id="confirmPW"
                v-model="confirmPW"
                placeholder="Confirm password"
                :state="confirmPasswordState"
                aria-describedby="confirmpw-live-feedback"
                v-on:keyup.enter="createAccount(userName, pw, fullName)"
            ></b-form-input>
            <b-form-invalid-feedback id="confirmpw-live-feedback">
                Passwords must match
            </b-form-invalid-feedback>

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
var frontendUrl = "https://cors-anywhere.herokuapp.com/http://" + config.build.host + ":" + config.build.port;
var backendUrl = 
  "http://" + config.build.backendHost + ":" + config.build.port;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

    export default {
        computed: {
            fullNameState() {
                return this.fullName.length > 0 ? true : false
            },
            usernameState() {
                return this.userName.length > 0 ? true : false
            },
            emailState() {
                return this.email.length > 0 ? true : false
            },
            passwordState() {
                return this.pw.length > 7 ? true : false
            },
            confirmPasswordState() {
                return this.pw === this.confirmPW ? true : false
            }
            },
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
                    name: "welcome"
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
                        // this.errorMsg = "All fields must be filled!";
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
    margin-top: 4px;
    margin-left: 0;
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