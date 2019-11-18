<template>
  <div>
    <h2>Frequently Asked Questions</h2>
    <img id="faq" src="../assets/conversation.svg" alt="" width="200" height="200">
    <ul class="list-group list-group-flush">
      <li class="list-group-item"><b>How do we pay for the session?</b> To keep things simple, you can pay the tutor using cash or e-transfer in person after your session ends.</li>
      <li class="list-group-item"><b>Can we cancel a session after it is confirmed?</b> A session can be cancelled upto 24 hours before it is scheduled.</li>
      <li class="list-group-item"><b>Are reviews anonymous?</b> Yes, the reviews are anonymous.</li>
      <li class="list-group-item"><b>Can't find a specific course?</b> You can contact us to request the course, we are working on adding more courses.</li>
    </ul>
    <br>
    <h2>Have other questions? Contact Us: </h2> 
    <h4>Email : {{email}}</h4>
    <h4>Phone : {{phone}}</h4>
  </div>
</template>

<script>
export default {
  name: 'Contact',
  data(){
    return {
      email: 'contact@tutoringsystem.com',
      phone: '1 514 XXX XXXX'
    }
  }
}
</script>

<style scoped>
#faq{
  margin-bottom: 20px;
}
</style>