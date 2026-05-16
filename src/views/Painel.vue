<template>
  <PainelShell titulo="Painel do Professor" @logout="logout">
    <template #nav>
      <!-- Dashboard -->
      <button class="nav-item" :class="{ ativo: abaAtiva === 'dashboard' }" @click="abaAtiva = 'dashboard'">
        <svg class="icon-aba" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <rect x="3" y="3" width="7" height="7" rx="1"/>
          <rect x="14" y="3" width="7" height="7" rx="1"/>
          <rect x="3" y="14" width="7" height="7" rx="1"/>
          <rect x="14" y="14" width="7" height="7" rx="1"/>
        </svg>
        <span class="nav-label">Dashboard</span>
      </button>
      <!-- Atividades -->
      <button class="nav-item" :class="{ ativo: abaAtiva === 'atividades' }" @click="abaAtiva = 'atividades'; carregarMinhasAtividades()">
        <svg class="icon-aba" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <rect x="3" y="3" width="18" height="18" rx="2"/>
          <path d="M9 11h6M9 15h6"/>
        </svg>
        <span class="nav-label">Atividades</span>
      </button>
      <!-- Meu Perfil -->
      <button class="nav-item" :class="{ ativo: abaAtiva === 'perfil' }" @click="abaAtiva = 'perfil'; carregarPerfil()">
        <svg class="icon-aba" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
          <circle cx="12" cy="7" r="4"/>
        </svg>
        <span class="nav-label">Meu Perfil</span>
      </button>
    </template>
    <template #icon>
      <svg class="icon-header" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
        <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
        <circle cx="9" cy="7" r="4"/>
        <path d="M23 21v-2a4 4 0 0 0-3-3.87"/>
        <path d="M16 3.13a4 4 0 0 1 0 7.75"/>
      </svg>
    </template>

    <!-- â”€â”€ DASHBOARD â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ -->
    <div v-if="abaAtiva === 'dashboard'">
      <div class="dash-grid">
        <!-- Status -->
        <div class="dash-card">
          <div class="dash-card-icon ic-status">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/>
              <polyline points="22 4 12 14.01 9 11.01"/>
            </svg>
          </div>
          <div class="dash-card-info">
            <span class="dash-label">Status do Cadastro</span>
            <span class="status-badge" :class="statusClass">{{ perfilStatus || '—' }}</span>
          </div>
        </div>

        <!-- Total Atividades -->
        <div class="dash-card">
          <div class="dash-card-icon ic-ativ">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <rect x="3" y="3" width="18" height="18" rx="2"/>
              <path d="M9 11h6M9 15h6"/>
            </svg>
          </div>
          <div class="dash-card-info">
            <span class="dash-label">Atividades Publicadas</span>
            <span class="dash-valor">{{ minhasAtividades.length }}</span>
          </div>
        </div>

        <!-- IdentificaÃ§Ã£o -->
        <div class="dash-card dash-card-wide">
          <div class="dash-card-icon ic-perfil">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
              <circle cx="12" cy="7" r="4"/>
            </svg>
          </div>
          <div class="dash-card-info">
            <span class="dash-label">Identificação</span>
            <span class="dash-valor-nome">{{ perfilNome || '—' }}</span>
            <span class="dash-sub">{{ perfilEmail }}</span>
            <span v-if="perfilUf" class="dash-sub">{{ perfilEscola || 'Sem escola vinculada' }} · {{ perfilUf }}</span>
          </div>
        </div>

        <!-- Lattes -->
        <div class="dash-card dash-card-wide">
          <div class="dash-card-icon ic-lattes">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M10 13a5 5 0 0 0 7.54.54l3-3a5 5 0 0 0-7.07-7.07l-1.72 1.71"/>
              <path d="M14 11a5 5 0 0 0-7.54-.54l-3 3a5 5 0 0 0 7.07 7.07l1.71-1.71"/>
            </svg>
          </div>
          <div class="dash-card-info">
            <span class="dash-label">Currículo Lattes</span>
            <a v-if="perfilLattes" :href="perfilLattes" target="_blank" rel="noopener" class="lattes-link">Ver Lattes</a>
            <span v-else class="dash-sub">Não cadastrado</span>
          </div>
        </div>
      </div>
    </div>

    <!-- â”€â”€ ATIVIDADES â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ -->
    <div v-else-if="abaAtiva === 'atividades'">
      <!-- Lista de atividades -->
      <div class="secao-card mb-md">
        <div class="secao-header">
          <svg class="icon-secao" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M9 5H7a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V7a2 2 0 0 0-2-2h-2"/>
            <rect x="9" y="3" width="6" height="4" rx="1"/>
          </svg>
          <h2 class="secao-titulo">Minhas Atividades</h2>
        </div>

        <div v-if="carregandoAtividades" class="loading-msg">Carregando...</div>
        <div v-else-if="minhasAtividades.length === 0" class="vazio-msg">Nenhuma atividade publicada ainda.</div>
        <div v-else class="table-wrapper">
          <table class="ativ-table">
            <thead>
              <tr>
                <th>Título</th>
                <th>Data</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="a in minhasAtividades" :key="a.id">
                <td class="ativ-titulo">{{ a.titulo }}</td>
                <td class="ativ-data">{{ formatarData(a.data) }}</td>
                <td class="ativ-acoes">
                  <button class="btn-apagar" @click="deletarAtividade(a.id)" title="Excluir atividade">
                    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <polyline points="3 6 5 6 21 6"/>
                      <path d="M19 6l-1 14a2 2 0 0 1-2 2H8a2 2 0 0 1-2-2L5 6"/>
                      <path d="M10 11v6M14 11v6"/>
                    </svg>
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- FormulÃ¡rio nova atividade -->
      <div class="secao-card">
        <div class="secao-header">
          <svg class="icon-secao" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="12" y1="5" x2="12" y2="19"/>
            <line x1="5" y1="12" x2="19" y2="12"/>
          </svg>
          <h2 class="secao-titulo">Nova Atividade</h2>
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
            Publicar Atividade
          </button>
        </form>

        <transition name="fade">
          <div v-if="publicado" class="sucesso-msg">
            <svg class="icon-msg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="20 6 9 17 4 12"/>
            </svg>
            Atividade publicada com sucesso!
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
    </div>

    <!-- â”€â”€ MEU PERFIL â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ -->
    <div v-else-if="abaAtiva === 'perfil'">
      <div class="secao-card">
        <div class="secao-header">
          <svg class="icon-secao" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
            <circle cx="12" cy="7" r="4"/>
          </svg>
          <h2 class="secao-titulo">Meu Perfil</h2>
        </div>

        <form @submit.prevent="salvarPerfil">
          <div class="form-grid">
            <div class="form-group">
              <label>Nome</label>
              <input v-model="editandoPerfil.nome" type="text" class="form-input" placeholder="Seu nome completo" />
            </div>
            <div class="form-group">
              <label>UF</label>
              <select v-model="editandoPerfil.uf" class="form-input">
                <option value="">Selecione</option>
                <option v-for="u in ufs" :key="u" :value="u">{{ u }}</option>
              </select>
            </div>
            <div class="form-group">
              <label>E-mail</label>
              <input v-model="editandoPerfil.email" type="email" class="form-input" placeholder="seu@email.com" />
            </div>
            <div class="form-group">
              <label>Nova Senha (opcional)</label>
              <input v-model="editandoPerfil.senha" type="password" class="form-input" placeholder="Mínimo 8 caracteres" autocomplete="new-password" />
            </div>
            <div class="form-group">
              <label>Escola / Instituição</label>
              <input v-model="editandoPerfil.escola" type="text" class="form-input" placeholder="Nome da sua instituição" />
            </div>
            <div class="form-group">
              <label>Idade</label>
              <input v-model.number="editandoPerfil.idade" type="number" class="form-input" min="18" max="100" />
            </div>
            <div class="form-group full">
              <label>Link do Currículo Lattes</label>
              <input v-model="editandoPerfil.linkCurriculoLattes" type="url" class="form-input" placeholder="http://lattes.cnpq.br/..." />
            </div>
          </div>

          <button type="submit" class="btn-salvar">
            <svg class="icon-btn" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="20 6 9 17 4 12"/>
            </svg>
            Salvar Alterações
          </button>
        </form>

        <transition name="fade">
          <div v-if="perfilSalvo" class="sucesso-msg">
            <svg class="icon-msg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="20 6 9 17 4 12"/>
            </svg>
            Perfil atualizado com sucesso!
          </div>
        </transition>

        <transition name="fade">
          <div v-if="erroPerfilMsg" class="erro-msg">
            <svg class="icon-msg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/>
            </svg>
            {{ erroPerfilMsg }}
          </div>
        </transition>
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
      abaAtiva: 'dashboard',

      // professor
      professorId: null,
      perfilNome: '',
      perfilEmail: '',
      perfilIdade: null,
      perfilUf: '',
      perfilEscola: '',
      perfilLattes: '',
      perfilStatus: '',

      // editar perfil
      editandoPerfil: { nome: '', email: '', senha: '', idade: null, uf: '', escola: '', linkCurriculoLattes: '' },
      perfilSalvo: false,
      erroPerfilMsg: '',

      // atividades
      minhasAtividades: [],
      carregandoAtividades: false,

      // form nova atividade
      titulo: '',
      dataNoticia: '',
      descricao: '',
      temLocalizacao: false,
      localizacao: '',
      file: null,
      file2: null,
      publicado: false,
      erroMsg: '',

      ufs: ['AC','AL','AM','AP','BA','CE','DF','ES','GO','MA','MG','MS','MT','PA','PB','PE','PI','PR','RJ','RN','RO','RR','RS','SC','SE','SP','TO']
    }
  },

  computed: {
    statusClass() {
      const s = this.perfilStatus
      if (s === 'APROVADO') return 'badge-aprovado'
      if (s === 'REJEITADO') return 'badge-rejeitado'
      return 'badge-pendente'
    }
  },

  mounted() {
    if (localStorage.getItem("logado") !== "true") {
      this.$router.push("/admin")
      return
    }
    this.carregarDashboard()
  },

  methods: {
    token() {
      return localStorage.getItem("token")
    },

    async carregarDashboard() {
      try {
        const [profRes, ativRes] = await Promise.all([
          fetch("http://localhost:8080/api/professores/me", {
            headers: { "Authorization": `Bearer ${this.token()}` }
          }),
          fetch("http://localhost:8080/atividades/minhas", {
            headers: { "Authorization": `Bearer ${this.token()}` }
          })
        ])
        if (profRes.ok) {
          const prof = await profRes.json()
          this.professorId = prof.id
          this.perfilNome = prof.nome
          this.perfilEmail = prof.email
          this.perfilIdade = prof.idade
          this.perfilUf = prof.uf
          this.perfilEscola = prof.escola
          this.perfilLattes = prof.linkCurriculoLattes
          this.perfilStatus = prof.status
        }
        if (ativRes.ok) {
          this.minhasAtividades = await ativRes.json()
        }
      } catch (e) {
        console.error("Erro ao carregar dashboard:", e)
      }
    },

    async carregarMinhasAtividades() {
      this.carregandoAtividades = true
      try {
        const res = await fetch("http://localhost:8080/atividades/minhas", {
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        if (res.ok) this.minhasAtividades = await res.json()
      } finally {
        this.carregandoAtividades = false
      }
    },

    async carregarPerfil() {
      if (!this.professorId) await this.carregarDashboard()
      this.editandoPerfil = {
        nome: this.perfilNome,
        email: this.perfilEmail,
        senha: '',
        idade: this.perfilIdade,
        uf: this.perfilUf,
        escola: this.perfilEscola,
        linkCurriculoLattes: this.perfilLattes
      }
    },

    async deletarAtividade(id) {
      if (!confirm("Deseja excluir esta atividade?")) return
      try {
        const res = await fetch(`http://localhost:8080/atividades/${id}`, {
          method: "DELETE",
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        if (res.ok) {
          this.minhasAtividades = this.minhasAtividades.filter(a => a.id !== id)
        }
      } catch (e) {
        console.error("Erro ao deletar:", e)
      }
    },

    async publicar() {
      try {
        const formData = new FormData()
        formData.append("titulo", this.titulo)
        formData.append("descricao", this.descricao)
        formData.append("data", this.dataNoticia)
        if (this.temLocalizacao && this.localizacao) formData.append("localizacao", this.localizacao)
        if (this.file) formData.append("foto", this.file)
        if (this.file2) formData.append("foto2", this.file2)

        const res = await fetch("http://localhost:8080/atividades", {
          method: "POST",
          headers: { "Authorization": `Bearer ${this.token()}` },
          body: formData
        })

        if (!res.ok) {
          const body = await res.json().catch(() => null)
          this.erroMsg = body?.message || body?.error || "Erro ao salvar a atividade."
          setTimeout(() => { this.erroMsg = '' }, 5000)
          return
        }

        const nova = await res.json()
        this.minhasAtividades.push(nova)
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
        this.erroMsg = "Erro de conexÃ£o: " + e.message
        setTimeout(() => { this.erroMsg = '' }, 5000)
      }
    },

    async salvarPerfil() {
      try {
        const payload = { ...this.editandoPerfil }
        if (!payload.senha) delete payload.senha

        const res = await fetch(`http://localhost:8080/api/professores/${this.professorId}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${this.token()}`
          },
          body: JSON.stringify(payload)
        })

        if (!res.ok) {
          const body = await res.json().catch(() => null)
          this.erroPerfilMsg = body?.message || body?.error || "Erro ao salvar perfil."
          setTimeout(() => { this.erroPerfilMsg = '' }, 5000)
          return
        }

        const prof = await res.json()
        this.perfilNome = prof.nome
        this.perfilEmail = prof.email
        this.perfilIdade = prof.idade
        this.perfilUf = prof.uf
        this.perfilEscola = prof.escola
        this.perfilLattes = prof.linkCurriculoLattes
        this.erroPerfilMsg = ''
        this.perfilSalvo = true
        setTimeout(() => { this.perfilSalvo = false }, 4000)
      } catch (e) {
        this.erroPerfilMsg = "Erro de conexÃ£o: " + e.message
        setTimeout(() => { this.erroPerfilMsg = '' }, 5000)
      }
    },

    formatarData(d) {
      if (!d) return '—'
      const [y, m, day] = d.split('-')
      return `${day}/${m}/${y}`
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
.icon-header { width: 1.75rem; height: 1.75rem; color: var(--oya-glow); }

/* â”€â”€ NAV SIDEBAR â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ */
.nav-item {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  width: 100%;
  padding: 0.7rem 0.85rem;
  border: none;
  border-radius: var(--radius-md);
  background: transparent;
  color: rgba(168, 213, 192, 0.65);
  font-size: 0.875rem;
  font-weight: 500;
  cursor: pointer;
  transition: background 0.2s, color 0.2s;
  text-align: left;
  font-family: var(--font-body);
}
.nav-item:hover { background: rgba(255,255,255,0.07); color: var(--oya-mint); }
.nav-item.ativo  { background: rgba(217,79,30,0.18); color: var(--oya-warm); }
.nav-label { flex: 1; }
.icon-aba  { width: 1rem; height: 1rem; flex-shrink: 0; }

/* â”€â”€ DASHBOARD â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ */
.dash-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 1rem;
}

.dash-card {
  display: flex;
  align-items: flex-start;
  gap: 1rem;
  background: #fff;
  border: 0.5px solid var(--oya-fog);
  border-radius: var(--radius-lg);
  padding: 1.25rem 1.5rem;
  box-shadow: 0 2px 12px rgba(26, 58, 42, 0.05);
}

.dash-card-wide { grid-column: 1 / -1; }

.dash-card-icon {
  width: 2.5rem;
  height: 2.5rem;
  border-radius: var(--radius-md);
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}
.dash-card-icon svg { width: 1.2rem; height: 1.2rem; }

.ic-status  { background: rgba(107, 170, 138, 0.12); color: var(--oya-sage); }
.ic-ativ    { background: rgba(217, 79, 30, 0.10);  color: var(--oya-ember); }
.ic-perfil  { background: rgba(26, 58, 42, 0.08);   color: var(--oya-forest); }
.ic-lattes  { background: rgba(100, 149, 237, 0.12); color: #4a7cc5; }

.dash-card-info {
  display: flex;
  flex-direction: column;
  gap: 0.3rem;
  min-width: 0;
}

.dash-label {
  font-size: 0.7rem;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  color: var(--oya-stone);
  font-weight: 600;
}

.dash-valor {
  font-size: 1.75rem;
  font-weight: 700;
  color: var(--oya-forest);
  font-family: var(--font-display);
  line-height: 1;
}

.dash-valor-nome {
  font-size: 1rem;
  font-weight: 600;
  color: var(--oya-forest);
  font-family: var(--font-display);
}

.dash-sub {
  font-size: 0.82rem;
  color: var(--oya-stone);
}

.status-badge {
  display: inline-flex;
  align-items: center;
  padding: 0.25rem 0.75rem;
  border-radius: 2rem;
  font-size: 0.75rem;
  font-weight: 700;
  letter-spacing: 0.06em;
  width: fit-content;
}

.badge-aprovado  { background: rgba(107, 170, 138, 0.15); color: #2e7d52; }
.badge-pendente  { background: rgba(220, 170, 50, 0.18);  color: #8a6400; }
.badge-rejeitado { background: rgba(217, 79, 30, 0.12);   color: var(--oya-ember); }

.lattes-link {
  font-size: 0.85rem;
  color: #4a7cc5;
  text-decoration: none;
  font-weight: 500;
}
.lattes-link:hover { text-decoration: underline; }

/* â”€â”€ ATIVIDADES TABLE â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ */
.mb-md { margin-bottom: 1.25rem; }

.table-wrapper { overflow-x: auto; }

.ativ-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 0.875rem;
}

.ativ-table th {
  text-align: left;
  padding: 0.55rem 0.75rem;
  font-size: 0.7rem;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.07em;
  color: var(--oya-stone);
  border-bottom: 1.5px solid var(--oya-fog);
}

.ativ-table td {
  padding: 0.75rem;
  border-bottom: 1px solid rgba(0,0,0,0.04);
  color: var(--oya-char);
  vertical-align: middle;
}

.ativ-table tr:last-child td { border-bottom: none; }
.ativ-table tr:hover td { background: rgba(26, 58, 42, 0.02); }

.ativ-titulo { font-weight: 500; }
.ativ-data   { color: var(--oya-stone); white-space: nowrap; }
.ativ-acoes  { width: 3rem; text-align: right; }

.btn-apagar {
  background: transparent;
  border: none;
  padding: 0.35rem;
  cursor: pointer;
  color: var(--oya-stone);
  border-radius: var(--radius-md);
  transition: color 0.15s, background 0.15s;
  display: inline-flex;
}
.btn-apagar svg { width: 1rem; height: 1rem; }
.btn-apagar:hover { color: var(--oya-ember); background: rgba(217, 79, 30, 0.08); }

.loading-msg,
.vazio-msg {
  padding: 1.5rem;
  text-align: center;
  color: var(--oya-stone);
  font-size: 0.875rem;
}

/* â”€â”€ SHARED FORM STYLES â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ */
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
  box-sizing: border-box;
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
  .dash-grid { grid-template-columns: 1fr; }
  .dash-card-wide { grid-column: 1; }
  .form-grid { grid-template-columns: 1fr; }
  .form-group.full { grid-column: 1; }
}

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
  .dash-grid { grid-template-columns: 1fr; }
  .dash-card-wide { grid-column: 1; }
  .form-grid { grid-template-columns: 1fr; }
  .form-group.full { grid-column: 1; }
}
</style>
