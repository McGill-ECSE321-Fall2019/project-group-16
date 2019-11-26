<template>
    <div>
        <br>
            <li v-for="tutor in tutors" v-bind:key="tutor">
                <h1>
                Tutor's name: {{tutor.name}}
                </h1>
            </li>
        <br>
        <h3>Reviews:</h3>
            <li v-for="review in reviews" v-bind:key="review">
                <div id="review" class=card>
                <td>Review #{{review.id}}</td>
                <td>Rating: {{review.rating}}/5</td>
                <td>Comments: {{review.review}}</td>
                </div>
            </li>
        <br>
        <h3>Courses taught:</h3><br>
        <li v-for="course in courses" v-bind:key="course">
            <div id="review" class=card>
            <td>CourseCode: {{course.courseCode}}</td>
            <td>Subject: {{course.subject}}</td>
            <td>University: {{course.university}}</td>
            <td><b-button
            type="button"
            variant="outline-info"
            v-on:click="bookSession(course.CourseCode)"
            >Book Session</b-button></td>
            </div>
        </li>
    </div>
    
    
    
</template>
<script src="./tutorProfile.js">
</script>
<style>
#review {
    width:30%;
    max-height: auto;
    min-width: 550px;
    margin:auto;
    margin-top:15px;
    padding:15px;
    text-align: center;
}
#name {
    width:30%;
    max-height: auto;
    min-width: 550px;
    margin:auto;
    margin-top:15px;
    padding:15px;
    text-align: center;
}
</style>