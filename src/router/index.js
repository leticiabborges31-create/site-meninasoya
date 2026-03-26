import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import Admin from '../views/Login.vue'
import Painel from '../views/Painel.vue'
import Cadastro from '../views/Cadastro.vue'
import Parceria from '../views/Parceria.vue' // 👈 NOVO

import AtividadeLista from '../views/AtividadeLista.vue'
import AtividadeDetalhe from '../views/AtividadeDetalhe.vue'

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
      path: '/cadastro',
      name: 'cadastro',
      component: Cadastro
    },

    {
      path: '/painel',
      name: 'painel',
      component: Painel
    },

    {
      path: '/parceria', // 👈 NOVA ROTA
      name: 'parceria',
      component: Parceria
    },

    {
      path: '/atividades',
      name: 'atividades',
      component: AtividadeLista
    },

    {
      path: '/atividade',
      name: 'atividade',
      component: AtividadeDetalhe
    }
  ]
})

/* 🔐 PROTEÇÃO DE ROTA */
router.beforeEach((to, from, next) => {
  const logado = localStorage.getItem("logado")

  if (to.path === "/painel" && logado !== "true") {
    next("/admin")
  } else {
    next()
  }
})

export default router