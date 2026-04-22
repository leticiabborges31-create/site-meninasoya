<template>
  <div class="atividades-wrapper">
    <div class="atividades-header">
      <h1 class="atividades-titulo">Atividades</h1>
      <p class="atividades-subtitulo">Confira nossas atividades mais recentes</p>
    </div>

    <!-- LOADING -->
    <div v-if="carregando" class="loading">
      <span>Carregando...</span>
    </div>

    <!-- LISTA -->
    <div v-else-if="atividades.length > 0" class="lista-atividades">
      <div
        v-for="atividade in atividades"
        :key="atividade.id"
        class="card-atividade"
      >
        <!-- IMAGEM -->
        <div class="card-imagem" v-if="atividade.id">
          <img
            :src="`http://localhost:8080/atividades/${atividade.id}/foto`"
            :alt="atividade.titulo"
            @error="e => e.target.style.display='none'"
          />
        </div>

        <!-- CONTEÚDO -->
        <div class="card-conteudo">
          <div class="card-topo">
            <span class="card-data">📅 {{ formatarData(atividade.data) }}</span>
            <!-- ÍCONES (só aparece se logado) -->
            <div class="card-acoes" v-if="isLogado">
              <button @click="editar(atividade)" class="btn-editar" title="Editar">✏️</button>
              <button @click="apagar(atividade.id)" class="btn-apagar" title="Apagar">🗑️</button>
            </div>
          </div>
          <h2 class="card-titulo">{{ atividade.titulo }}</h2>
          <p class="card-descricao">{{ atividade.descricao }}</p>
        </div>
      </div>
    </div>

    <!-- VAZIO -->
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
          headers: {
            "Authorization": `Bearer ${token}`
          }
        })
        if (!response.ok) throw new Error("Erro ao apagar")
        this.atividades = this.atividades.filter(a => a.id !== id)
      } catch (error) {
        alert("Erro ao apagar atividade: " + error.message)
      }
    }
  }
}
</script>

<style scoped>
.atividades-wrapper {
  max-width: 800px;
  margin: 0 auto;
  padding: 40px 20px;
  font-family: 'Poppins', sans-serif;
}

.atividades-header {
  text-align: center;
  margin-bottom: 40px;
}

.atividades-titulo {
  font-size: 36px;
  font-weight: 700;
  color: #1a1a1a;
  margin-bottom: 8px;
}

.atividades-subtitulo {
  font-size: 16px;
  color: #888;
}

/* LISTA */
.lista-atividades {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* CARD */
.card-atividade {
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.08);
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  border-left: 5px solid #ff8a3d;
}

.card-atividade:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 30px rgba(0,0,0,0.12);
}

/* IMAGEM */
.card-imagem img {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

/* CONTEÚDO */
.card-conteudo {
  padding: 24px;
}

.card-topo {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.card-data {
  font-size: 13px;
  color: #ff8a3d;
  font-weight: 600;
}

/* AÇÕES */
.card-acoes {
  display: flex;
  gap: 8px;
}

.btn-editar,
.btn-apagar {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 18px;
  padding: 4px 8px;
  border-radius: 8px;
  transition: background 0.2s;
}

.btn-editar:hover {
  background: #fff3e0;
}

.btn-apagar:hover {
  background: #fdecea;
}

.card-titulo {
  font-size: 20px;
  font-weight: 700;
  color: #1a1a1a;
  margin-bottom: 10px;
}

.card-descricao {
  font-size: 15px;
  color: #555;
  line-height: 1.6;
}

/* LOADING / VAZIO */
.loading,
.vazio {
  text-align: center;
  padding: 60px 20px;
  color: #888;
  font-size: 16px;
}

/* RESPONSIVO */
@media (max-width: 600px) {
  .atividades-titulo {
    font-size: 26px;
  }

  .card-conteudo {
    padding: 16px;
  }

  .card-titulo {
    font-size: 17px;
  }
}
</style>