<template>
  <div class="detalhe-container" v-if="atividade">
    <button class="btn-voltar" @click="voltarParaAtividades">
      ← Voltar
    </button>
 
    <article class="detalhe-conteudo">
      <header class="detalhe-header">
        <h1 class="detalhe-titulo">{{ atividade.titulo }}</h1>
        <span class="detalhe-data">📅 {{ formatarData(atividade.data) }}</span>
      </header>
 
      <section class="detalhe-corpo">
        <p class="detalhe-descricao">{{ atividade.descricao }}</p>
 
        <div class="detalhe-meta">
          <div class="meta-item" v-if="atividade.local">
            <span class="meta-label">📍 Local:</span>
            <span class="meta-valor">{{ atividade.local }}</span>
          </div>
 
          <div class="meta-item" v-if="atividade.horario">
            <span class="meta-label">🕐 Horário:</span>
            <span class="meta-valor">{{ atividade.horario }}</span>
          </div>
 
          <div class="meta-item" v-if="atividade.instrutor">
            <span class="meta-label">👩‍🏫 Instrutor(a):</span>
            <span class="meta-valor">{{ atividade.instrutor }}</span>
          </div>
 
          <div class="meta-item" v-if="atividade.vagas">
            <span class="meta-label">👥 Vagas:</span>
            <span class="meta-valor">{{ atividade.vagas }}</span>
          </div>
        </div>
 
        <div class="detalhe-acoes">
          <button class="btn-inscrever" v-if="!inscrito" @click="inscrever">
            Inscrever-se
          </button>
          <button class="btn-inscrito" v-else disabled>
            ✓ Inscrito
          </button>
        </div>
      </section>
    </article>
  </div>
 
  <div v-else class="detalhe-vazio">
    <p>Atividade não encontrada.</p>
    <button class="btn-voltar" @click="voltarParaAtividades">
      ← Voltar para atividades
    </button>
  </div>
</template>
 
<script>
export default {
  data() {
    return {
      atividade: null,
      inscrito: false
    }
  },
 
  mounted() {
    this.carregarAtividade()
  },
 
  methods: {
    carregarAtividade() {
      const atividadeStr = localStorage.getItem("atividadeSelecionada")
      if (atividadeStr) {
        try {
          this.atividade = JSON.parse(atividadeStr)
          this.verificarInscricao()
        } catch (error) {
          console.error("Erro ao carregar atividade:", error)
        }
      }
    },
 
    formatarData(data) {
      if (!data) return ''
      const [ano, mes, dia] = data.split('-')
      return `${dia}/${mes}/${ano}`
    },
 
    voltarParaAtividades() {
      localStorage.removeItem("atividadeSelecionada")
      this.$router.push("/atividades")
    },
 
    inscrever() {
      this.inscrito = true
      console.log("Inscrito em:", this.atividade.titulo)
    },
 
    verificarInscricao() {
      const inscritos = JSON.parse(localStorage.getItem("inscritos") || "[]")
      this.inscrito = inscritos.includes(this.atividade.id)
    }
  }
}
</script>