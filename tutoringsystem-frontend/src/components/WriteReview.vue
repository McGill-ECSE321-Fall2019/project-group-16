<template>
   <div class="review">
        <h5>Review Tutor</h5>
        <img id="reviewImg" src="../assets/review.svg" alt="">
        <div id="selectTutor">
        <select class="form-control"  v-model="selectedTutor" >
          <option value="" selected disabled hidden>Choose here</option>
          <option v-for="tutor in tutors" v-bind:value="{name : tutor.name , rate: tutor.hourlyRate}" v-bind:key="tutor">{{tutor.name}}</option>
        </select>
        </div>
        <div class="rating"> 
        <label>Rating :</label>
        <select class="form-control" id="exampleFormControlSelect1" v-model="selectedRating">
          <option>1 : Poor</option>
          <option>2 : Below Average</option>
          <option>3 : Average</option>
          <option>4 : Good</option>
          <option>5 : Excellent</option>
        </select>
        </div>
        <div class="comments">
          <label for="">Comments: </label>
         <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" v-model="comments"></textarea>
        </div>
        <b-button id="reviewButton" variant="outline-info" v-on:click="submitReview(selectedTutor.username, selectedRating,comments)">Submit Review</b-button>
        <div>
          Note : For this page to work, we need to integrate with the Tutor ViewPoint
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
    data () {
      return {
        tutors: [],
        newTutor: '',
        selectedTutor: '',
        errorTutor: '',
        response: [],

        student: {
          type: Object
        },
        errorStudent: '',

        submitReviewMsg: {
          type:Object
        },
        errorReviewMsg: '',
      }
    },
    beforeMount() {
      this.getTutors()
      this.getStudent()
    },
    methods : {
          getTutors: function(){
            AXIOS.get('/tutor')
            .then(response => {
              this.tutors = response.data
            })
            .catch(e => {
              this.errorTutor = e.response.data.message;
            });
          },
          getStudent: function(){
            AXIOS.get('/student')
            .then(response => {
              this.student = reponse.data
            })
            .catch(e => {
              this.errorStudent = e.response.data.message;
            })
          },
          submitReview: function(username, rating, comment){
            AXIOS.post('/tutorReview/' + comment + '/' + username + '/' + rating + '/' + this.student.username)
            .then(response => {
              this.submitReviewMsg = response.data
              window.location.href = "/#/tutor/" + username
            })
            .catch (e => {
              this.errorReviewMsg = e.response.data.message;
            })

          }
  }
}
</script>
<style scoped>
.review {
  margin-right: 30px;
  margin-left: 30px;
}
.comments {
  margin: 20px;
}

#reviewButton {
  margin: 10px;
  float: right;
}
#refreshButton {
  margin: 10px;
  float: middle;
}
#reviewImg {
  margin: 20px;
  width: 200px;
  height: 200px;
}
</style>


