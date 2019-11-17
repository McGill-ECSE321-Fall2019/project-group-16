<template>
   <div class="review">
        <h5>Review Tutor</h5>
        <img id="reviewImg" src="../assets/review.svg" alt="">
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
#reviewImg {
  margin: 20px;
  width: 200px;
  height: 200px;
}
</style>