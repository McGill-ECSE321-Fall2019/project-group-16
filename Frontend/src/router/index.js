import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import createAccount from '@/components/createAccount'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/account',
      name: 'createAccount',
      component: createAccount
    }
  ]
})
