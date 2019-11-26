import axios from 'axios'
var config = require('../../config')


var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function PersonDto(name){
    this.name = name
    this.events = []
}
function EventDto (name, date, start, end) {
    this.name = name
    this.eventDate = date
    this.startTime = start
    this.endTime = end
}
function TutorDto(name, rate){
    this.name = name
    this.reviews = []
    this.courses = []
    this.rate = rate
}
function ReviewDto(id, review, rating){
    this.id = id
    this.review = review
    this.rating = rating
}
function CourseDto(courseCode, subject, university){
    this.courseCode = courseCode
    this.subject = subject
    this.university = university
}

export default {
    name: 'test',
    props: [
        'username',
    ],
    data() {
        return {
            name: "hi",
            tutor: "bonjour",
            student: {
                type: Object
            },
            tutor: {
                type: Object
            },
            errorTutor: '',

            reviews: [],
            newReview: '',
            selectedReviews: '',
            errorReview: '',

            courses: [],
            newCourse: '',
            selectedCourses: '',
            errorCourse: '',
        }
    },
    created: function() {
        this.name = this.username

        AXIOS.get('/tutorReview/tutor/' + username)
        .then(response => {
            this.reviews = response.data
            this.errorReview = ''
        })
        .catch(e => {
            this.errorReview = e.response.data.message;
        })

        AXIOS.get('/courses/tutor/' + username)
        .then(response => {
            this.courses = response.data
            this.errorCourse = ''
        })
        .catch(e => {
            this.errorCourse = e.response.data.message;
        })

        AXIOS.get('/tutor/' + username)
        .then(response => {
            this.tutor = response.data
            this.errorTutor = ''
        })
        .catch(e => {
            this.errorTutor = e.response.data.message;
        })
        // const p1 = new PersonDto('John')
        // const p2 = new PersonDto('Jill')
        // this.people = [p1, p2]

        // const t1 = new TutorDto('Bob', 14)

        // const r1 = new ReviewDto(1, 'good', 4)
        // const r2 = new ReviewDto(2, 'mediocre', 5)
        // const r3 = new ReviewDto(3, 'meh', 3)
        // this.reviews = [r1, r2, r3]

        // const c1 = new CourseDto('Math141', 'math', 'Mcgill')
        // const c2 = new CourseDto('Math263', 'ode', 'Mcgill')
        // this.courses = [c1, c2]

        // this.tutors.push(t1)
    },
    methods: {
        createTutor: function() {
            AXIOS.post(`/tutors/bob/bobuser/14.4`, {}, {})
            .then(response => {
                this.tutor = reponse.data
                this.name = "worked"
            })
            .catch(e => {
                var errorMsg = e.message
                console.log(errorMsg)
                this.name = "failed"
            });
            this.name = "trolls"
        },
        createPerson: function() {
            var p = new PersonDto("hallo")
            this.people.push(p)
            this.newPerson
        },
        bookSession: function(courseCode){
            this.goToViewSession()            
            //AXIOS.post(`/session/` + id + '/' + username + '/' + student.username + '')
        },
        goToViewSession: function() {
            Router.push({
                path:"/viewsessions",
                name: "Sesh"
            });
        }

    }
}
