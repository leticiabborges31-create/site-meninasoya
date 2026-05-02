<template>
  <div class="login-wrapper">
    <div class="login-card">
      <!-- HEADER -->
      <div class="login-header">
        <div class="login-logo">MO</div>
        <h1 class="login-titulo">Meninas Oyá</h1>
        <p class="login-subtitulo">Acesso ao Painel</p>
      </div>

      <!-- FORMULÁRIO -->
      <form @submit.prevent="login" class="login-form">
        <div v-if="erro" class="erro-msg">{{ erro }}</div>

        <div class="form-group">
          <label class="form-label">Email ou usuário</label>
          <input 
            v-model="loginUsername" 
            type="text"
            placeholder="seu@email.com ou ADMIN"
            class="form-input"
            required
          />
        </div>

        <div class="form-group">
          <label class="form-label">Senha</label>
          <input 
            v-model="loginpassword" 
            type="password" 
            placeholder="••••••••"
            class="form-input"
            required
          />
        </div>

        <button type="submit" class="btn-login">Entrar</button>
      </form>

      <!-- RODAPÉ -->
      <div class="login-footer">
        <p class="login-text">Não tem conta? 
          <button @click="$router.push('/cadastro')" class="btn-link">Criar conta</button>
        </p>
      </div>
    </div>

    <!-- DECORAÇÃO -->
    <div class="login-decor"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginUsername: '',
      loginpassword: '',
      erro: ''
    }
  },

  methods: {
    async login() {
      this.erro = ''
      try {
        const response = await fetch("http://localhost:8080/auth", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            username: this.loginUsername.trim().toLowerCase(),
            password: this.loginpassword
          })
        })

        if (!response.ok) {
          const body = await response.json().catch(() => ({}))
          if (response.status === 403) {
            this.erro = body.message || 'Cadastro pendente ou rejeitado. Aguarde a aprovação.'
          } else {
            this.erro = 'Email ou senha inválidos.'
          }
          return
        }

        const data = await response.json()
        const token = data.accessToken || data.accesstoken
        localStorage.setItem("token", token)
        localStorage.setItem("logado", "true")
        localStorage.setItem("usuario", JSON.stringify({ username: this.loginUsername }))

        // Decodifica o JWT para descobrir a role
        const payload = JSON.parse(atob(token.split('.')[1]))
        const authorities = payload.authorities || ''
        if (authorities.includes('ADMIN')) {
          this.$router.push('/painel-admin')
        } else {
          this.$router.push('/painel')
        }

      } catch (error) {
        console.error("Erro no login:", error)
        this.erro = 'Erro ao conectar com o servidor.'
      }
    }
  }
}
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.login-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  position: relative;
  overflow: hidden;
}

.login-decor {
  position: absolute;
  width: 400px;
  height: 400px;
  background: rgba(255, 138, 61, 0.1);
  border-radius: 50%;
  top: -100px;
  right: -100px;
  animation: float 6s ease-in-out infinite;
}

@keyframes float {
  0%, 100% { transform: translateY(0px); }
  50% { transform: translateY(-20px); }
}

.login-card {
  background: white;
  border-radius: 16px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.12);
  padding: 60px 40px;
  width: 100%;
  max-width: 420px;
  position: relative;
  z-index: 10;
  animation: slideUp 0.6s ease-out;
}

@keyframes slideUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* HEADER */
.login-header {
  text-align: center;
  margin-bottom: 40px;
}

.login-logo {
  width: 60px;
  height: 60px;
  background: linear-gradient(135deg, #ff8a3d 0%, #ff6a00 100%);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 28px;
  font-weight: 700;
  margin: 0 auto 20px;
  box-shadow: 0 10px 25px rgba(255, 138, 61, 0.2);
}

.login-titulo {
  font-size: 32px;
  font-weight: 700;
  color: #1a1a1a;
  margin-bottom: 8px;
}

.login-subtitulo {
  font-size: 14px;
  color: #999;
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

/* FORMULÁRIO */
.login-form {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-label {
  font-size: 14px;
  font-weight: 600;
  color: #333;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.form-input {
  padding: 14px 16px;
  border: 2px solid #e8e8e8;
  border-radius: 10px;
  font-size: 16px;
  font-family: inherit;
  transition: all 0.3s ease;
  background: #fafafa;
}

.form-input:focus {
  outline: none;
  border-color: #ff8a3d;
  background: white;
  box-shadow: 0 0 0 4px rgba(255, 138, 61, 0.1);
}

.form-input::placeholder {
  color: #ccc;
}

.erro-msg {
  background: #fee2e2;
  color: #b91c1c;
  border: 1px solid #fca5a5;
  border-radius: 8px;
  padding: 10px 14px;
  font-size: 0.9rem;
  margin-bottom: 12px;
}

/* BOTÃO LOGIN */
.btn-login {
  padding: 14px 24px;
  background: linear-gradient(135deg, #ff8a3d 0%, #ff6a00 100%);
  color: white;
  border: none;
  border-radius: 10px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  margin-top: 10px;
  box-shadow: 0 10px 25px rgba(255, 138, 61, 0.2);
}

.btn-login:hover {
  transform: translateY(-2px);
  box-shadow: 0 15px 35px rgba(255, 138, 61, 0.3);
}

.btn-login:active {
  transform: translateY(0);
}

/* RODAPÉ */
.login-footer {
  text-align: center;
  margin-top: 30px;
  padding-top: 24px;
  border-top: 1px solid #f0f0f0;
}

.login-text {
  font-size: 14px;
  color: #666;
}

.btn-link {
  background: none;
  border: none;
  color: #ff8a3d;
  font-weight: 600;
  cursor: pointer;
  text-decoration: none;
  transition: all 0.3s ease;
  margin-left: 4px;
}

.btn-link:hover {
  color: #ff6a00;
  text-decoration: underline;
}

/* RESPONSIVO */
@media (max-width: 600px) {
  .login-card {
    padding: 40px 24px;
    margin: 20px;
  }

  .login-titulo {
    font-size: 24px;
  }

  .login-logo {
    width: 50px;
    height: 50px;
    font-size: 24px;
  }

  .form-input {
    padding: 12px 14px;
    font-size: 14px;
  }

  .btn-login {
    padding: 12px 20px;
    font-size: 14px;
  }
}
</style>
