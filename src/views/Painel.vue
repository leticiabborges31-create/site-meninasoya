<template>
  <PainelShell titulo="Painel do Professor" @logout="logout">
    <template #icon>
      <svg class="icon-header" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
        <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
        <circle cx="9" cy="7" r="4"/>
        <path d="M23 21v-2a4 4 0 0 0-3-3.87"/>
        <path d="M16 3.13a4 4 0 0 1 0 7.75"/>
      </svg>
    </template>

    <div class="secao-card">
      <div class="secao-header">
        <svg class="icon-secao" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <line x1="12" y1="5" x2="12" y2="19"/>
          <line x1="5" y1="12" x2="19" y2="12"/>
        </svg>
        <h2 class="secao-titulo">{{ editando ? 'Editar Atividade' : 'Nova Atividade' }}</h2>
      </div>

      <form @submit.prevent="publicar">
        <div class="form-grid">
          <div class="form-group">
            <label>Título</label>
            <input v-model="titulo" type="text" placeholder="Título da atividade" class="form-input" required />
          </div>
          <div class="form-group">
            <label>Data</label>
            <input v-model="dataNoticia" type="date" class="form-input" required />
          </div>
          <div class="form-group full">
            <label>Descrição</label>
            <textarea v-model="descricao" placeholder="Descrição detalhada..." class="form-textarea" rows="4" required></textarea>
          </div>
          <div class="form-group full">
            <label>Imagem</label>
            <div class="file-upload">
              <input type="file" @change="uploadImagem" accept="image/*" class="file-input" id="foto-atividade" />
              <label for="foto-atividade" class="file-label">
                <svg class="icon-file" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"/>
                  <polyline points="17 8 12 3 7 8"/>
                  <line x1="12" y1="3" x2="12" y2="15"/>
                </svg>
                {{ file ? file.name : 'Clique para selecionar imagem' }}
              </label>
            </div>
          </div>
        </div>

        <button type="submit" class="btn-salvar">
          <svg class="icon-btn" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="20 6 9 17 4 12"/>
          </svg>
          {{ editando ? 'Atualizar Atividade' : 'Publicar Atividade' }}
        </button>
      </form>

      <div v-if="publicado" class="sucesso-msg">
        <svg class="icon-msg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <polyline points="20 6 9 17 4 12"/>
        </svg>
        Atividade salva com sucesso!
      </div>
    </div>
  </PainelShell>
</template>

<script>
import PainelShell from '@/components/PainelShell.vue'

export default {
  components: { PainelShell },
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

  mounted() {
    if (localStorage.getItem("logado") !== "true") {
      this.$router.push("/admin")
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
        if (this.file) formData.append("foto", this.file)

        const token = localStorage.getItem("token")
        const response = await fetch("http://localhost:8080/atividades", {
          method: "POST",
          headers: { "Authorization": `Bearer ${token}` },
          body: formData
        })

        if (!response.ok) {
          const erro = await response.text()
          throw new Error(erro || "Erro ao salvar")
        }

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
@import url('https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700&display=swap');

*, *::before, *::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.admin-wrapper {
  min-height: 100vh;
  background:
    radial-gradient(circle at top left, rgba(240, 112, 48, 0.14), transparent 30%),
    linear-gradient(180deg, #fff7f1 0%, #f7fbf5 100%);
  color: #1a1a18;
  font-family: inherit;
}

/* HEADER */
.admin-header {
  position: sticky;
  top: 0;
  z-index: 50;
  background: rgba(26, 58, 22, 0.96);
  backdrop-filter: blur(10px);
  border-bottom: 1px solid rgba(255, 255, 255, 0.08);
}

.admin-header-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1.4rem 1.5rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 1rem;
}

.header-titulo {
  display: flex;
  align-items: center;
  gap: 0.9rem;
}
<style scoped>
/* Estilos locais — wrapper/header/logout ficam no PainelShell */
.icon-header { width: 2rem; height: 2rem; color: #f07030; }

.icon-btn,
.icon-secao,
.icon-msg,
.icon-file {
  width: 1.15rem;
  height: 1.15rem;
  flex-shrink: 0;
}

.secao-card {
  background: rgba(255, 255, 255, 0.94);
  border: 1px solid rgba(45, 90, 39, 0.1);
  border-radius: 1.5rem;
  padding: 1.5rem;
  box-shadow: 0 18px 45px rgba(26, 58, 22, 0.08);
}

.secao-header {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  margin-bottom: 1.25rem;
}

.icon-secao { color: #d95f1c; }

.secao-titulo {
  font-size: 1.2rem;
  color: #1a3a16;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 1rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.45rem;
}

.form-group.full { grid-column: 1 / -1; }

.form-group label {
  font-size: 0.9rem;
  font-weight: 700;
  color: #32502c;
}

.form-input,
.form-textarea {
  width: 100%;
  padding: 0.95rem 1rem;
  border: 1px solid rgba(45, 90, 39, 0.14);
  border-radius: 1rem;
  background: #fffdfa;
  color: #1a1a18;
  font: inherit;
  transition: 0.2s ease;
}

.form-input:focus,
.form-textarea:focus {
  outline: none;
  border-color: rgba(217, 95, 28, 0.55);
  box-shadow: 0 0 0 4px rgba(217, 95, 28, 0.1);
  background: #fff;
}

.form-textarea {
  resize: vertical;
  min-height: 130px;
}

.file-upload { position: relative; }

.file-input {
  position: absolute;
  opacity: 0;
  pointer-events: none;
}

.file-label {
  display: inline-flex;
  align-items: center;
  gap: 0.65rem;
  width: 100%;
  min-height: 56px;
  padding: 0.95rem 1rem;
  border: 1.5px dashed rgba(217, 95, 28, 0.45);
  border-radius: 1rem;
  background: #fff6ef;
  color: #8b4b22;
  cursor: pointer;
  transition: 0.2s ease;
  font-family: inherit;
}

.file-label:hover {
  background: #fff0e5;
  border-color: #d95f1c;
}

.btn-salvar {
  margin-top: 1.25rem;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 0.55rem;
  padding: 0.95rem 1.25rem;
  border: none;
  border-radius: 999px;
  background: linear-gradient(135deg, #1a3a16, #2d5a27);
  color: #fff;
  font-weight: 700;
  cursor: pointer;
  transition: 0.2s ease;
  font-family: inherit;
  font-size: 1rem;
}

.btn-salvar:hover {
  transform: translateY(-1px);
  box-shadow: 0 14px 30px rgba(26, 58, 22, 0.18);
}

.sucesso-msg {
  margin-top: 1rem;
  display: inline-flex;
  align-items: center;
  gap: 0.55rem;
  padding: 0.85rem 1rem;
  border-radius: 1rem;
  background: #edf8ec;
  border: 1px solid rgba(45, 90, 39, 0.16);
  color: #1a3a16;
  font-weight: 600;
}

@media (max-width: 600px) {
  .form-grid { grid-template-columns: 1fr; }
  .form-group.full { grid-column: 1; }
}
</style>