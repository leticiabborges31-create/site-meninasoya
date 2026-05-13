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
          <div class="form-group">
            <label class="checkbox-label">
              <input type="checkbox" v-model="temLocalizacao" class="checkbox-input" />
              Tem localização?
            </label>
            <input
              v-if="temLocalizacao"
              v-model="localizacao"
              type="text"
              placeholder="Ex: UFMA-CCET, Sala 10"
              class="form-input mt-sm"
            />
          </div>
          <div class="form-group full">
            <label>Imagem 1</label>
            <div class="file-upload">
              <input type="file" @change="e => file = e.target.files[0]" accept="image/*" class="file-input" id="foto-atividade" />
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
          <div class="form-group full">
            <label>Imagem 2 (opcional)</label>
            <div class="file-upload">
              <input type="file" @change="e => file2 = e.target.files[0]" accept="image/*" class="file-input" id="foto2-atividade" />
              <label for="foto2-atividade" class="file-label">
                <svg class="icon-file" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"/>
                  <polyline points="17 8 12 3 7 8"/>
                  <line x1="12" y1="3" x2="12" y2="15"/>
                </svg>
                {{ file2 ? file2.name : 'Clique para selecionar segunda imagem' }}
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

      <transition name="fade">
        <div v-if="publicado" class="sucesso-msg">
          <svg class="icon-msg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="20 6 9 17 4 12"/>
          </svg>
          Atividade publicada com sucesso! Ela já aparece na lista de atividades.
        </div>
      </transition>

      <transition name="fade">
        <div v-if="erroMsg" class="erro-msg">
          <svg class="icon-msg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/>
          </svg>
          {{ erroMsg }}
        </div>
      </transition>
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
      temLocalizacao: false,
      localizacao: '',
      file: null,
      file2: null,
      publicado: false,
      editando: false,
      erroMsg: ''
    }
  },

  mounted() {
    if (localStorage.getItem("logado") !== "true") {
      this.$router.push("/admin")
    }
  },

  methods: {
    async publicar() {
      try {
        const formData = new FormData()
        formData.append("titulo", this.titulo)
        formData.append("descricao", this.descricao)
        formData.append("data", this.dataNoticia)
        if (this.temLocalizacao && this.localizacao) formData.append("localizacao", this.localizacao)
        if (this.file) formData.append("foto", this.file)
        if (this.file2) formData.append("foto2", this.file2)

        const token = localStorage.getItem("token")
        const response = await fetch("http://localhost:8080/atividades", {
          method: "POST",
          headers: { "Authorization": `Bearer ${token}` },
          body: formData
        })

        if (!response.ok) {
          const body = await response.json().catch(() => null)
          const msg = body?.message || body?.error || "Erro ao salvar a atividade. Tente novamente."
          this.erroMsg = msg
          setTimeout(() => { this.erroMsg = '' }, 5000)
          return
        }

        this.erroMsg = ''
        this.publicado = true
        setTimeout(() => { this.publicado = false }, 4000)
        this.titulo = ''
        this.dataNoticia = ''
        this.descricao = ''
        this.temLocalizacao = false
        this.localizacao = ''
        this.file = null
        this.file2 = null
      } catch (e) {
        this.erroMsg = "Erro de conexão: " + e.message
        setTimeout(() => { this.erroMsg = '' }, 5000)
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
/* Estilos locais — wrapper/header/logout ficam no PainelShell */
.icon-header { width: 1.75rem; height: 1.75rem; color: var(--oya-glow); }

.icon-btn,
.icon-secao,
.icon-msg,
.icon-file {
  width: 1.1rem;
  height: 1.1rem;
  flex-shrink: 0;
}

.secao-card {
  background: #fff;
  border: 0.5px solid var(--oya-fog);
  border-radius: var(--radius-lg);
  padding: 1.5rem;
  box-shadow: 0 4px 24px rgba(26, 58, 42, 0.06);
}

.secao-header {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  margin-bottom: 1.25rem;
}

.icon-secao { color: var(--oya-ember); }

.secao-titulo {
  font-family: var(--font-display);
  font-size: 1.15rem;
  color: var(--oya-forest);
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 1rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.4rem;
}

.form-group.full { grid-column: 1 / -1; }

.form-group label {
  font-size: 0.78rem;
  font-weight: 500;
  color: var(--oya-stone);
}

.checkbox-label {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.78rem !important;
  font-weight: 500;
  color: var(--oya-stone);
  cursor: pointer;
}

.checkbox-input {
  width: 1rem;
  height: 1rem;
  accent-color: var(--oya-ember);
  cursor: pointer;
}

.mt-sm { margin-top: 0.4rem; }

.form-input,
.form-textarea {
  width: 100%;
  padding: 0.9rem 1rem;
  border: 1.5px solid var(--oya-fog);
  border-radius: var(--radius-md);
  background: #FAFAF8;
  color: var(--oya-char);
  font-family: var(--font-body);
  font-size: 14px;
  transition: border-color 0.2s, box-shadow 0.2s;
  outline: none;
}

.form-input:focus,
.form-textarea:focus {
  border-color: rgba(217, 79, 30, 0.5);
  box-shadow: 0 0 0 3px rgba(217, 79, 30, 0.08);
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
  padding: 0.9rem 1rem;
  border: 1.5px dashed rgba(217, 79, 30, 0.35);
  border-radius: var(--radius-md);
  background: var(--oya-sand);
  color: var(--oya-stone);
  cursor: pointer;
  transition: background 0.2s, border-color 0.2s;
  font-family: var(--font-body);
  font-size: 14px;
}

.file-label:hover {
  background: rgba(217, 79, 30, 0.06);
  border-color: var(--oya-ember);
}

.btn-salvar {
  margin-top: 1.25rem;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 0.55rem;
  padding: 0.9rem 1.5rem;
  border: none;
  border-radius: var(--radius-pill);
  background: var(--oya-ember);
  color: #fff;
  font-weight: 500;
  font-size: 0.875rem;
  cursor: pointer;
  transition: background 0.2s, transform 0.2s, box-shadow 0.2s;
  font-family: var(--font-body);
}

.btn-salvar:hover {
  background: var(--oya-flame);
  transform: translateY(-1px);
  box-shadow: 0 10px 28px rgba(217, 79, 30, 0.25);
}

.sucesso-msg {
  margin-top: 1rem;
  display: flex;
  align-items: flex-start;
  gap: 0.55rem;
  padding: 0.9rem 1.1rem;
  border-radius: var(--radius-md);
  background: rgba(107, 170, 138, 0.12);
  border: 1px solid rgba(74, 122, 98, 0.25);
  color: var(--oya-sage);
  font-weight: 500;
  font-size: 0.9rem;
}

.erro-msg {
  margin-top: 1rem;
  display: flex;
  align-items: flex-start;
  gap: 0.55rem;
  padding: 0.9rem 1.1rem;
  border-radius: var(--radius-md);
  background: rgba(217, 79, 30, 0.08);
  border: 1px solid rgba(217, 79, 30, 0.2);
  color: var(--oya-ember);
  font-weight: 500;
  font-size: 0.9rem;
}

.fade-enter-active, .fade-leave-active { transition: opacity 0.3s; }
.fade-enter-from, .fade-leave-to { opacity: 0; }

@media (max-width: 600px) {
  .form-grid { grid-template-columns: 1fr; }
  .form-group.full { grid-column: 1; }
}
</style>