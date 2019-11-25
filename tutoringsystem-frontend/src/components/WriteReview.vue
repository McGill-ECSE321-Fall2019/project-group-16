<template>
   <div class="review">
        <h5>Review Tutor</h5>
        <img id="reviewImg" src="../assets/review.svg" alt="">
        <div class="selectTutor">  
        <label>Select Tutor :</label>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <b-button @click="getTutors()" variant="outline-info">Get Tutors</b-button>
        <br><br>
      <div class="form-group">

      <div id="selectTut">
      <select class="form-control" v-model="selectedTutor" @change="getTutors(selectedTutor.username)">
      <option value="" selected disabled hidden>Choose here</option>
      <option v-for="tutor in tutors" v-bind:value="{name : tutor.username}" v-bind:key="tutor">
      {{ tutor.username }}
          </option>
        </select>
      </div>
        </div>
        </div>

        <div class="rating">
        <label>Rating :</label>
        <select class="form-control" id="exampleFormControlSelect1">
          <option>1 : Poor</option>
          <option>2 : Below Average</option>
          <option>3 : Average</option>
          <option>4 : Good</option>
          <option>5 : Excellent</option>
        </select>
        </div>
        <div class="comments">
          <label for="">Comments: </label>
         <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
        <b-button id="reviewButton" variant="outline-info">Submit Review</b-button>
        <div>
          Note : For this page to work, we need to integrate with the Tutor ViewPoint
           </div>
    </div>
</template>

<style scoped>
.review {
  margin-right: 30px;
  margin-left: 30px;
}
.comments {
  margin: 20px;
}

#reviewButton {
  margin: 10px;
  float: right;
}
#refreshButton {
  margin: 10px;
  float: middle;
}
#reviewImg {
  margin: 20px;
  width: 200px;
  height: 200px;
}
</style>


