<template>
  <div class="painel-wrapper">
    <!-- HEADER -->
    <header class="painel-header">
      <div class="painel-header-content">
        <div class="header-titulo">
          <span class="header-icone">📊</span>
          <div>
            <h1>Painel Administrativo</h1>
            <p>{{ editando ? "Editar Atividade" : "Adicionar Nova Atividade" }}</p>
          </div>
        </div>
        <button @click="logout" class="btn-logout">
          <span>🚪</span> Sair
        </button>
      </div>
    </header>

    <!-- CONTAINER PRINCIPAL -->
    <main class="painel-main">
      <div class="painel-card">
        <form @submit.prevent="publicar" class="painel-formulario">

          <!-- CAMPO TÍTULO -->
          <div class="form-group">
            <label class="form-label">Título da Atividade</label>
            <input
              v-model="titulo"
              type="text"
              placeholder="Ex: Workshop de Programação"
              class="form-input"
              required
            />
          </div>

          <!-- CAMPO DATA -->
          <div class="form-group">
            <label class="form-label">Data</label>
            <input
              v-model="dataNoticia"
              type="date"
              class="form-input"
              required
            />
          </div>

          <!-- CAMPO DESCRIÇÃO -->
          <div class="form-group">
            <label class="form-label">Descrição</label>
            <textarea
              v-model="descricao"
              placeholder="Descreva os detalhes da atividade..."
              class="form-textarea"
              rows="6"
              required
            ></textarea>
          </div>

          <!-- CAMPO IMAGEM -->
          <div class="form-group">
            <label class="form-label">Upload de Imagem</label>
            <div class="file-upload">
              <input
                type="file"
                @change="uploadImagem"
                accept="image/*"
                class="file-input"
                id="file-input"
              />
              <label for="file-input" class="file-label">
                <span class="file-icon">📸</span>
                <span class="file-text">{{ file ? file.name : 'Clique para selecionar imagem' }}</span>
              </label>
            </div>
          </div>

          <!-- BOTÕES -->
          <div class="form-actions">
            <button type="submit" class="btn-publicar">
              <span>✓</span> {{ editando ? 'Atualizar' : 'Publicar' }}
            </button>
          </div>

          <!-- MENSAGEM DE SUCESSO -->
          <transition name="fade">
            <div v-if="publicado" class="sucesso-msg">
              <span class="sucesso-icon">✅</span>
              <span>Atividade salva com sucesso!</span>
            </div>
          </transition>

        </form>
      </div>
    </main>
  </div>
</template>

<script>
export default {

  data() {
    return {
      titulo: '',
      dataNoticia: '',
      descricao: '',
      file: null,
      publicado: false,
      editando: false
    }
  },

  methods: {

    uploadImagem(e) {
      this.file = e.target.files[0]
    },

    async publicar() {
      try {
        const formData = new FormData()
        formData.append("titulo", this.titulo)
        formData.append("descricao", this.descricao)
        formData.append("data", this.dataNoticia)

        if (this.file) {
          formData.append("foto", this.file)
        }

        const token = localStorage.getItem("token")

        const response = await fetch("http://localhost:8080/atividades", {
          method: "POST",
          headers: {
            "Authorization": `Bearer ${token}`
          },
          body: formData
        })

        if (!response.ok) {
          const erro = await response.text()
          throw new Error(erro || "Erro ao salvar")
        }

        const text = await response.text()
        const data = text ? JSON.parse(text) : {}
        console.log("Salvo:", data)

        this.publicado = true
        setTimeout(() => { this.publicado = false }, 3000)

        this.titulo = ''
        this.dataNoticia = ''
        this.descricao = ''
        this.file = null

      } catch (e) {
        alert("Erro ao salvar: " + e.message)
      }
    },

    logout() {
      localStorage.removeItem("logado")
      localStorage.removeItem("usuario")
      localStorage.removeItem("token")
      this.$router.push("/admin")
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

.painel-wrapper {
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  font-family: 'Poppins', sans-serif;
}

.painel-header {
  background: linear-gradient(135deg, #ff8a3d 0%, #ff6a00 100%);
  color: white;
  padding: 24px 40px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
  position: sticky;
  top: 0;
  z-index: 100;
}

.painel-header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
}

.header-titulo {
  display: flex;
  align-items: center;
  gap: 16px;
}

.header-icone {
  font-size: 32px;
  display: block;
}

.painel-header h1 {
  font-size: 28px;
  font-weight: 700;
  margin-bottom: 4px;
}

.painel-header p {
  font-size: 13px;
  opacity: 0.9;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.btn-logout {
  display: flex;
  align-items: center;
  gap: 8px;
  background: rgba(255, 255, 255, 0.2);
  color: white;
  border: 2px solid rgba(255, 255, 255, 0.4);
  padding: 10px 20px;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  font-size: 14px;
}

.btn-logout:hover {
  background: rgba(255, 255, 255, 0.3);
  border-color: white;
  transform: translateY(-2px);
}

.painel-main {
  max-width: 700px;
  margin: 40px auto;
  padding: 0 20px;
}

.painel-card {
  background: white;
  border-radius: 16px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.12);
  padding: 40px;
  animation: slideUp 0.6s ease-out;
}

@keyframes slideUp {
  from { opacity: 0; transform: translateY(30px); }
  to { opacity: 1; transform: translateY(0); }
}

.painel-formulario {
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

.form-input,
.form-textarea {
  padding: 14px 16px;
  border: 2px solid #e8e8e8;
  border-radius: 10px;
  font-size: 16px;
  font-family: 'Poppins', sans-serif;
  transition: all 0.3s ease;
  background: #fafafa;
  color: #333;
}

.form-input:focus,
.form-textarea:focus {
  outline: none;
  border-color: #ff8a3d;
  background: white;
  box-shadow: 0 0 0 4px rgba(255, 138, 61, 0.1);
}

.form-input::placeholder,
.form-textarea::placeholder {
  color: #ccc;
}

.form-textarea {
  resize: vertical;
  min-height: 120px;
}

.file-upload {
  position: relative;
}

.file-input {
  display: none;
}

.file-label {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 14px 16px;
  border: 2px dashed #ff8a3d;
  border-radius: 10px;
  background: #fff9f5;
  cursor: pointer;
  transition: all 0.3s ease;
  font-size: 14px;
  color: #666;
}

.file-label:hover {
  background: #fff5f0;
  border-color: #ff6a00;
}

.file-icon {
  font-size: 20px;
}

.file-text {
  font-weight: 500;
}

.form-actions {
  display: flex;
  gap: 12px;
  margin-top: 8px;
}

.btn-publicar {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
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
  box-shadow: 0 10px 25px rgba(255, 138, 61, 0.2);
}

.btn-publicar:hover {
  transform: translateY(-2px);
  box-shadow: 0 15px 35px rgba(255, 138, 61, 0.3);
}

.btn-publicar:active {
  transform: translateY(0);
}

.sucesso-msg {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 14px 16px;
  background: #d4edda;
  border: 1px solid #c3e6cb;
  border-radius: 8px;
  color: #155724;
  font-weight: 500;
  animation: slideDown 0.3s ease-out;
}

@keyframes slideDown {
  from { opacity: 0; transform: translateY(-10px); }
  to { opacity: 1; transform: translateY(0); }
}

.sucesso-icon {
  font-size: 18px;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

@media (max-width: 600px) {
  .painel-header {
    padding: 16px 20px;
  }

  .painel-header-content {
    flex-direction: column;
    gap: 16px;
    align-items: flex-start;
  }

  .btn-logout {
    width: 100%;
    justify-content: center;
  }

  .painel-card {
    padding: 24px 16px;
  }

  .form-input,
  .form-textarea {
    padding: 12px 14px;
    font-size: 14px;
  }

  .btn-publicar {
    padding: 12px 20px;
    font-size: 14px;
  }

  .painel-header h1 {
    font-size: 20px;
  }
}
</style>