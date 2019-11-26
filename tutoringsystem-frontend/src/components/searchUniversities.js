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
function CoursesDto(courseCode){
    this.courseCode = courseCode
}
function TutorDto(tutorUsername, hourlyRate){
  this.username = tutorUsername;
  this.hourlyRate = hourlyRate;
}

export default{
    name: 'universities',
    data () {
      return {
        universities: [],
        newUniversity: '',
        selectedUniversity: '',
        errorUniversity: '',
        
        courses: [],
        newCourse: '',
        selectedCourse: '',
        errorCourse: '',
        response: [],

        tutors: [],
        newTutor: '',
        selectedTutor: '',
        errorTutor: '',
        response: [],

        session: '',
        newSession: '',
        selectedSessions: '',
        errorSession: '',

      }
    },
    beforeMount(){
      this.getUniversities();
    },
    methods : {
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
          getCourses: function(uniName){
            AXIOS.get(`/courses/`+ uniName )
            .then(response => {
              // JSON responses are automatically parsed.
              this.courses = response.data
            })
            .catch(e => {
              this.errorCourse = e;
            });
          },
          getTutors: function(){
            AXIOS.get('/tutor')
            .then(response => {
              this.tutors = response.data
            })
            .catch(e => {
              this.errorTutor = e.response.data.message;
            });
          },
          // bookSession: function(sessionID,username,startTime,endTime,mydate,roomNr,code){
          //   AXIOS.post('/session/' + sessionID + '/' + username + '/' + startTime+':00' + '/' + endTime+':00' + '/' + mydate + '/' + roomNr + '/' + code)
          //   .then(response => {
          //     this.session = response.data
          //   })
          //   .catch(e => {
          //     this.errorSession = e.response.data;
          //   });
          // },
          bookSession: function(username,startTime,endTime,mydate,code,isGroupSession){
            if(typeof isGroupSession == "undefined"){
                isGroupSession=false;
            }
            AXIOS.post('/session/' + username + '/' + startTime+':00' + '/' + endTime+':00' + '/' + mydate + '/' + code + '/' + isGroupSession)
            //AXIOS.post('/session/' + 'rijulsaini' + '/' + '13:13'+':00' + '/' + '14:13'+':00' + '/' + '2020-03-03' + '/' + 'ECSE326' + '/' + 'false')
            .then(response => {
              this.session = response.data;
            })
            .catch(e => {
              this.errorSession = e.response.data;
            });
          },
          gotoSessions(){
            window.location.href ="/#/viewsessions"
        },

        // deleteAllSessions: function(){
        //   AXIOS.post('/sessions/delete')
        //   .then(response => {
        //     this.session = response.data
        //   })
        //   .catch(e => {
        //     this.errorSession = e.response.data;
        //   });
        // },


  }
}
