<template>
<span class="container">


   
<div id="table">
  <h5>All Current & Pending Sessions</h5>
  <div id="subImg">
        <img src="../assets/session.png" alt="" width="10%" height="10%">
    </div>
  <table class="table table-bordered">
   <tr>
      <th>Session ID</th>
      <th>Student</th>
      <th>Tutor</th>
      <th>Room</th>
      <th>Course</th>
      <th>Date</th>
      <th>Start Time</th>
      <th>End Time</th>
      <th>Group Session</th>
      <th>Confirmed</th>
    </tr>
   <tr v-for="session in sessions" v-bind:key="session">
      <td>{{ session.id }}</td>
      <td>{{ session.student[0] }}</td>
      <td>{{ session.tutor }}</td>
      <td>{{ session.room }}</td>
      <td>{{ session.course }}</td>
      <td>{{ session.date }}</td>
      <td>{{ session.startTime }}</td>
      <td>{{ session.endTime }}</td>
      <td>{{ session.isGroupSession }}</td>
      <td>{{ session.isConfirmed }}</td>
  </tr>
<!-- ... -->
</table>
</div>
 <h5>Cancel a Session</h5>
   <div id="subImg">
        <img src="../assets/delete.png" alt="" width="10%" height="10%">
    </div>
 
<h7>Choose a session to cancel from the list below</h7>
   <div id="selectSession">
  <select class="form-control" v-model="selectedSession" @change="getSessions()">
    <option value="" selected disabled hidden>Choose here</option>
    <option v-for="session in sessions" v-bind:value="{name : session.id}" v-bind:key="session">
      {{ session.id }}
    </option>
  </select>
  </div>
  

<b-button @click="deleteSession(selectedSession.name)" variant="danger">Cancel Session</b-button>
 <br><br>

</span>
</template>

<script src="./getSessions.js">
</script>


<style scoped>

#table{
  margin-left: 20px;
  margin-right: 20px;
  position: center;
}
#selectSession {
  margin-right: 50px; 
  margin-left: 50px;
  padding: 10px;
  max-width: 100%;
  margin-bottom: 10px;
}


</style>