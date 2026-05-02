<template>
  <PainelShell titulo="Painel Administrativo" @logout="logout">
    <template #icon>
      <svg class="icon-header" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
        <path d="M12 3v1m0 16v1m9-9h-1m-16 0H1M20.485 3.515l-.707.707M5.222 18.778l-.707.707M20.485 20.485l-.707-.707M5.222 5.222l-.707-.707M15 12a3 3 0 11-6 0 3 3 0 016 0z"/>
      </svg>
    </template>

    <!-- ABAS -->
    <template #tabs>
    <div class="abas">
      <button
        v-for="aba in abas"
        :key="aba.key"
        @click="abaAtiva = aba.key"
        :class="['btn-aba', { ativo: abaAtiva === aba.key }]"
      >
        <svg class="icon-aba" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <g v-if="aba.key === 'pendentes'">
            <circle cx="12" cy="12" r="10"/>
            <polyline points="12 6 12 12 16 14"/>
          </g>
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
    </template>

      <!-- ======= ABA PENDENTES ======= -->
      <div v-if="abaAtiva === 'pendentes'">
        <div class="secao-card">
          <div class="secao-header">
            <svg class="icon-secao" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="12" cy="12" r="10"/>
              <polyline points="12 6 12 12 16 14"/>
            </svg>
            <h2 class="secao-titulo">Professores aguardando aprovação</h2>
            <span v-if="pendentes.length" class="badge-count">{{ pendentes.length }}</span>
          </div>

          <div v-if="pendentes.length === 0" class="vazio">
            <svg class="icon-vazio" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="20 6 9 17 4 12"/>
            </svg>
            Nenhum cadastro pendente.
          </div>

          <div v-else class="pendentes-lista">
            <div v-for="p in pendentes" :key="p.id" class="pendente-card">
              <!-- Linha principal -->
              <div class="pendente-info">
                <div class="pendente-avatar">{{ p.nome?.[0]?.toUpperCase() ?? '?' }}</div>
                <div class="pendente-dados">
                  <p class="pendente-nome">{{ p.nome }}</p>
                  <p class="pendente-email">{{ p.email }}</p>
                </div>
              </div>

              <!-- Chips de metadados -->
              <div class="pendente-meta">
                <span class="meta-chip">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2"/><line x1="16" y1="2" x2="16" y2="6"/><line x1="8" y1="2" x2="8" y2="6"/><line x1="3" y1="10" x2="21" y2="10"/></svg>
                  {{ formatCpf(p.cpf) }}
                </span>
                <span class="meta-chip">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/></svg>
                  {{ p.uf }}
                </span>
                <span class="meta-chip">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/><polyline points="9 22 9 12 15 12 15 22"/></svg>
                  {{ p.escola }}
                </span>
                <span v-if="p.idade" class="meta-chip">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="8" r="4"/><path d="M6 20v-2a4 4 0 0 1 4-4h4a4 4 0 0 1 4 4v2"/></svg>
                  {{ p.idade }} anos
                </span>
                <a v-if="p.linkCurriculoLattes" :href="p.linkCurriculoLattes" target="_blank" rel="noopener" class="meta-chip meta-chip--link">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M10 13a5 5 0 0 0 7.54.54l3-3a5 5 0 0 0-7.07-7.07l-1.72 1.71"/><path d="M14 11a5 5 0 0 0-7.54-.54l-3 3a5 5 0 0 0 7.07 7.07l1.71-1.71"/></svg>
                  Lattes
                </a>
              </div>

              <!-- Ações -->
              <div class="pendente-acoes">
                <button @click="aprovarProfessor(p.id)" class="btn-aprovar">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="20 6 9 17 4 12"/></svg>
                  Aprovar
                </button>
                <button @click="rejeitarProfessor(p.id)" class="btn-rejeitar">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/></svg>
                  Rejeitar
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

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
                <th>CPF</th>
                <th>UF</th>
                <th>Escola</th>
                <th>Status</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="p in professores" :key="p.id">
                <td>{{ p.email }}</td>
                <td>{{ p.nome }}</td>
                <td>{{ p.cpf }}</td>
                <td>{{ p.uf }}</td>
                <td>{{ p.escola }}</td>
                <td><span :class="['badge-status', p.status?.toLowerCase()]">{{ p.status }}</span></td>
                <td class="acoes">
                  <button @click="abrirEditar(p)" class="btn-acao btn-editar" title="Editar">
                    <svg class="icon-delete" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"/>
                      <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"/>
                    </svg>
                  </button>
                  <button @click="abrirResetSenha(p)" class="btn-acao btn-senha" title="Resetar senha">
                    <svg class="icon-delete" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/>
                      <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                    </svg>
                  </button>
                  <button @click="deletarProfessor(p.id)" class="btn-apagar" title="Apagar">
                    <svg class="icon-delete" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <polyline points="3 6 5 6 21 6"/>
                      <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/>
                    </svg>
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- MODAL EDITAR PROFESSOR -->
      <div v-if="modalEdicao" class="modal-overlay" @click.self="modalEdicao = false">
        <div class="modal-card">
          <div class="modal-header">
            <h3>Editar Professor</h3>
            <button @click="modalEdicao = false" class="modal-fechar">&times;</button>
          </div>
          <div class="form-grid">
            <div class="form-group">
              <label>Nome</label>
              <input v-model="editando.nome" type="text" class="form-input" />
            </div>
            <div class="form-group">
              <label>Email</label>
              <input v-model="editando.email" type="email" class="form-input" />
            </div>
            <div class="form-group">
              <label>Idade</label>
              <input v-model="editando.idade" type="number" class="form-input" />
            </div>
            <div class="form-group">
              <label>UF</label>
              <select v-model="editando.uf" class="form-input">
                <option value="">Selecione</option>
                <option v-for="uf in ufs" :key="uf" :value="uf">{{ uf }}</option>
              </select>
            </div>
            <div class="form-group full">
              <label>Escola</label>
              <input v-model="editando.escola" type="text" class="form-input" />
            </div>
            <div class="form-group full">
              <label>Link do Lattes</label>
              <input v-model="editando.linkCurriculoLattes" type="url" class="form-input" />
            </div>
          </div>
          <div class="modal-footer">
            <button @click="salvarEdicao" class="btn-salvar">Salvar</button>
            <button @click="modalEdicao = false" class="btn-cancelar">Cancelar</button>
          </div>
        </div>
      </div>

      <!-- MODAL RESET SENHA -->
      <div v-if="modalSenha" class="modal-overlay" @click.self="modalSenha = false">
        <div class="modal-card modal-card--sm">
          <div class="modal-header">
            <h3>Resetar Senha</h3>
            <button @click="modalSenha = false" class="modal-fechar">&times;</button>
          </div>
          <p class="modal-subtitulo">Professor: <strong>{{ resetando.nome }}</strong></p>
          <div class="form-group">
            <label>Nova senha</label>
            <input v-model="resetando.novaSenha" type="password" placeholder="Mínimo 8 caracteres" class="form-input" />
          </div>
          <div class="modal-footer">
            <button @click="confirmarResetSenha" class="btn-salvar">Confirmar</button>
            <button @click="modalSenha = false" class="btn-cancelar">Cancelar</button>
          </div>
        </div>
      </div>

      <!-- MODAL CONFIRMAÇÃO GENÉRICO -->
      <div v-if="confirmModal.show" class="modal-overlay" @click.self="confirmModal.show = false">
        <div class="modal-card modal-card--sm modal-confirm">
          <div class="confirm-icon" :class="confirmModal.variante">
            <svg v-if="confirmModal.variante === 'danger'" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="3 6 5 6 21 6"/><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/></svg>
            <svg v-else-if="confirmModal.variante === 'success'" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="20 6 9 17 4 12"/></svg>
            <svg v-else viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>
          </div>
          <h3 class="confirm-titulo">{{ confirmModal.titulo }}</h3>
          <p class="confirm-mensagem">{{ confirmModal.mensagem }}</p>
          <div class="modal-footer">
            <button @click="confirmModal.show = false" class="btn-cancelar">Cancelar</button>
            <button @click="executarConfirm" :class="['btn-confirm', confirmModal.variante]">{{ confirmModal.labelOk }}</button>
          </div>
        </div>
      </div>

      <!-- TOAST -->
      <transition name="toast-fade">
        <div v-if="toast.show" :class="['toast', toast.variante]">
          <svg v-if="toast.variante === 'sucesso'" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="20 6 9 17 4 12"/></svg>
          <svg v-else viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/></svg>
          {{ toast.mensagem }}
        </div>
      </transition>
 
      
 
 
 
  </PainelShell>
</template>

<script>
import PainelShell from '@/components/PainelShell.vue'

const API = "http://localhost:8080"

export default {
  components: { PainelShell },
  data() {
    return {
      abaAtiva: 'pendentes',
      abas: [
        { key: 'pendentes', label: 'Pendentes' },
        { key: 'atividades', label: 'Atividades' },
        { key: 'alunos', label: 'Alunos' },
        { key: 'professores', label: 'Professores' }
      ],
      atividade: { titulo: '', data: '', descricao: '', foto: null },
      aluno: { nome: '', idade: '', uf: '', escola: '' },
      professor: { email: '', cpf: '', senha: '', nome: '', idade: '', uf: '', escola: '', linkCurriculoLattes: '' },
      editando: { id: null, email: '', nome: '', idade: '', uf: '', escola: '', linkCurriculoLattes: '' },
      resetando: { id: null, nome: '', novaSenha: '' },
      modalEdicao: false,
      modalSenha: false,
      confirmModal: { show: false, titulo: '', mensagem: '', variante: 'warning', labelOk: 'Confirmar', _resolve: null },
      toast: { show: false, mensagem: '', variante: 'sucesso' },
      ufs: ['AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO'],
      alunos: [],
      professores: [],
      pendentes: [],
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
    this.carregarPendentes()
  },

  methods: {
    token() {
      return localStorage.getItem("token")
    },

    formatCpf(cpf) {
      if (!cpf) return '—'
      const d = cpf.replace(/\D/g, '')
      if (d.length !== 11) return cpf
      return d.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4')
    },

    mostrarToast(mensagem, variante = 'sucesso') {
      this.toast = { show: true, mensagem, variante }
      setTimeout(() => { this.toast.show = false }, 3500)
    },

    abrirConfirm({ titulo, mensagem, variante = 'warning', labelOk = 'Confirmar' }) {
      return new Promise(resolve => {
        this.confirmModal = { show: true, titulo, mensagem, variante, labelOk, _resolve: resolve }
      })
    },

    executarConfirm() {
      this.confirmModal.show = false
      if (this.confirmModal._resolve) this.confirmModal._resolve(true)
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
      const ok = await this.abrirConfirm({
        titulo: 'Apagar aluno?',
        mensagem: 'Esta ação não pode ser desfeita.',
        variante: 'danger',
        labelOk: 'Apagar'
      })
      if (!ok) return
      try {
        await fetch(`${API}/api/alunos/${id}`, {
          method: "DELETE",
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        this.alunos = this.alunos.filter(a => a.id !== id)
        this.mostrarToast('Aluno removido com sucesso.')
      } catch (e) {
        this.mostrarToast('Erro ao apagar: ' + e.message, 'erro')
      }
    },

    // PENDENTES (professores aguardando aprovacao)
    async carregarPendentes() {
      try {
        const response = await fetch(`${API}/api/professores/pendentes`, {
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        if (!response.ok) return
        this.pendentes = await response.json()
      } catch (e) { console.error(e) }
    },

    async aprovarProfessor(id) {
      const ok = await this.abrirConfirm({
        titulo: 'Aprovar professor?',
        mensagem: 'O professor poderá fazer login após a aprovação.',
        variante: 'success',
        labelOk: 'Aprovar'
      })
      if (!ok) return
      try {
        const response = await fetch(`${API}/api/professores/${id}/aprovar`, {
          method: "PUT",
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        if (!response.ok) throw new Error(await response.text())
        this.pendentes = this.pendentes.filter(p => p.id !== id)
        await this.carregarProfessores()
        this.mostrarToast('Professor aprovado com sucesso!')
      } catch (e) { this.mostrarToast('Erro ao aprovar: ' + e.message, 'erro') }
    },

    async rejeitarProfessor(id) {
      const ok = await this.abrirConfirm({
        titulo: 'Rejeitar cadastro?',
        mensagem: 'O professor não poderá fazer login no sistema.',
        variante: 'danger',
        labelOk: 'Rejeitar'
      })
      if (!ok) return
      try {
        const response = await fetch(`${API}/api/professores/${id}/rejeitar`, {
          method: "PUT",
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        if (!response.ok) throw new Error(await response.text())
        this.pendentes = this.pendentes.filter(p => p.id !== id)
        this.mostrarToast('Cadastro rejeitado.')
      } catch (e) { this.mostrarToast('Erro ao rejeitar: ' + e.message, 'erro') }
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

    abrirEditar(p) {
      this.editando = {
        id: p.id,
        email: p.email,
        nome: p.nome,
        idade: p.idade,
        uf: p.uf,
        escola: p.escola,
        linkCurriculoLattes: p.linkCurriculoLattes
      }
      this.modalEdicao = true
    },

    async salvarEdicao() {
      try {
        const body = {
          email: this.editando.email,
          nome: this.editando.nome,
          idade: parseInt(this.editando.idade),
          uf: this.editando.uf,
          escola: this.editando.escola,
          linkCurriculoLattes: this.editando.linkCurriculoLattes
        }
        const response = await fetch(`${API}/api/professores/${this.editando.id}`, {
          method: 'PUT',
          headers: {
            'Authorization': `Bearer ${this.token()}`,
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(body)
        })
        if (!response.ok) throw new Error(await response.text())
        this.modalEdicao = false
        await this.carregarProfessores()
      } catch (e) { alert('Erro ao salvar: ' + e.message) }
    },

    abrirResetSenha(p) {
      this.resetando = { id: p.id, nome: p.nome, novaSenha: '' }
      this.modalSenha = true
    },

    async confirmarResetSenha() {
      if (this.resetando.novaSenha.length < 8) {
        alert('A senha deve ter pelo menos 8 caracteres.')
        return
      }
      try {
        const response = await fetch(`${API}/api/professores/${this.resetando.id}/reset-senha`, {
          method: 'PATCH',
          headers: {
            'Authorization': `Bearer ${this.token()}`,
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ novaSenha: this.resetando.novaSenha })
        })
        if (!response.ok) throw new Error(await response.text())
        this.modalSenha = false
        this.mostrarToast(`Senha de ${this.resetando.nome} redefinida!`)
      } catch (e) { this.mostrarToast('Erro ao resetar senha: ' + e.message, 'erro') }
    },

    async deletarProfessor(id) {
      const ok = await this.abrirConfirm({
        titulo: 'Apagar professor?',
        mensagem: 'Todos os dados deste professor serão removidos permanentemente.',
        variante: 'danger',
        labelOk: 'Apagar'
      })
      if (!ok) return
      try {
        await fetch(`${API}/api/professores/${id}`, {
          method: "DELETE",
          headers: { "Authorization": `Bearer ${this.token()}` }
        })
        this.professores = this.professores.filter(p => p.id !== id)
        this.mostrarToast('Professor removido.')
      } catch (e) {
        this.mostrarToast('Erro ao apagar: ' + e.message, 'erro')
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
/* Estilos locais — wrapper/header/logout ficam no PainelShell */
.icon-header { width: 2rem; height: 2rem; color: #f07030; }

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

.btn-apagar:hover { background: #ffe4d8; }

.acoes { display: flex; gap: 0.4rem; align-items: center; }

.btn-acao {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 2.4rem;
  height: 2.4rem;
  border: none;
  border-radius: 999px;
  cursor: pointer;
  transition: 0.2s ease;
}

.btn-editar { background: #eef5ec; color: #2d5a27; }
.btn-editar:hover { background: #d8eed3; }
.btn-senha { background: #fffbe6; color: #a06800; }
.btn-senha:hover { background: #fff3b0; }

.badge-status {
  display: inline-block;
  padding: 0.25rem 0.65rem;
  border-radius: 999px;
  font-size: 0.78rem;
  font-weight: 700;
  text-transform: uppercase;
}
.badge-status.aprovado { background: #e8f5e4; color: #2d5a27; }
.badge-status.pendente { background: #fff8e1; color: #a06800; }
.badge-status.rejeitado { background: #fdecea; color: #b71c1c; }

/* MODAL */
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(10, 20, 8, 0.55);
  backdrop-filter: blur(4px);
  z-index: 200;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 1rem;
}

.modal-card {
  background: #fff;
  border-radius: 1.5rem;
  padding: 2rem;
  width: 100%;
  max-width: 640px;
  box-shadow: 0 24px 60px rgba(26, 58, 22, 0.18);
}

.modal-card--sm { max-width: 420px; }

.modal-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 1.25rem;
}

.modal-header h3 {
  font-size: 1.2rem;
  color: #1a3a16;
}

.modal-fechar {
  background: none;
  border: none;
  font-size: 1.5rem;
  line-height: 1;
  color: #888;
  cursor: pointer;
  padding: 0 0.25rem;
}

.modal-fechar:hover { color: #c95420; }

.modal-subtitulo {
  margin-bottom: 1rem;
  color: #555;
  font-size: 0.95rem;
}

.modal-footer {
  display: flex;
  gap: 0.75rem;
  margin-top: 1.5rem;
  justify-content: flex-end;
}

.btn-cancelar {
  padding: 0.85rem 1.25rem;
  border: 1px solid rgba(45, 90, 39, 0.15);
  border-radius: 999px;
  background: #fff;
  color: #555;
  font-weight: 600;
  cursor: pointer;
  transition: 0.2s ease;
  font-family: inherit;
}

.btn-cancelar:hover { background: #f3f3f1; }

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

/* ── MODAL CONFIRMⅠÇÃO ───────────────────────────── */
.modal-confirm { text-align: center; padding: 2.25rem 2rem; }

.confirm-icon {
  width: 3.5rem;
  height: 3.5rem;
  border-radius: 50%;
  display: grid;
  place-items: center;
  margin: 0 auto 1.25rem;
}

.confirm-icon svg { width: 1.5rem; height: 1.5rem; }

.confirm-icon.danger  { background: #fdecea; color: #c62828; }
.confirm-icon.success { background: #e8f5e4; color: #2d5a27; }
.confirm-icon.warning { background: #fff8e1; color: #a06800; }

.confirm-titulo {
  font-size: 1.15rem;
  font-weight: 700;
  color: #1a3a16;
  margin: 0 0 0.5rem;
}

.confirm-mensagem {
  font-size: 0.9rem;
  color: #6a5f58;
  margin: 0;
  line-height: 1.5;
}

.btn-confirm {
  padding: 0.85rem 1.5rem;
  border: none;
  border-radius: 999px;
  font-weight: 700;
  font-size: 0.9rem;
  cursor: pointer;
  transition: 0.2s ease;
  font-family: inherit;
}

.btn-confirm.danger  { background: linear-gradient(135deg, #c62828, #e53935); color: #fff; }
.btn-confirm.success { background: linear-gradient(135deg, #1a3a16, #2d5a27); color: #fff; }
.btn-confirm.warning { background: linear-gradient(135deg, #d95f1c, #f07030); color: #fff; }
.btn-confirm:hover { transform: translateY(-1px); box-shadow: 0 8px 20px rgba(0,0,0,0.15); }

/* ── TOAST ───────────────────────────────────────── */
.toast {
  position: fixed;
  bottom: 2rem;
  left: 50%;
  transform: translateX(-50%);
  display: inline-flex;
  align-items: center;
  gap: 0.6rem;
  padding: 0.85rem 1.5rem;
  border-radius: 999px;
  font-weight: 600;
  font-size: 0.9rem;
  z-index: 999;
  box-shadow: 0 8px 28px rgba(0,0,0,0.15);
  white-space: nowrap;
}

.toast svg { width: 1rem; height: 1rem; flex-shrink: 0; }
.toast.sucesso { background: #1a3a16; color: #d4f0cc; }
.toast.erro    { background: #c62828; color: #fde8e8; }

.toast-fade-enter-active, .toast-fade-leave-active { transition: opacity 0.3s ease, transform 0.3s ease; }
.toast-fade-enter-from, .toast-fade-leave-to { opacity: 0; transform: translateX(-50%) translateY(12px); }

/* ── PENDENTES (cards) ──────────────────────────────── */
.badge-count {
  margin-left: auto;
  min-width: 1.6rem;
  height: 1.6rem;
  padding: 0 0.45rem;
  background: linear-gradient(135deg, #d95f1c, #f07030);
  color: #fff;
  border-radius: 999px;
  font-size: 0.78rem;
  font-weight: 700;
  display: inline-flex;
  align-items: center;
  justify-content: center;
}

.pendentes-lista {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.pendente-card {
  display: flex;
  align-items: center;
  gap: 1rem;
  padding: 1rem 1.15rem;
  border: 1px solid rgba(45, 90, 39, 0.1);
  border-radius: 1.1rem;
  background: #fdfcfa;
  transition: box-shadow 0.2s ease, border-color 0.2s ease;
  flex-wrap: wrap;
}

.pendente-card:hover {
  border-color: rgba(217, 95, 28, 0.2);
  box-shadow: 0 6px 20px rgba(217, 95, 28, 0.08);
}

.pendente-info {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  flex: 0 0 220px;
  min-width: 0;
}

.pendente-avatar {
  flex-shrink: 0;
  width: 2.5rem;
  height: 2.5rem;
  border-radius: 50%;
  background: linear-gradient(135deg, #2d5a27, #1a3a16);
  color: #fff;
  font-size: 1rem;
  font-weight: 700;
  display: grid;
  place-items: center;
}

.pendente-dados { min-width: 0; }

.pendente-nome {
  font-weight: 700;
  color: #1a3a16;
  font-size: 0.95rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  margin: 0;
}

.pendente-email {
  font-size: 0.82rem;
  color: #6a5f58;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  margin: 0;
}

.pendente-meta {
  display: flex;
  flex-wrap: wrap;
  gap: 0.4rem;
  flex: 1;
}

.meta-chip {
  display: inline-flex;
  align-items: center;
  gap: 0.3rem;
  padding: 0.3rem 0.65rem;
  background: #f3f0ec;
  border-radius: 999px;
  font-size: 0.8rem;
  color: #4a4540;
  font-weight: 500;
  text-decoration: none;
}

.meta-chip svg { width: 0.85rem; height: 0.85rem; flex-shrink: 0; }

.meta-chip--link {
  background: #fff4ec;
  color: #c95420;
  font-weight: 600;
}

.meta-chip--link:hover {
  background: #ffe8d8;
  text-decoration: underline;
}

.pendente-acoes {
  display: flex;
  gap: 0.5rem;
  flex-shrink: 0;
}

.btn-aprovar,
.btn-rejeitar {
  display: inline-flex;
  align-items: center;
  gap: 0.4rem;
  padding: 0.55rem 1rem;
  border: none;
  border-radius: 999px;
  font-size: 0.85rem;
  font-weight: 700;
  cursor: pointer;
  transition: 0.2s ease;
  font-family: inherit;
}

.btn-aprovar svg,
.btn-rejeitar svg { width: 0.9rem; height: 0.9rem; flex-shrink: 0; }

.btn-aprovar {
  background: linear-gradient(135deg, #1a3a16, #2d5a27);
  color: #fff;
}

.btn-aprovar:hover {
  transform: translateY(-1px);
  box-shadow: 0 8px 20px rgba(26, 58, 22, 0.18);
}

.btn-rejeitar {
  background: #fff1ec;
  color: #c95420;
}

.btn-rejeitar:hover {
  background: #ffe4d8;
  transform: translateY(-1px);
}

@media (max-width: 640px) {
  .pendente-card { flex-direction: column; align-items: flex-start; }
  .pendente-info { flex: unset; width: 100%; }
  .pendente-acoes { width: 100%; }
  .btn-aprovar, .btn-rejeitar { flex: 1; justify-content: center; }
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
  .abas {
    padding-left: 1rem;
    padding-right: 1rem;
  }

  .btn-aba,
  .btn-salvar {
    width: 100%;
    justify-content: center;
  }

  .secao-card {
    padding: 1.1rem;
  }
}
</style>
