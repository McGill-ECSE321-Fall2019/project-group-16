<template>
  <div>
    <div id="header">
      <h2>Tutoring Service</h2>
    </div>
    <nav>
      <router-link class="spacing" v-for="routes in links" 
      v-bind:key="routes.id"
      :to="`${routes.page}`">{{routes.text}}</router-link>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'Navigation',
  data() {
    return {
      links: [
        {
          id: 0,
          text: 'Home',
          page:'/Home'
        },
        {
          id: 1,
          text: 'About',
          page:'/About'
        },
        {
          id: 2,
          text: 'Contact',
          page:'/Contact'
        },
        {
          id: 3,
          text: 'Update Account',
          page: '/updateAccount'
        }
      ]
    }
  }
}
</script>
<style>
.spacing {
  margin-right: 10px;
  font-size: 20px;
}
#header {
 background-color: rgb(12, 158, 158);
}

</style>