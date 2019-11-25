import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

function TutorDto(tutorUsername, hourlyRate){
  this.username = tutorUsername;
  this.hourlyRate = hourlyRate;
}

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default{
    name: 'tutors',
    data () {
      return {
        tutors: [],
        newTutor: '',
        selectedTutor: '',
        errorTutor: '',
        response: [],
      }
    },
    methods : {
        getTutors: function () {
                // Initializing people from backend
                  AXIOS.get(`/Tutors`)
                  .then(response => {
                    // JSON responses are automatically parsed.
                    this.Tutors = response.data
                    this.errorTutors =''
                  })
                  .catch(e => {
                    this.errorTutors = e.response.data.message;
                  });
          },
  }
}
