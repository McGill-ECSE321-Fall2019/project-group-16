<template>
    <div id="requestCourse" class="card head">
        <span id="header" class="header">Course Requests</span>
        <b-container fluid>
            <b-button @click="getUniversities()" variant="outline-info">Get Universities</b-button>
             <br><br>
            <div class="form-group">
              <h5>Select University</h5>
              <div id="selectUni">
                <div id="selectUni">
                <select class="form-control dropdown" v-model="school">
                  <option value="" selected disabled hidden>Select School</option>
                  <option v-for="university in universities" v-bind:value="{name : university.name}" v-bind:key="university">
                    {{ university.name }}
                  </option>
                </select>
                </div>
              <b-form-input
                class="loginField"
                id="course"
                v-model="course"
                placeholder="Course Name"
                v-on:keyup.enter="requestCourse(school, course)"
              ></b-form-input>
                <b-button 
                    variant="outline-info"
                    class="requestBtn"
                    v-on:click="requestCourse(school, course)"
                >Request Course</b-button>
                <div v-if="showAlert">
                    <div id="alert" class="alert alert-success" role="alert">{{ requestAlert }}</div>
                </div>
                <div v-if="showError">
                    <div id="alert" class="alert alert-danger" role="alert">{{ requestError }}</div>
                </div>
              </div>
            </div>
        </b-container>
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
function UniversityDto(name) {
    this.name = name
}

export default {
    data() {
        return{
            course: "",
            school: "",
            requestAlert: "",
            showAlert: false,
            universities: [],
            showError: false,
            requestError: ""
        }
    },
    methods: {
        getUniversities: function () {
                // Initializing people from backend
                  AXIOS.get(`/universities`)
                  .then(response => {
                    // JSON responses are automatically parsed.
                    this.universities = response.data
                    this.errorUniversity =''
                  })
                  .catch(e => {
                    this.errorUniversity = e.response.data.message;
                  });
          },
        requestCourse: function(school, course){
            this.showAlert = false;
            this.showError = false;
            if(school.name == null || course.trim() < 1){
                this.showError = true;
                this.requestError = "Please select a school and enter a course name!"

            } else {
                this.showAlert = true;
                this.requestAlert = course + " has been requested for " + school.name + " an admin will get back to you!";
            }
        }
    }
}
</script>

<style >
.loginField {
  width: 40%;
  border-radius: 4px;
  border: 0px;
  padding: 2%;
  margin: auto;
  margin-top: 15px;
  outline-style: auto;
}
.head {
    width: 60%;
    border-radius: 4px;
    margin: auto;

}
.header {
    margin-bottom: 10px;
    font-size: 25px;
}
.requestBtn {
    width: 40%;
    border-radius: 4px;
    border: 0px;
    margin: auto;
    margin-top: 15px;
    height: 45px;
    outline-style: auto;
    margin-bottom: 15px;
}
.dropdown {
    width: 40%;
    margin-left: auto;
    margin-right: auto;
}
</style>