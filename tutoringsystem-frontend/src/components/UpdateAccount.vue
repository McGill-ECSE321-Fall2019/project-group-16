<template>
    <div id="updateAccount" class="card">
        <span id="header" class="header">Update Account Information</span>
        <b-row>
            <b-col sm="4">
                <h5 class="loginField">Full Name</h5> 
            </b-col>
            <b-col>
                <b-container fluid>
                    <b-form-input
                        class="loginField"
                        type="FullName"
                        id="fullName"
                        v-model="fullName"
                        placeholder="Full Name"
                        :state="fullNameState"
                        aria-describedby="fullname-live-feedback"
                    ></b-form-input>
                <b-form-invalid-feedback id="fullname-live-feedback">
                    Can't be empty
                </b-form-invalid-feedback>
                </b-container>
            </b-col>
        </b-row>
        <b-row>
            <b-col sm="4">
                <h5 class="loginField">Password</h5> 
            </b-col>
            <b-col>
                <b-container fluid>
                    <b-form-input
                        class="loginField"
                        type="password"
                        id="password"
                        v-model="pw"
                        placeholder="Enter new password"
                        :state="passwordState"
                        aria-describedby="pw-live-feedback"
                    ></b-form-input>
                    <b-form-invalid-feedback id="pw-live-feedback">
                        Password must be at least 8 characters
                    </b-form-invalid-feedback>
                </b-container>               
            </b-col>
        </b-row>
        <b-row>
            <b-col sm="4">
                <h5 class="loginField">Confirm Password</h5> 
            </b-col>
            <b-col>
                <b-container fluid>
                    <b-form-input
                        class="loginField"
                        type="password"
                        id="confirmPW"
                        v-model="confirmPW"
                        placeholder="Confirm new password"
                        :state="confirmPasswordState"
                        aria-describedby="confirmpw-live-feedback"
                    ></b-form-input>
                    <b-form-invalid-feedback id="confirmpw-live-feedback">
                        Passwords must match
                    </b-form-invalid-feedback>
                </b-container>
            </b-col>
        </b-row>
        <b-row>
            <b-col>
                <button
                    type="button"
                    v-on:click="getStudent()"
                    class="btn btn-lg requestBtn button"
                >Reset</button>
            </b-col>
            <b-col>
                <button
                    type="button"
                    v-on:click="updateAccount(fullName, pw, confirmPW)"
                    class="btn btn-lg requestBtn button"
                >Save</button>
            </b-col>
        </b-row>

        <div v-if="showAlert">
            <div id="alert" class="alert alert-success" role="alert">{{ requestAlert }}</div>
        </div>
        <div v-if="showError">
            <div id="alert" class="alert alert-danger" role="alert">{{ requestError }}</div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {

    computed: {
        fullNameState() {
            return this.fullName.length > 0 ? true : false
        },
        passwordState() {
            return this.pw.length > 7 ? true : false
        },
        confirmPasswordState() {
            if(this.pw.length === 0){
                return null
            } else {
                return this.pw === this.confirmPW ? true : false
            }
        }
    },
    data() {
        return{
            course: "",
            school: "",
            requestAlert: "",
            showAlert: false,
            showError: false,
            requestError: "",
            pw: "",
            confirmPW: "",
            fullName: "",
            student: {
                type: Object
            },
            userName: "",
        }
    },
    beforeMount(){
        this.getStudent()
    },
    methods: {
        updateAccount: function(fullName, pw, confirmPW){
            this.showAlert = false;
            this.showError = false;

            AXIOS.post(`/studentUpdate/` + this.userName + `/` + this.pw + `/` + fullName)
            .then(response =>{
                this.showAlert = true;
                this.requestAlert = "Your details have been updated!";
                localStorage.removeItem('newFullName');
                localStorage.setItem('newFullName', fullName);
            })
            .catch(e => {
                this.showError = true;
                this.requestError = "There was an error :(";
            });
            
        },
        getStudent: function(){
            this.showAlert = false;
            this.showError = false;
            if(localStorage.getItem('newFullName') === null){
                AXIOS.get(`/student`)
                .then(response => {
                        this.student = response.data;
                        this.fullName = this.student.name;
                        this.userName = this.student.username;
                })
                .catch(e => {
                    this.showError = true;
                    this.requestError = "There was an error :(";
                });
            }else{
                this.fullName = localStorage.getItem('newFullName');
            }


        }
    }
}
</script>

<style >
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
.requestBtn {
    width: 96%;
    border-radius: 4px;
    border: 0px;
    margin: auto;
    margin-top: 15px;
    height: 45px;
    outline-style: auto;
    margin-bottom: 10px;
    background-color: rgb(12, 158, 158);
    
}
.card {
    width: 30%;
    margin: auto;
}
</style>