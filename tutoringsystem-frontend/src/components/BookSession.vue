<template>
<span class="container">
   
<div id="table">
  <h6>How does it work?</h6>
  <table class="table table-bordered">
    <tr>
      <td>Step 1</td>
      <td>Step 2</td>
      <td>Step 3</td>
      <td>Step 4</td>
      <td>Step 5</td>
    </tr>
    <tr>
      <td>Select your university</td>
      <td>Select the course you want help with</td>
      <td>The list of tutors is now updated! Select a tutor</td>
      <td>Select Date & Time for the session</td>
      <td>Book Session!</td>
    </tr>
  </table>
</div>

<!-- <div>
    <div id="selectUni">
     <label>Choose University: </label> <br>
     <select class='form-control' id='session-university-select' v-model="selectedUniversity" @change="generateCourses(selectedUniversity.name)">
		       		  <option disabled value="">Please select a university</option>
		        		<option v-for="uni in universities" v-bind:value="uni" v-bind:key="uni">{{university.name}}</option>
	</select>
    </div>
</div> -->

<!-- <b-button @click="getUniversities()" variant="outline-info">Get Universities</b-button> -->
 <!-- <br><br> -->

<div class="form-group">
  <h5>Select University</h5>
  <div id="subImg">
        <img src="../assets/books.svg" alt="" width="10%" height="10%">
    </div>
  <div id="selectUni">
  <select class="form-control" v-model="selectedUniversity" @change="getCourses(selectedUniversity.name)">
    <option value="" selected disabled hidden>Choose here</option>
    <option v-for="university in universities" v-bind:value="{name : university.name}" v-bind:key="university">
      {{ university.name }}
    </option>
  </select>
  </div>

  <h5>Select Course</h5>
  <div id="img">
        <img src="../assets/seo.svg" alt="" width="10%" height="10%">
    </div>
  <div id="selectCourse">
  <select class="form-control"  v-model="selectedCourse"  @change="getTutors(selectedCourse.code)">
    <option value="" selected disabled hidden>Choose here</option>
    <option v-for="course in courses" v-bind:value="{code : course.courseCode}" v-bind:key="course">{{course.courseCode}}</option>
  </select>
  </div>
  <div>
  <h5>Select Tutor</h5>
  <div>
    <img src="../assets/teacher.svg" alt="" width="10%" height="10%">
  </div>
  <div id="selectTutor">
  <select class="form-control"  v-model="selectedTutor" >
    <option value="" selected disabled hidden>Choose here</option>
     <option v-for="tutor in tutors" v-bind:value="{username: tutor.username, name : tutor.name , rate: tutor.hourlyRate}" v-bind:key="tutor">{{tutor.name}}</option>
  </select>
  </div>
  </div> 
</div> 


 <!-- <b-button id = "tutorProfileButton" variant="outline-info" v-on:click="gotoProfile">View Tutor Profile</b-button> -->
<b-button id="ViewTutorProfileButton" variant="outline-info" v-on:click="gotoTutorProfile(selectedTutor.username)">Check Tutor Profile</b-button>
<br>
<br>

<div>
  <h5>Select Date & Time for Session</h5>
  <div id= "timeInputs" class="form-group">  
    <label for="date">Date:</label>
<input class="form-control" type="date" v-model="mydate" />
 <label for="time">Start Time:</label>
<input class="form-control" type="time" v-model="startTime" />
 <label for="date">End Time:</label>
<input class="form-control" type="time" v-model="endTime" />
  </div>
<div id="GroupSession" class="form-group">
  <input type="checkbox" id="checkbox" v-model="isGroupSession">
  <label for="checkbox">This is a group session</label>
</div>
 <br>
<b-button id="bookSessionButton" variant="outline-info" v-on:click="bookSession(selectedTutor.username,startTime,endTime,mydate,selectedCourse.code,isGroupSession), gotoSessions()">Book Session</b-button>
</div>
</span>

</template>

<script src="./searchUniversities.js">
</script>


<style scoped>

#table{
  margin-left: 20px;
  margin-right: 20px;
  position: center;
}
#selectUni {
  margin-right: 50px; 
  margin-left: 50px;
  padding: 10px;
  max-width: 100%;
  margin-bottom: 10px;
}
#selectCourse {
  margin-right: 50px; 
  margin-left: 50px;
  padding: 10px;
  max-width: 100%;
  margin-bottom: 10px;
}
#selectTutor {
  margin-right: 50px; 
  margin-left: 50px;
  padding: 10px;
  max-width: 100%;
  margin-bottom: 10px;
}
#tutorProfileButton {
  margin: 10px;
}
#sessionInputs {
  margin-right: 50px; 
  margin-left: 50px;
}
#timeInputs {
  margin-left: 500px;
  margin-right: 500px;
 position: center;
 
}
</style>