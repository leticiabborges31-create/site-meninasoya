<template>
  <div class="atividades-wrapper">
    <div class="atividades-header">
      <h1 class="atividades-titulo">Atividades</h1>
      <p class="atividades-subtitulo">Confira nossas atividades mais recentes</p>
    </div>

    <div v-if="carregando" class="loading">Carregando...</div>

    <div v-else-if="atividades.length > 0" class="lista-atividades">
      <div
        v-for="atividade in atividades"
        :key="atividade.id"
        class="card-atividade"
        @click="abrirDetalhe(atividade.id)"
      >
        <img
          v-if="atividade.id"
          :src="`http://localhost:8080/atividades/${atividade.id}/foto`"
          :alt="atividade.titulo"
          class="card-imagem"
          @error="e => e.target.style.display='none'"
        />

        <div class="card-conteudo">
          <div class="card-topo">
            <span class="card-data">📅 {{ formatarData(atividade.data) }}</span>
            <div class="card-acoes" v-if="isLogado" @click.stop>
              <button @click="editar(atividade)" class="btn-editar" title="Editar">✏️</button>
              <button @click="apagar(atividade.id)" class="btn-apagar" title="Apagar">🗑️</button>
            </div>
          </div>
          <h2 class="card-titulo">{{ atividade.titulo }}</h2>
          <p class="card-descricao">{{ stripHtml(atividade.descricao) }}</p>
          <div class="card-rodape">
            <span v-if="atividade.professorNome" class="card-professor">👩‍🏫 {{ atividade.professorNome }}</span>
            <span v-if="atividade.localizacao" class="card-local">📍 {{ atividade.localizacao }}</span>
            <span class="card-ver-mais">Ver mais →</span>
          </div>
        </div>
      </div>
    </div>

    <div v-else class="vazio">
      <p>Nenhuma atividade encontrada.</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      atividades: [],
      carregando: true,
      isLogado: false
    }
  },

  mounted() {
    this.isLogado = localStorage.getItem("logado") === "true"
    this.carregarAtividades()
  },

  methods: {
    async carregarAtividades() {
      try {
        const response = await fetch("http://localhost:8080/atividades")
        if (!response.ok) throw new Error("Erro ao buscar atividades")
        const dados = await response.json()
        this.atividades = dados.sort((a, b) => new Date(b.data || 0) - new Date(a.data || 0))
      } catch (error) {
        console.error("Erro ao carregar atividades:", error)
      } finally {
        this.carregando = false
      }
    },

    formatarData(data) {
      if (!data) return ''
      const [ano, mes, dia] = data.split('-')
      return `${dia}/${mes}/${ano}`
    },

    abrirDetalhe(id) {
      this.$router.push(`/atividade/${id}`)
    },

    stripHtml(html) {
      if (!html) return ''
      return html.replace(/<[^>]*>/g, '').replace(/&amp;/g, '&').replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&nbsp;/g, ' ').trim()
    },

    editar(atividade) {
      localStorage.setItem("atividade_editando", JSON.stringify(atividade))
      this.$router.push("/painel")
    },

    async apagar(id) {
      if (!confirm("Tem certeza que deseja apagar esta atividade?")) return
      try {
        const token = localStorage.getItem("token")
        const response = await fetch(`http://localhost:8080/atividades/${id}`, {
          method: "DELETE",
          headers: { "Authorization": `Bearer ${token}` }
        })
        if (!response.ok) throw new Error("Erro ao apagar")
        this.atividades = this.atividades.filter(a => a.id !== id)
      } catch (error) {
        alert("Erro ao apagar: " + error.message)
      }
    }
  }
}
</script>

<style scoped>
.atividades-wrapper {
  max-width: 800px;
  margin: 0 auto;
  padding: var(--space-xl) var(--space-md);
  font-family: var(--font-body);
}

.atividades-header {
  text-align: center;
  margin-bottom: var(--space-xl);
}

.atividades-titulo {
  font-family: var(--font-display);
  font-size: 2.25rem;
  color: var(--oya-forest);
  margin-bottom: var(--space-sm);
}

.atividades-subtitulo {
  font-size: 1rem;
  color: var(--oya-steel);
}

.lista-atividades {
  display: flex;
  flex-direction: column;
  gap: var(--space-lg);
}

.card-atividade {
  background: #fff;
  border-radius: var(--radius-lg);
  box-shadow: 0 4px 20px rgba(15, 34, 24, 0.06);
  overflow: hidden;
  border-left: 4px solid var(--oya-ember);
  transition: transform 0.2s, box-shadow 0.2s;
}

.card-atividade {
  cursor: pointer;
}

.card-atividade:hover {
  transform: translateY(-3px);
  box-shadow: 0 8px 28px rgba(15, 34, 24, 0.1);
}

.card-imagem {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.card-conteudo { padding: var(--space-lg); }

.card-topo {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: var(--space-sm);
}

.card-data {
  font-size: 0.82rem;
  color: var(--oya-ember);
  font-weight: 500;
}

.card-acoes { display: flex; gap: var(--space-sm); }

.btn-editar,
.btn-apagar {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 18px;
  padding: 4px 8px;
  border-radius: var(--radius-sm);
  transition: background 0.2s;
}

.btn-editar:hover { background: rgba(107, 170, 138, 0.12); }
.btn-apagar:hover { background: rgba(217, 79, 30, 0.08); }

.card-titulo {
  font-family: var(--font-display);
  font-size: 1.25rem;
  color: var(--oya-forest);
  margin-bottom: var(--space-sm);
}

.card-descricao {
  font-size: 0.9rem;
  color: var(--oya-stone);
  line-height: 1.6;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.card-rodape {
  display: flex;
  flex-wrap: wrap;
  gap: 0.75rem;
  margin-top: 0.75rem;
}

.card-professor,
.card-local {
  font-size: 0.8rem;
  color: var(--oya-steel);
  background: var(--oya-fog, #f4f4f4);
  padding: 0.2rem 0.6rem;
  border-radius: var(--radius-pill, 999px);
}

.card-ver-mais {
  margin-left: auto;
  font-size: 0.8rem;
  font-weight: 600;
  color: var(--oya-ember);
}

.loading, .vazio {
  text-align: center;
  padding: 60px 20px;
  color: var(--oya-steel);
  font-size: 1rem;
}

@media (max-width: 600px) {
  .atividades-titulo { font-size: 1.65rem; }
  .card-conteudo { padding: var(--space-md); }
  .card-titulo { font-size: 1.05rem; }
}
</style>