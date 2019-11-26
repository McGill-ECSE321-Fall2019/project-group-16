import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default{
    data () {
        return{
        log: '',
        errorLogout: '',
    }
    },
    methods : {
        logout: function() {
            AXIOS.put('/logout')
            .then(response => {
                this.log = this.response;
                window.location.href="/#/";
              })
              .catch(e => {
                this.errorLogout= e.response.data.message;
                alert(e.message);
              });
        },
        gotosearch(){
            window.location.href = "/#/bookSession"
        },
        gotoSessions(){
            window.location.href ="/#/viewsessions"
        },
        gotoReview(){
            window.location.href ="/#/writereview"
        },
        gotoCourseRequest(){
            window.location.href = "/#/requestCourse"
        },
    }
}