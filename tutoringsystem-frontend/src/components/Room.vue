<template>
  <div>
    <h1>Room Assignment</h1>
    <body>
    <form>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<label rn="rnum">Room Number:</label>
    	<input type="text" id="rnum" name="rnum">
    	<button>Search</button>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<label rl="rloc">Room Location:</label>
    	<input type="text" id="rloc" name="rloc">
    	<button>Search</button>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<label rc="rcap">Room Capacity:</label>
    	<input type="text" id="rcap" name="rcap">
    	<button>Search</button>
    </form>
    <p></p>
    	<h3>Room 1</h3>
    	<table class="a">
    		<tr>
    			<th>Course Session</th>
    			<th>Start Time</th>
    			<th>End Time</th>
    			<th>Change Start Time</th>
    			<th>Change End Time</th>
    			<th>Delete</th>
    		</tr>
    		<tr>
<!--    			<td>{{roomAssignment.aCourseSession}}</td>	
    			<td>{{roomAssignment.aStartTime}}</td>
    			<td>{{roomAssignment.aEndTime}}</td> -->
    			<td></td>
    			<td></td>
    			<td></td>
    			<td> 
    				<select>
    					<option value="0">Select time:</option>
    					<option value="1">9:00</option>
    					<option value="2">10:00</option>
    					<option value="3">11:00</option>
    					<option value="4">12:00</option>
    					<option value="5">13:00</option>
    					<option value="6">14:00</option>
    					<option value="7">15:00</option>
    					<option value="8">16:00</option>
    					<option value="9">17:00</option>
    				</select>
    				<button style="font-size:10px;height:20px;width:50px">Change</button>
    			</td>
    			<td>
    				<select>
    					<option value="0">Select time:</option>
    					<option value="1">9:00</option>
    					<option value="2">10:00</option>
    					<option value="3">11:00</option>
    					<option value="4">12:00</option>
    					<option value="5">13:00</option>
    					<option value="6">14:00</option>
    					<option value="7">15:00</option>
    					<option value="8">16:00</option>
    					<option value="9">17:00</option>
    				</select>
    				<button style="font-size:10px;height:20px;width:50px">Change</button>
    			</td>
    			<td><button style="font-size:15px;height:30px;width:70px">Delete</button></td>
    		</tr>
    	</table>
    
        <p></p>
    	<h3>Room 2</h3>
    	<table class="a">
    		<tr>
    			<th>Course Session</th>
    			<th>Start Time</th>
    			<th>End Time</th>
    			<th>Change Start Time</th>
    			<th>Change End Time</th>
    			<th>Delete</th>
    		</tr>
    		<tr>
<!--    			<td>{{roomAssignment.aCourseSession}}</td>	
    			<td>{{roomAssignment.aStartTime}}</td>
    			<td>{{roomAssignment.aEndTime}}</td> -->
    			<td></td>
    			<td></td>
    			<td></td>
    			<td> 
    				<select>
    					<option value="0">Select time:</option>
    					<option value="1">9:00</option>
    					<option value="2">10:00</option>
    					<option value="3">11:00</option>
    					<option value="4">12:00</option>
    					<option value="5">13:00</option>
    					<option value="6">14:00</option>
    					<option value="7">15:00</option>
    					<option value="8">16:00</option>
    					<option value="9">17:00</option>
    				</select>
    				<button style="font-size:10px;height:20px;width:50px">Change</button>
    			</td>
    			<td>
    				<select>
    					<option value="0">Select time:</option>
    					<option value="1">9:00</option>
    					<option value="2">10:00</option>
    					<option value="3">11:00</option>
    					<option value="4">12:00</option>
    					<option value="5">13:00</option>
    					<option value="6">14:00</option>
    					<option value="7">15:00</option>
    					<option value="8">16:00</option>
    					<option value="9">17:00</option>
    				</select>
    				<button style="font-size:10px;height:20px;width:50px">Change</button>
    			</td>
    			<td><button style="font-size:15px;height:30px;width:70px">Delete</button></td>
    		</tr>
    	</table>
    	
        <p></p>
    	<h3>Room 3</h3>
    	<table class="a">
    		<tr>
    			<th>Course Session</th>
    			<th>Start Time</th>
    			<th>End Time</th>
    			<th>Change Start Time</th>
    			<th>Change End Time</th>
    			<th>Delete</th>
    		</tr>
    		<tr>
<!--    			<td>{{roomAssignment.aCourseSession}}</td>	
    			<td>{{roomAssignment.aStartTime}}</td>
    			<td>{{roomAssignment.aEndTime}}</td> -->
    			<td></td>
    			<td></td>
    			<td></td>
    			<td> 
    				<select>
    					<option value="0">Select time:</option>
    					<option value="1">9:00</option>
    					<option value="2">10:00</option>
    					<option value="3">11:00</option>
    					<option value="4">12:00</option>
    					<option value="5">13:00</option>
    					<option value="6">14:00</option>
    					<option value="7">15:00</option>
    					<option value="8">16:00</option>
    					<option value="9">17:00</option>
    				</select>
    				<button style="font-size:10px;height:20px;width:50px">Change</button>
    			</td>
    			<td>
    				<select>
    					<option value="0">Select time:</option>
    					<option value="1">9:00</option>
    					<option value="2">10:00</option>
    					<option value="3">11:00</option>
    					<option value="4">12:00</option>
    					<option value="5">13:00</option>
    					<option value="6">14:00</option>
    					<option value="7">15:00</option>
    					<option value="8">16:00</option>
    					<option value="9">17:00</option>
    				</select>
    				<button style="font-size:10px;height:20px;width:50px">Change</button>
    			</td>
    			<td><button style="font-size:15px;height:30px;width:70px">Delete</button></td>
    		</tr>
    	</table>
    	
        <p></p>
    	<h3>Room 4</h3>
    	<table class="a">
    		<tr>
    			<th>Course Session</th>
    			<th>Start Time</th>
    			<th>End Time</th>
    			<th>Change Start Time</th>
    			<th>Change End Time</th>
    			<th>Delete</th>
    		</tr>
    		<tr>
<!--    			<td>{{roomAssignment.aCourseSession}}</td>	
    			<td>{{roomAssignment.aStartTime}}</td>
    			<td>{{roomAssignment.aEndTime}}</td> -->
    			<td></td>
    			<td></td>
    			<td></td>
    			<td> 
    				<select>
    					<option value="0">Select time:</option>
    					<option value="1">9:00</option>
    					<option value="2">10:00</option>
    					<option value="3">11:00</option>
    					<option value="4">12:00</option>
    					<option value="5">13:00</option>
    					<option value="6">14:00</option>
    					<option value="7">15:00</option>
    					<option value="8">16:00</option>
    					<option value="9">17:00</option>
    				</select>
    				<button style="font-size:10px;height:20px;width:50px">Change</button>
    			</td>
    			<td>
    				<select>
    					<option value="0">Select time:</option>
    					<option value="1">9:00</option>
    					<option value="2">10:00</option>
    					<option value="3">11:00</option>
    					<option value="4">12:00</option>
    					<option value="5">13:00</option>
    					<option value="6">14:00</option>
    					<option value="7">15:00</option>
    					<option value="8">16:00</option>
    					<option value="9">17:00</option>
    				</select>
    				<button style="font-size:10px;height:20px;width:50px">Change</button>
    			</td>
    			<td><button style="font-size:15px;height:30px;width:70px">Delete</button></td>
    		</tr>
    	</table>
    	
<!--    <table>
    	<tr></tr>
    	<tr>
    		<th></th>
    		<th>9:00</th>
    		<th>10:00</th>
    		<th>11:00</th>
    		<th>12:00</th>
    		<th>13:00</th>
    		<th>14:00</th>
    		<th>15:00</th>
    		<th>16:00</th>
    		<th>17:00</th>
    	</tr>
    	<tr>
    		<th>Room 1</th>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    	</tr>
    	<tr>
    		<th>Room 2</th>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    	</tr>
    	<tr>
    		<th>Room 3</th>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    	</tr>
    	<tr>
    		<th>Room 4</th>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    	</tr>
    </table>
    <p></p>
    <form>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<label all="all">Get all room assignment by start time:</label>
    	<select>
    		<option value="0">Select time:</option>
    		<option value="1">9:00</option>
    		<option value="2">10:00</option>
    		<option value="3">11:00</option>
    		<option value="4">12:00</option>
    		<option value="5">13:00</option>
    		<option value="6">14:00</option>
    		<option value="7">15:00</option>
    		<option value="8">16:00</option>
    		<option value="9">17:00</option>
    	</select>
    	    <button>Search</button>
    </form> -->
    <p></p>
    <h3>New Room Assignment</h3>
    <form>
    	<fieldset>
    		<br>
    		Room Number:
    		<select>
    		<option value="0">Select room:</option>
    		<option value="1">1</option>
    		<option value="2">2</option>
    		<option value="3">3</option>
    		<option value="4">4</option>
    		</select>
    		<br>
    		<p></p>
    		Start Time:
    		<select>
    		<option value="0">Select time:</option>
    		<option value="1">9:00</option>
    		<option value="2">10:00</option>
    		<option value="3">11:00</option>
    		<option value="4">12:00</option>
    		<option value="5">13:00</option>
    		<option value="6">14:00</option>
    		<option value="7">15:00</option>
    		<option value="8">16:00</option>
    		<option value="9">17:00</option>
    		</select>
    		<br>
    		<p></p>
    		End Time:
    		<select>
    		<option value="0">Select time:</option>
    		<option value="1">9:00</option>
    		<option value="2">10:00</option>
    		<option value="3">11:00</option>
    		<option value="4">12:00</option>
    		<option value="5">13:00</option>
    		<option value="6">14:00</option>
    		<option value="7">15:00</option>
    		<option value="8">16:00</option>
    		<option value="9">17:00</option>
    		</select>
    		<br>
    		<p></p>
    		Course Session:
    		<select>
    		<option value="0">Select session:</option>
    		<option value="1">1</option>
    		<option value="2">2</option>
    		<option value="3">3</option>
    		<option value="4">4</option>
    		</select>
    		<br>
    		<p></p>
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		<button style="height:30px;width:90px">Create</button>
    		<p></p>
    	</fieldset>
    </form>  	
    </body>
  </div>
</template>

<script>
export default {
  name: 'Room' //this is the name of the component
}
</script>
<style>
input {
	width: 10%;
}

button {
	width: 7%;
}

table, td, th {
	border: 1px solid #ddd;
	text-align: center;
}

table.a {
	table-layout: fixed;
	width: 95%;
	padding: 0px;
}

table {
	border-collapse: collapse;
	width: 90%;
	margin-left: 3%;
	margin-right:5%;
}

th, td {
	padding: 15px;
}

.custom-select {
  position: relative;
  font-family: Arial;
}

.custom-select select {
  display: none; /*hide original SELECT element:*/
}

/*hide the items when the select box is closed:*/
.select-hide {
  display: none;
}

.select-items div:hover, .same-as-selected {
  background-color: rgba(0, 0, 0, 0.1);
}

h3 {
	margin-left: 3%;
}

fieldset {	
	border: solid;
	width: 40%;
	margin-left: 3%;
}
</style>