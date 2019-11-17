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
    </tr>
    <tr>
      <td>Click on Get Universities button to view the universities we offer</td>
      <td>Select your university</td>
      <td>Select the course you want help with</td>
      <td>Click on view tutors to view available tutors for the course!</td>
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

<b-button @click="getUniversities()" variant="outline-info">Get Universities</b-button>
 <br><br>

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
  <select class="form-control"  v-model="selectedCourse" >
    <option value="" selected disabled hidden>Choose here</option>
     <option v-for="course in courses" v-bind:value="{code : course.courseCode}" v-bind:key="course">{{course.courseCode}}</option>
  </select>
  </div>
</div> 
<div>
    <h6>Click the button to view all available tutors for the selected course!</h6>
    <b-button id = "tutorButton" variant="outline-info">View Tutors</b-button>
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
</style>