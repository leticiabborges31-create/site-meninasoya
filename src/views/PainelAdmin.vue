<template>
  <div class="admin-wrapper">
    <!-- HEADER -->
    <header class="admin-header">
      <div class="admin-header-content">
        <div class="header-titulo">
          <svg class="icon-header" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M12 3v1m0 16v1m9-9h-1m-16 0H1M20.485 3.515l-.707.707M5.222 18.778l-.707.707M20.485 20.485l-.707-.707M5.222 5.222l-.707-.707M15 12a3 3 0 11-6 0 3 3 0 016 0z"/>
          </svg>
          <div>
            <h1>Painel Administrativo</h1>
            <p>Meninas Oyá</p>
          </div>
        </div>
        <button @click="logout" class="btn-logout">
          <svg class="icon-btn" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2z"/>
            <polyline points="16 11 12 15 8 11"/>
            <line x1="12" y1="4" x2="12" y2="15"/>
          </svg>
          Sair
        </button>
      </div>
    </header>
 
    <!-- ABAS -->
    <div class="abas">
      <button
        v-for="aba in abas"
        :key="aba.key"
        @click="abaAtiva = aba.key"
        :class="['btn-aba', { ativo: abaAtiva === aba.key }]"
      >
        <svg class="icon-aba" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <g v-if="aba.key === 'atividades'">
            <rect x="3" y="3" width="18" height="18" rx="2"/>
            <path d="M9 11h6M9 15h6"/>
          </g>
          <g v-if="aba.key === 'alunos'">
            <circle cx="12" cy="8" r="4"/>
            <path d="M6 21v-2a4 4 0 0 1 4-4h4a4 4 0 0 1 4 4v2"/>
          </g>
          <g v-if="aba.key === 'professores'">
            <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
            <circle cx="9" cy="7" r="4"/>
            <path d="M23 21v-2a4 4 0 0 0-3-3.87"/>
            <path d="M16 3.13a4 4 0 0 1 0 7.75"/>
          </g>
        </svg>
        {{ aba.label }}
      </button>
    </div>
 
    <main class="admin-main">
 
      <!-- ======= ABA ATIVIDADES ======= -->
      <div v-if="abaAtiva === 'atividades'">
        <div class="secao-card">
          <div class="secao-header">
            <svg class="icon-secao" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <line x1="12" y1="5" x2="12" y2="19"/>
              <line x1="5" y1="12" x2="19" y2="12"/>
            </svg>
            <h2 class="secao-titulo">Nova Atividade</h2>
          </div>
          <div class="form-grid">
            <div class="form-group">
              <label>Título</label>
              <input v-model="atividade.titulo" type="text" placeholder="Título da atividade" class="form-input" />
            </div>
            <div class="form-group">
              <label>Data</label>
              <input v-model="atividade.data" type="date" class="form-input" />
            </div>
            <div class="form-group full">
              <label>Descrição</label>
              <textarea v-model="atividade.descricao" placeholder="Descrição detalhada..." class="form-textarea" rows="4"></textarea>
            </div>
            <div class="form-group full">
              <label>Imagem</label>
              <div class="file-upload">
                <input type="file" @change="e => atividade.foto = e.target.files[0]" accept="image/*" class="file-input" id="foto-atividade" />
                <label for="foto-atividade" class="file-label">
                  <svg class="icon-file" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"/>
                    <polyline points="17 8 12 3 7 8"/>
                    <line x1="12" y1="3" x2="12" y2="15"/>
                  </svg>
                  {{ atividade.foto ? atividade.foto.name : 'Clique para selecionar imagem' }}
                </label>
              </div>
            </div>
          </div>
          <button @click="salvarAtividade" class="btn-salvar">
            <svg class="icon-btn" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2z"/>
              <polyline points="17 6 12 13 7 8"/>
            </svg>
            Publicar Atividade
          </button>
          <div v-if="sucessoAtividade" class="sucesso-msg">
            <svg class="icon-msg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="20 6 9 17 4 12"/>
            </svg>
            Atividade salva com sucesso!
          </div>
        </div>
      </div>
 
      <!-- ======= ABA ALUNOS ======= -->
      <div v-if="abaAtiva === 'alunos'">
        <div class="secao-card">
          <div class="secao-header">
            <svg class="icon-secao" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <line x1="12" y1="5" x2="12" y2="19"/>
              <line x1="5" y1="12" x2="19" y2="12"/>
            </svg>
            <h2 class="secao-titulo">Cadastrar Aluno</h2>
          </div>
          <div class="form-grid">
            <div class="form-group">
              <label>Nome</label>
              <input v-model="aluno.nome" type="text" placeholder="Nome completo" class="form-input" />
            </div>
            <div class="form-group">
              <label>Idade</label>
              <input v-model="aluno.idade" type="number" placeholder="Idade" class="form-input" />
            </div>
            <div class="form-group">
              <label>UF</label>
              <select v-model="aluno.uf" class="form-input">
                <option value="">Selecione o estado</option>
                <option value="MA">Maranhao</option>
                <option value="SP">Sao Paulo</option>
                <option value="CE">Ceara</option>
              </select>
            </div>
            <div class="form-group">
              <label>Escola</label>
              <input v-model="aluno.escola" type="text" placeholder="Nome da escola" class="form-input" />
            </div>
          </div>
          <button @click="salvarAluno" class="btn-salvar">
            <svg class="icon-btn" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2z"/>
              <polyline points="17 6 12 13 7 8"/>
            </svg>
            Cadastrar Aluno
          </button>
          <div v-if="sucessoAluno" class="sucesso-msg">
            <svg class="icon-msg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="20 6 9 17 4 12"/>
            </svg>
            Aluno cadastrado com sucesso!
          </div>
        </div>
 
        <!-- LISTA ALUNOS -->
        <div class="secao-card">
          <div class="secao-header">
            <svg class="icon-secao" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="12" cy="8" r="4"/>
              <path d="M6 21v-2a4 4 0 0 1 4-4h4a4 4 0 0 1 4 4v2"/>
            </svg>
            <h2 class="secao-titulo">Lista de Alunos</h2>
          </div>
          <div v-if="alunos.length === 0" class="vazio">
            <svg class="icon-vazio" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="11" cy="11" r="8"/>
              <path d="m21 21-4.35-4.35"/>
            </svg>
            Nenhum aluno cadastrado.
          </div>
          <table v-else class="tabela">
            <thead>
              <tr>
                <th>Nome</th>
                <th>Idade</th>
                <th>UF</th>
                <th>Escola</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="a in alunos" :key="a.id">
                <td>{{ a.nome }}</td>
                <td>{{ a.idade }}</td>
                <td>{{ a.uf }}</td>
                <td>{{ a.escola }}</td>
                <td>
                  <button @click="deletarAluno(a.id)" class="btn-apagar" title="Apagar">
                    <svg class="icon-delete" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <polyline points="3 6 5 6 21 6"/>
                      <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/>
                      <line x1="10" y1="11" x2="10" y2="17"/>
                      <line x1="14" y1="11" x2="14" y2="17"/>
                    </svg>
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
 
      <!-- ======= ABA PROFESSORES ======= -->
      <div v-if="abaAtiva === 'professores'">
        <div class="secao-card">
          <div class="secao-header">
            <svg class="icon-secao" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <line x1="12" y1="5" x2="12" y2="19"/>
              <line x1="5" y1="12" x2="19" y2="12"/>
            </svg>
            <h2 class="secao-titulo">Cadastrar Professor</h2>
          </div>
          <div class="form-grid">
            <div class="form-group">
              <label>Email</label>
              <input v-model="professor.email" type="email" placeholder="email@exemplo.com" class="form-input" />
            </div>
            <div class="form-group">
              <label>Senha</label>
              <input v-model="professor.senha" type="password" placeholder="Minimo 8 caracteres" class="form-input" />
            </div>
            <div class="form-group">
              <label>Nome</label>
              <input v-model="professor.nome" type="text" placeholder="Nome completo" class="form-input" />
            </div>
            <div class="form-group">
              <label>Idade</label>
              <input v-model="professor.idade" type="number" placeholder="Idade" class="form-input" />
            </div>
            <div class="form-group">
              <label>UF</label>
              <select v-model="professor.uf" class="form-input">
                <option value="">Selecione o estado</option>
                <option value="MA">Maranhao</option>
                <option value="SP">Sao Paulo</option>
                <option value="CE">Ceara</option>
              </select>
            </div>
            <div class="form-group">
              <label>Escola</label>
              <input v-model="professor.escola" type="text" placeholder="Nome da escola" class="form-input" />
            </div>
            <div class="form-group full">
              <label>Link do Lattes</label>
              <input v-model="professor.linkCurriculoLattes" type="url" placeholder="https://lattes.cnpq.br/..." class="form-input" />
            </div>
          </div>
          <button @click="salvarProfessor" class="btn-salvar">
            <svg class="icon-btn" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2z"/>
              <polyline points="17 6 12 13 7 8"/>
            </svg>
            Cadastrar Professor
          </button>
          <div v-if="sucessoProfessor" class="sucesso-msg">
            <svg class="icon-msg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="20 6 9 17 4 12"/>
            </svg>
            Professor cadastrado com sucesso!
          </div>
        </div>
 
        <!-- LISTA PROFESSORES -->
        <div class="secao-card">
          <div class="secao-header">
            <svg class="icon-secao" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
              <circle cx="9" cy="7" r="4"/>
              <path d="M23 21v-2a4 4 0 0 0-3-3.87"/>
              <path d="M16 3.13a4 4 0 0 1 0 7.75"/>
            </svg>
            <h2 class="secao-titulo">Lista de Professores</h2>
          </div>
          <div v-if="professores.length === 0" class="vazio">
            <svg class="icon-vazio" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="11" cy="11" r="8"/>
              <path d="m21 21-4.35-4.35"/>
            </svg>
            Nenhum professor cadastrado.
          </div>
          <table v-else class="tabela">
            <thead>
              <tr>
                <th>Email</th>
                <th>Nome</th>
                <th>Idade</th>
                <th>UF</th>
                <th>Escola</th>
                <th>Lattes</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="p in professores" :key="p.id">
                <td>{{ p.email }}</td>
                <td>{{ p.nome }}</td>
                <td>{{ p.idade }}</td>
                <td>{{ p.uf }}</td>
                <td>{{ p.escola }}</td>
                <td>
                  <a :href="p.linkCurriculoLattes" target="_blank" class="link-lattes">
                    <svg class="icon-link" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M10 13a5 5 0 0 0 7.54.54l3-3a5 5 0 0 0-7.07-7.07l-1.72 1.71"/>
                      <path d="M14 11a5 5 0 0 0-7.54-.54l-3 3a5 5 0 0 0 7.07 7.07l1.71-1.71"/>
                    </svg>
                    Ver
                  </a>
                </td>
                <td>
                  <button @click="deletarProfessor(p.id)" class="btn-apagar" title="Apagar">
                    <svg class="icon-delete" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <polyline points="3 6 5 6 21 6"/>
                      <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/>
                      <line x1="10" y1="11" x2="10" y2="17"/>
                      <line x1="14" y1="11" x2="14" y2="17"/>
                    </svg>
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
 
      
 
 
 
          <!-- ✅ MENSAGEM DE ERRO AGORA APARECE -->
    </main>
  </div>
</template>

<script>
const API = "http://localhost:8080"

export default {
  data() {
    return {
      abaAtiva: 'atividades',
      abas: [
        { key: 'atividades', label: 'Atividades' },
        { key: 'alunos', label: 'Alunos' },
        { key: 'professores', label: 'Professores' }
      ],
      atividade: { titulo: '', data: '', descricao: '', foto: null },
      aluno: { nome: '', idade: '', uf: '', escola: '' },
      professor: { email: '', senha: '', nome: '', idade: '', uf: '', escola: '', linkCurriculoLattes: '' },
      alunos: [],
      professores: [],
      sucessoAtividade: false,
      sucessoAluno: false,
      sucessoProfessor: false
    }
  },

  mounted() {
    if (localStorage.getItem("logado") !== "true") {
      this.$router.push("/admin")
      return
    }
    this.carregarAlunos()
    this.carregarProfessores()
  },

  methods: {
    token() {
      return localStorage.getItem("token")
    },

    // ATIVIDADES
    async salvarAtividade() {
      try {
        const formData = new FormData()
        formData.append("titulo", this.atividade.titulo)
        formData.append("descricao", this.atividade.descricao)
        formData.append("data", this.atividade.data)
        if (this.atividade.foto) formData.append("foto", this.atividade.foto)

        const response = await fetch(`${API}/atividades`, {
          method: "POST",
          headers: { "Authorization": `Bearer ${this.token()}` },
          body: formData
        })
        if (!response.ok) throw new Error(await response.text())

        this.sucessoAtividade = true
        setTimeout(() => { this.sucessoAtividade = false }, 3000)
        this.atividade = { titulo: '', data: '', descricao: '', foto: null }
      } catch (e) {
        alert("Erro: " + e.message)
      }
    },

    // ALUNOS
    async carregarAlunos() {
      try {
        const response = await fetch(`${API}/api/alunos`, {
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        if (!response.ok) return
        this.alunos = await response.json()
      } catch (e) { console.error(e) }
    },

    async salvarAluno() {
      try {
        const response = await fetch(`${API}/api/alunos`, {
          method: "POST",
          headers: {
            "Authorization": `Bearer ${this.token()}`,
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            ...this.aluno,
            idade: parseInt(this.aluno.idade)
          })
        })
        if (!response.ok) throw new Error(await response.text())

        this.sucessoAluno = true
        setTimeout(() => { this.sucessoAluno = false }, 3000)
        this.aluno = { nome: '', idade: '', uf: '', escola: '' }
        await this.carregarAlunos()
      } catch (e) {
        alert("Erro: " + e.message)
      }
    },

    async deletarAluno(id) {
      if (!confirm("Apagar este aluno?")) return
      try {
        await fetch(`${API}/api/alunos/${id}`, {
          method: "DELETE",
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        this.alunos = this.alunos.filter(a => a.id !== id)
      } catch (e) {
        alert("Erro ao apagar: " + e.message)
      }
    },

    // PROFESSORES
    async carregarProfessores() {
      try {
        const response = await fetch(`${API}/api/professores`, {
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        if (!response.ok) return
        this.professores = await response.json()
      } catch (e) { console.error(e) }
    },

    async salvarProfessor() {
      try {
        const response = await fetch(`${API}/api/professores`, {
          method: "POST",
          headers: {
            "Authorization": `Bearer ${this.token()}`,
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            ...this.professor,
            idade: parseInt(this.professor.idade)
          })
        })
        if (!response.ok) throw new Error(await response.text())

        this.sucessoProfessor = true
        setTimeout(() => { this.sucessoProfessor = false }, 3000)
        this.professor = { email: '', senha: '', nome: '', idade: '', uf: '', escola: '', linkCurriculoLattes: '' }
        await this.carregarProfessores()
      } catch (e) {
        alert("Erro: " + e.message)
      }
    },

    async deletarProfessor(id) {
      if (!confirm("Apagar este professor?")) return
      try {
        await fetch(`${API}/api/professores/${id}`, {
          method: "DELETE",
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        this.professores = this.professores.filter(p => p.id !== id)
      } catch (e) {
        alert("Erro ao apagar: " + e.message)
      }
    },

    logout() {
      localStorage.removeItem("logado")
      localStorage.removeItem("token")
      localStorage.removeItem("usuario")
      this.$router.push("/admin")
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700&display=swap');


/* ── RESET ───────────────────────────────────────────── */
*, *::before, *::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
  font-family: 'DM Sans', sans-serif;
  background: #fdfcfa;
  color: #1a1a18;
  -webkit-font-smoothing: antialiased;
}

img {
  max-width: 100%;
  display: block;
}

a {
  text-decoration: none;
}

.admin-wrapper {
  min-height: 100vh;
  background:
    radial-gradient(circle at top left, rgba(240, 112, 48, 0.14), transparent 30%),
    linear-gradient(180deg, #fff7f1 0%, #f7fbf5 100%);
  color: #1a1a18;
}

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

.header-titulo h1 {
  color: #fff8f1;
  font-size: clamp(1.35rem, 2vw, 2rem);
  margin-bottom: 0.15rem;
}

.header-titulo p {
  color: rgba(255, 248, 241, 0.7);
  font-size: 0.9rem;
}

.icon-header,
.icon-btn,
.icon-aba,
.icon-secao,
.icon-msg,
.icon-file,
.icon-delete,
.icon-link,
.icon-vazio {
  width: 1.15rem;
  height: 1.15rem;
  flex-shrink: 0;
}

.icon-header {
  width: 2rem;
  height: 2rem;
  color: #f07030;
}

.btn-logout {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  border: 1px solid rgba(255, 255, 255, 0.18);
  background: rgba(255, 255, 255, 0.08);
  color: #fff8f1;
  padding: 0.8rem 1.1rem;
  border-radius: 999px;
  font-weight: 600;
  cursor: pointer;
  transition: 0.2s ease;
}

.btn-logout:hover {
  background: rgba(240, 112, 48, 0.18);
  border-color: rgba(240, 112, 48, 0.35);
}

.abas {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1.25rem 1.5rem 0;
  display: flex;
  gap: 0.75rem;
  flex-wrap: wrap;
}

.btn-aba {
  display: inline-flex;
  align-items: center;
  gap: 0.55rem;
  padding: 0.85rem 1.15rem;
  border: 1px solid rgba(45, 90, 39, 0.12);
  border-radius: 999px;
  background: #fff;
  color: #32502c;
  font-weight: 600;
  cursor: pointer;
  transition: 0.2s ease;
  box-shadow: 0 8px 20px rgba(26, 58, 22, 0.05);
}

.btn-aba:hover {
  transform: translateY(-1px);
  border-color: rgba(217, 95, 28, 0.25);
}

.btn-aba.ativo {
  background: linear-gradient(135deg, #d95f1c, #f07030);
  color: #fff;
  border-color: transparent;
  box-shadow: 0 14px 30px rgba(217, 95, 28, 0.22);
}

.admin-main {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1.5rem;
  display: grid;
  gap: 1.5rem;
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

.icon-secao {
  width: 1.2rem;
  height: 1.2rem;
  color: #d95f1c;
}

.secao-titulo {
  font-size: 1.2rem;
  color: #1a3a16;
}

.secao-descricao {
  margin-bottom: 1rem;
  color: #5f5a55;
  line-height: 1.6;
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

.form-group.full {
  grid-column: 1 / -1;
}

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

.file-upload {
  position: relative;
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
  padding: 0.95rem 1rem;
  border: 1.5px dashed rgba(217, 95, 28, 0.45);
  border-radius: 1rem;
  background: #fff6ef;
  color: #8b4b22;
  cursor: pointer;
  transition: 0.2s ease;
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

.vazio {
  padding: 2rem 1rem;
  text-align: center;
  color: #666;
  display: grid;
  justify-items: center;
  gap: 0.75rem;
}

.icon-vazio {
  width: 1.6rem;
  height: 1.6rem;
  color: #d95f1c;
}

.tabela {
  width: 100%;
  border-collapse: collapse;
  overflow: hidden;
}

.tabela thead th {
  text-align: left;
  font-size: 0.82rem;
  text-transform: uppercase;
  letter-spacing: 0.05em;
  color: #6a5f58;
  padding: 0 0 0.9rem;
  border-bottom: 1px solid rgba(45, 90, 39, 0.12);
}

.tabela tbody td {
  padding: 1rem 0.25rem 1rem 0;
  border-bottom: 1px solid rgba(45, 90, 39, 0.08);
  color: #2a2a28;
  vertical-align: middle;
}

.btn-apagar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 2.4rem;
  height: 2.4rem;
  border: none;
  border-radius: 999px;
  background: #fff1ec;
  color: #c95420;
  cursor: pointer;
  transition: 0.2s ease;
}

.btn-apagar:hover {
  background: #ffe4d8;
}

.link-lattes {
  display: inline-flex;
  align-items: center;
  gap: 0.4rem;
  color: #d95f1c;
  font-weight: 700;
}

.link-lattes:hover {
  text-decoration: underline;
}

/* ── PAINEL ADMIN ────────────────────────────────────── */
.painel-container {
  max-width: 600px;
  margin: 60px auto;
  padding: 40px;
  background: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0,0,0,0.1);
}

.titulo-painel {
  text-align: center;
  color: #d95f1c;
  margin-bottom: 30px;
  font-size: 28px;
}

.formulario {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.formulario label {
  font-weight: bold;
  color: #444;
  margin-top: 10px;
}

.formulario input,
.formulario textarea {
  padding: 12px;
  border-radius: 6px;
  border: 1px solid #ddd;
  font-size: 14px;
  background: white;
}

.formulario textarea {
  min-height: 120px;
  resize: none;
}

.botoes {
  display: flex;
  gap: 15px;
  margin-top: 25px;
}

.btn-salvar {
  flex: 1;
  padding: 12px;
  background: #dcdcdc;
  border: none;
  border-radius: 6px;
  font-weight: bold;
  cursor: pointer;
}

.btn-publicar {
  flex: 1;
  padding: 12px;
  background: #d95f1c;
  border: none;
  border-radius: 6px;
  color: white;
  font-weight: bold;
  cursor: pointer;
}

.btn-publicar:hover { background: #b84e17; }
.btn-salvar:hover { background: #cfcfcf; }

.mensagem-sucesso {
  margin-top: 25px;
  text-align: center;
}

.btn-voltar {
  margin-top: 10px;
  padding: 10px 20px;
  background: #d95f1c;
  border: none;
  border-radius: 6px;
  color: white;
  font-weight: bold;
  cursor: pointer;
}

/* ── AUTH ────────────────────────────────────────────── */
.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: #f0f5ef;
}

.auth-box {
  width: 100%;
  max-width: 420px;
  background: white;
  padding: 40px;
  border-radius: 16px;
  border: 2px solid #2d5a27;
  box-shadow: 0 10px 30px rgba(0,0,0,0.08);
  text-align: center;
  transition: 0.3s;
}

.auth-box:hover {
  transform: translateY(-5px);
  box-shadow: 0 15px 40px rgba(0,0,0,0.12);
}

.auth-box h2 {
  color: #2d5a27;
  margin-bottom: 20px;
  font-weight: 600;
}

.auth-box input {
  width: 100%;
  padding: 12px;
  margin-bottom: 12px;
  border-radius: 8px;
  border: 1px solid #ddd;
  font-size: 14px;
  outline: none;
  transition: 0.3s;
}

.auth-box input:focus {
  border-color: #d95f1c;
  box-shadow: 0 0 8px rgba(217, 95, 28, 0.3);
}

.auth-btn {
  width: 100%;
  padding: 14px;
  background: #d95f1c;
  color: white;
  border: none;
  border-radius: 10px;
  font-weight: bold;
  cursor: pointer;
  font-size: 15px;
  transition: 0.3s;
}

.auth-btn:hover {
  background: #b84e17;
  transform: scale(1.03);
}

.auth-box button {
  margin-top: 10px;
  background: none;
  border: none;
  color: #d95f1c;
  cursor: pointer;
  font-weight: 500;
}

.auth-box button:hover { text-decoration: underline; }

.auth-box hr {
  margin: 25px 0;
  border: none;
  height: 1px;
  background: #eee;
}

/* ── LISTA DE ATIVIDADES (admin) ─────────────────────── */
.lista-container {
  max-width: 800px;
  margin: 60px auto;
  padding: 20px;
}

.lista-atividades {
  list-style: none;
  padding: 0;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.card-atividade {
  padding: 20px 25px;
  border-radius: 14px;
  border-left: 6px solid #d95f1c;
  box-shadow: 0 8px 20px rgba(0,0,0,0.06);
  display: flex;
  justify-content: space-between;
  align-items: center;
  transition: all 0.3s ease;
  background: white;
}

.card-atividade:hover {
  transform: translateY(-4px) scale(1.01);
  box-shadow: 0 12px 28px rgba(0,0,0,0.12);
  border-left-color: #b84e17;
}

.conteudo {
  display: flex;
  flex-direction: column;
  gap: 6px;
  max-width: 75%;
}

.conteudo strong {
  font-size: 18px;
  font-weight: 600;
  color: #1a3a16;
}

.conteudo p {
  font-size: 14px;
  color: #666;
  line-height: 1.4;
}

.data {
  font-size: 12px;
  color: #999;
  margin-top: 5px;
}

.acoes {
  display: flex;
  gap: 12px;
  align-items: center;
}

.icone {
  width: 22px;
  height: 22px;
  cursor: pointer;
  transition: 0.2s;
  opacity: 0.7;
}

.icone:hover {
  transform: scale(1.2);
  opacity: 1;
}

@media (max-width: 900px) {
  .admin-header-content {
    flex-direction: column;
    align-items: flex-start;
  }

  .form-grid {
    grid-template-columns: 1fr;
  }

  .tabela {
    display: block;
    overflow-x: auto;
  }
}

@media (max-width: 640px) {
  .abas,
  .admin-main {
    padding-left: 1rem;
    padding-right: 1rem;
  }

  .btn-aba,
  .btn-salvar,
  .btn-logout {
    width: 100%;
    justify-content: center;
  }

  .secao-card {
    padding: 1.1rem;
  }
}
</style>
