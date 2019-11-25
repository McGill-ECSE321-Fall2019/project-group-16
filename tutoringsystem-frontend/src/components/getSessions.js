import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


function SessionDto(name) {
  this.name = name
}

export default{
  name: 'sessions',
  data () {
    return {
      sessions: [],
      newSession: '',
      selectedSessions: '',
      errorSession: '',
    }
  },
  methods : {
      getSessions: function () {
              // Initializing people from backend
                AXIOS.get(`/currentsesh`)
                .then(response => {
                  // JSON responses are automatically parsed.
                  this.sessions = response.data
                  this.errorSession =''
                })
                .catch(e => {
                  this.errorSession = e.response.data.message;
                });
        },
        deleteSession(id){
              AXIOS.post('/session/delete/'+ id)
              .then(response => {
                // JSON responses are automatically parsed.
                this.sessions = response.data
                this.errorSession =''
              })
              .catch(e => {
                this.errorSession = e.response.data.message;
              });

        },
        
}






}
