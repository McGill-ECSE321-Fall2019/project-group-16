import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/components/Welcome'
import About from '@/components/About'
import Contact from '@/components/Contact'
import Searchresult from '@/components/Searchresult'
import Selection from '@/components/Selection'
import WriteReview from '@/components/WriteReview'
import CreateAccount from '@/components/CreateAccount'
import Home from '@/components/Home'
import Sesh from '@/components/Sesh'
import TutorProfile from '@/components/TutorProfile'

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
    },{
      path: '/writereview',
      name: 'Writereview',
      component: WriteReview
    },
    {
      path: '/account',
      name: 'CreateAccount',
      component: CreateAccount
    },
    {
      path: '/viewsessions',
      name: 'Sesh',
      component: Sesh,
    },{
      path: '/tutorProfile',
      name: 'tutorProfile',
      component: TutorProfile,
    }
  ]
})
