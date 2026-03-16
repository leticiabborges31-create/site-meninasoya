import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import Admin from '../views/Admin.vue'
import Painel from '../views/Painel.vue'   // ← ADICIONE ISSO

const router = createRouter({

history: createWebHistory(),

routes: [

{
path: '/',
name: 'home',
component: HomeView
},

{
path: '/admin',
name: 'admin',
component: Admin
},

{
path: '/painel',      // ← NOVA ROTA
name: 'painel',
component: Painel
}

]

})

export default router