<template>
  <div class="admin-container">

    <h2>Cadastro</h2>

    <input v-model="nome" placeholder="Nome completo" />
    <input v-model="email" placeholder="Email" />
    <input type="password" v-model="senha" placeholder="Senha" />

    <button @click="cadastrar">Cadastrar</button>

    <p v-if="mensagemCadastro" class="mensagem-sucesso">{{ mensagemCadastro }}</p>
    <p v-if="erroCadastro" class="mensagem-erro">{{ erroCadastro }}</p>

    <hr />

    <h2>Login</h2>

    <input v-model="loginEmail" placeholder="Email" />
    <input type="password" v-model="loginSenha" placeholder="Senha" />

    <button @click="login">Entrar</button>

    <p v-if="erroLogin" class="mensagem-erro">{{ erroLogin }}</p>

  </div>
</template>

<script>
import api from '../service/api.js'

export default {
  data() {
    return {
      nome: '',
      email: '',
      senha: '',
      loginEmail: '',
      loginSenha: '',
      mensagemCadastro: '',
      erroCadastro: '',
      erroLogin: ''
    }
  },

  methods: {
    async cadastrar() {
      this.erroCadastro = ''
      this.mensagemCadastro = ''

      if (!this.nome || !this.email || !this.senha) {
        this.erroCadastro = 'Preencha todos os campos.'
        return
      }

      try {
        await api.post('/usuarios', {
          nome: this.nome,
          email: this.email,
          senha: this.senha
        })
        this.mensagemCadastro = 'Cadastro realizado com sucesso!'
        this.nome = ''
        this.email = ''
        this.senha = ''
      } catch {
        this.erroCadastro = 'Erro ao cadastrar. Tente novamente.'
      }
    },

    async login() {
      this.erroLogin = ''

      if (!this.loginEmail || !this.loginSenha) {
        this.erroLogin = 'Preencha email e senha.'
        return
      }

      try {
        const response = await api.post('/usuarios/login', {
          email: this.loginEmail,
          senha: this.loginSenha
        })

        if (response.data === true) {
          localStorage.setItem('logado', 'true')
          this.$router.push('/painel')
        } else {
          this.erroLogin = 'Email ou senha incorretos.'
        }
      } catch {
        this.erroLogin = 'Email ou senha incorretos.'
      }
    }
  }
}
</script>
