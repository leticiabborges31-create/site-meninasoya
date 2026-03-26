<template>
  <main class="container">
    <h2>Cadastro</h2>

    <form @submit.prevent="cadastrar">
      <input v-model="nome" type="text" placeholder="Nome" required />
      <input v-model="email" type="email" placeholder="Email" required />
      <input v-model="senha" type="password" placeholder="Senha" required />

      <button type="submit">Cadastrar</button>
    </form>

    <p v-if="mensagem">{{ mensagem }}</p>
  </main>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()

const nome = ref('')
const email = ref('')
const senha = ref('')
const mensagem = ref('')

const cadastrar = async () => {
  try {
    const response = await axios.post('http://localhost:8080/usuarios', {
      nome: nome.value,
      email: email.value,
      senha: senha.value
    })

    // ✅ mensagem
    mensagem.value = "Cadastro realizado com sucesso!"

    // ✅ salva login automático
    localStorage.setItem("logado", "true")
    localStorage.setItem("usuario", JSON.stringify(response.data))

    // limpar campos
    nome.value = ''
    email.value = ''
    senha.value = ''

    // 🔥 redireciona pro painel
    setTimeout(() => {
      router.push('/painel')
    }, 1000)

  } catch (error) {
    mensagem.value = "Erro ao cadastrar!"
    console.error(error)
  }
}
</script>

<style scoped>
.container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
}

input {
  display: block;
  width: 100%;
  margin: 10px 0;
  padding: 8px;
}

button {
  padding: 10px;
  width: 100%;
  background: orange;
  color: white;
  border: none;
  cursor: pointer;
}

p {
  margin-top: 10px;
}
</style>