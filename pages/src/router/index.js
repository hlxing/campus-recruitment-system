import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/components/homepage'
import InfoDetail from '@/components/infodetail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/detail',
      name: 'InfoDetail',
      component: InfoDetail
    }
  ]
})
