<template>
<span class="container">
   
<div id="table">
  <h6>All Current Sessions</h6>
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
      <td>{{ session.student }}</td>
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

<h6>Make sure to update the session list!</h6>

<b-button @click="getSessions()" variant="outline-info">Update Sessions</b-button>
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
#selectUni {
  margin-right: 50px; 
  margin-left: 50px;
  padding: 10px;
  max-width: 100%;
  margin-bottom: 10px;
}

</style>