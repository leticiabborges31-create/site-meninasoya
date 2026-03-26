<template>
  <div class="login-container">
    <h2>Login</h2>

    <input v-model="loginEmail" placeholder="Email" />
    <input type="password" v-model="loginSenha" placeholder="Senha" />

    <button @click="login">Entrar</button>

    <p>
      Não tem conta?
      <button @click="$router.push('/cadastro')">
        Criar conta
      </button>
    </p>
  </div>
</template>

<script>
export default {

  data(){
    return{
      loginEmail:'',
      loginSenha:''
    }
  },

  methods:{
    async login(){
      try {

        console.log("Tentando login...")

        const response = await fetch(
          `http://localhost:8080/usuarios/buscar?email=${this.loginEmail}`
        )

        if(!response.ok){
          alert("Usuário não encontrado")
          return
        }

        const usuario = await response.json()

        if(usuario.senha === this.loginSenha){

          // ✅ SALVA CORRETAMENTE
          localStorage.setItem("logado", "true")
          localStorage.setItem("usuario", JSON.stringify(usuario))

          console.log("Login OK, indo pro painel...")

          // 🔥 REDIRECIONA
          this.$router.push("/painel")

        } else {
          alert("Senha incorreta")
        }

      } catch (error) {
        alert("Erro no login")
        console.error(error)
      }
    }
  }
}
</script>

<style scoped>
.login-container {
  max-width: 300px;
  margin: auto;
  padding: 20px;
  display: flex;
  flex-direction: column;
}

input {
  margin-bottom: 10px;
  padding: 8px;
}

button {
  padding: 10px;
  background: orange;
  border: none;
  color: white;
  cursor: pointer;
}
</style>