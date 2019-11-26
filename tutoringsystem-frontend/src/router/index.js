import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/components/Welcome'
import About from '@/components/About'
import Contact from '@/components/Contact'
import Searchresult from '@/components/Searchresult'
import Selection from '@/components/Selection'
import WriteReview from '@/components/WriteReview'
import RequestCourse from '@/components/RequestCourse'
import UpdateAccount from '@/components/UpdateAccount'
import Tutor from '@/components/Tutor'
import BookSession from '@/components/BookSession'

import CreateAccount from '@/components/CreateAccount'
import Home from '@/components/Home'
import Sesh from '@/components/Sesh'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'welcome',
      component: Welcome
    },
    {
      path: '/about',
      name: 'About',
      component: About
    },
    {
      path: '/contact',
      name: 'Contact',
      component: Contact
    },
    {
      path: '/searchresult',
      name: 'Searchresult',
      component: Searchresult
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/selection',
      name: 'Selection',
      component: Selection
    },
    {
      path: '/writereview',
      name: 'Writereview',
      component: WriteReview
    },
    {
      path: '/tutor/:username',
      name: 'Tutor',
      props: true,
      component: Tutor
    },
    {
      path: '/account',
      name: 'CreateAccount',
      component: CreateAccount
    },
    {
      path: '/viewsessions',
      name: 'Sesh',
      component: Sesh
    },
    {
      path: '/requestCourse',
      name: 'CourseRequest',
      component: RequestCourse
    },
    {
      path: '/updateAccount',
      name: 'UpdateAccount',
      component: UpdateAccount
    },
    {
      path: '/bookSession',
      name: 'BookSession',
      component: BookSession
    }
  ]
})
