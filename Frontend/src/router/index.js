import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import createAccount from '@/components/createAccount'
import home from '@/components/home'

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
    },
    {
      path: '/home',
      name: 'homePage',
      component: home
    }
  ]
})
