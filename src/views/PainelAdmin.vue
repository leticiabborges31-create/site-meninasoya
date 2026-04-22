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
              <input v-model="aluno.uf" type="text" placeholder="Ex: MA" maxlength="2" class="form-input" />
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
              <label>Nome</label>
              <input v-model="professor.nome" type="text" placeholder="Nome completo" class="form-input" />
            </div>
            <div class="form-group">
              <label>Idade</label>
              <input v-model="professor.idade" type="number" placeholder="Idade" class="form-input" />
            </div>
            <div class="form-group">
              <label>UF</label>
              <input v-model="professor.uf" type="text" placeholder="Ex: MA" maxlength="2" class="form-input" />
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
      professor: { nome: '', idade: '', uf: '', escola: '', linkCurriculoLattes: '' },
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
        this.professor = { nome: '', idade: '', uf: '', escola: '', linkCurriculoLattes: '' }
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

* { margin: 0; padding: 0; box-sizing: border-box; }

.admin-wrapper {
  min-height: 100vh;
  background: #f5f7fa;
  font-family: 'DM Sans', sans-serif;
}

/* HEADER */
.admin-header {
  background: linear-gradient(135deg, #ff8a3d, #ff6a00);
  color: white;
  padding: 20px 40px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.15);
}

.admin-header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1100px;
  margin: 0 auto;
}

.header-titulo {
  display: flex;
  align-items: center;
  gap: 14px;
}

.icon-header {
  width: 32px;
  height: 32px;
  flex-shrink: 0;
}

.header-titulo h1 {
  font-size: 24px;
  font-weight: 700;
  letter-spacing: -0.5px;
}

.header-titulo p {
  font-size: 13px;
  opacity: 0.85;
  margin-top: 2px;
}

.btn-logout {
  display: flex;
  align-items: center;
  gap: 8px;
  background: rgba(255,255,255,0.2);
  color: white;
  border: 2px solid rgba(255,255,255,0.4);
  padding: 10px 16px;
  border-radius: 8px;
  font-weight: 600;
  font-size: 14px;
  cursor: pointer;
  transition: 0.3s;
  font-family: 'DM Sans', sans-serif;
}

.btn-logout:hover {
  background: rgba(255,255,255,0.3);
  border-color: rgba(255,255,255,0.6);
}

.icon-btn {
  width: 18px;
  height: 18px;
}

/* ABAS */
.abas {
  display: flex;
  gap: 8px;
  padding: 20px 40px 0;
  max-width: 1100px;
  margin: 0 auto;
}

.btn-aba {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 10px 24px;
  border: 2px solid #e0e0e0;
  border-radius: 10px 10px 0 0;
  background: white;
  color: #666;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: 0.2s;
  font-family: 'DM Sans', sans-serif;
}

.btn-aba:hover {
  background: #f9f9f9;
  border-color: #d0d0d0;
}

.btn-aba.ativo {
  background: #ff8a3d;
  color: white;
  border-color: #ff8a3d;
}

.icon-aba {
  width: 18px;
  height: 18px;
  flex-shrink: 0;
}

/* MAIN */
.admin-main {
  max-width: 1100px;
  margin: 0 auto;
  padding: 20px 40px 40px;
}

.secao-card {
  background: white;
  border-radius: 0 16px 16px 16px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.08);
  padding: 30px;
  margin-bottom: 24px;
}

.secao-header {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 20px;
  padding-bottom: 12px;
  border-bottom: 2px solid #f0f0f0;
}

.icon-secao {
  width: 20px;
  height: 20px;
  color: #ff8a3d;
  flex-shrink: 0;
}

.secao-titulo {
  font-size: 18px;
  font-weight: 700;
  color: #1a1a1a;
  margin: 0;
}

/* FORM */
.form-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
  margin-bottom: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.form-group.full {
  grid-column: span 2;
}

.form-group label {
  font-size: 13px;
  font-weight: 600;
  color: #333;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.form-input, .form-textarea {
  padding: 12px 14px;
  border: 2px solid #e8e8e8;
  border-radius: 8px;
  font-size: 15px;
  font-family: 'DM Sans', sans-serif;
  background: #fafafa;
  transition: 0.3s;
}

.form-input::placeholder, .form-textarea::placeholder {
  color: #aaa;
}

.form-input:focus, .form-textarea:focus {
  outline: none;
  border-color: #ff8a3d;
  background: white;
  box-shadow: 0 0 0 3px rgba(255, 138, 61, 0.1);
}

.file-input {
  display: none;
}

.file-label {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 12px 14px;
  border: 2px dashed #ff8a3d;
  border-radius: 8px;
  background: #fff9f5;
  cursor: pointer;
  font-size: 14px;
  color: #666;
  transition: 0.3s;
  font-weight: 500;
}

.file-label:hover {
  background: #ffe8d6;
}

.icon-file {
  width: 18px;
  height: 18px;
  color: #ff8a3d;
  flex-shrink: 0;
}

/* BOTÕES */
.btn-salvar {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 12px 28px;
  background: linear-gradient(135deg, #ff8a3d, #ff6a00);
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 15px;
  font-weight: 600;
  cursor: pointer;
  transition: 0.3s;
  font-family: 'DM Sans', sans-serif;
}

.btn-salvar:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(255, 138, 61, 0.3);
}

.btn-apagar {
  background: none;
  border: none;
  cursor: pointer;
  padding: 6px 8px;
  border-radius: 6px;
  transition: 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn-apagar:hover {
  background: #fdecea;
}

.icon-delete {
  width: 18px;
  height: 18px;
  color: #ff6a00;
  flex-shrink: 0;
}

/* MENSAGENS */
.sucesso-msg {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-top: 14px;
  padding: 12px 16px;
  background: #d4edda;
  border: 1px solid #c3e6cb;
  border-radius: 8px;
  color: #155724;
  font-weight: 500;
  font-size: 14px;
}

.icon-msg {
  width: 18px;
  height: 18px;
  flex-shrink: 0;
}

/* TABELA */
.tabela {
  width: 100%;
  border-collapse: collapse;
  font-size: 14px;
}

.tabela th {
  background: #fff5f0;
  color: #ff6a00;
  padding: 12px 16px;
  text-align: left;
  font-weight: 600;
  border-bottom: 2px solid #ffe0cc;
  font-family: 'DM Sans', sans-serif;
  letter-spacing: 0.5px;
}

.tabela td {
  padding: 12px 16px;
  border-bottom: 1px solid #f0f0f0;
  color: #444;
  font-family: 'DM Sans', sans-serif;
}

.tabela tr:hover td {
  background: #fffaf7;
}

.link-lattes {
  display: flex;
  align-items: center;
  gap: 6px;
  color: #ff8a3d;
  font-weight: 600;
  text-decoration: none;
  transition: 0.2s;
}

.link-lattes:hover {
  color: #ff6a00;
  text-decoration: underline;
}

.icon-link {
  width: 16px;
  height: 16px;
  flex-shrink: 0;
}

/* VAZIO */
.vazio {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 12px;
  padding: 40px 30px;
  color: #aaa;
  font-size: 14px;
}

.icon-vazio {
  width: 40px;
  height: 40px;
  opacity: 0.4;
}

/* RESPONSIVO */
@media (max-width: 600px) {
  .admin-header {
    padding: 16px 20px;
  }

  .admin-header-content {
    flex-direction: column;
    gap: 12px;
  }

  .header-titulo {
    width: 100%;
  }

  .btn-logout {
    width: 100%;
    justify-content: center;
  }

  .abas {
    padding: 16px 20px 0;
    flex-wrap: wrap;
  }

  .admin-main {
    padding: 16px 20px;
  }

  .form-grid {
    grid-template-columns: 1fr;
  }

  .form-group.full {
    grid-column: span 1;
  }

  .tabela {
    font-size: 12px;
  }

  .tabela td, .tabela th {
    padding: 8px 12px;
  }
}
</style>