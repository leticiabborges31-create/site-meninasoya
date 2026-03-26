<template>

<div class="lista-container">

  <h1>Atividades</h1>

  <ul class="lista-atividades">

    <li v-for="atividade in atividades" :key="atividade.id" class="card-atividade">

      <div class="conteudo">
        <strong>{{ atividade.titulo }}</strong>
        <p>{{ atividade.descricao }}</p>
      </div>

      <div class="acoes" v-if="isLogado">

        <img src="@/assets/lapis.png" class="icone" @click="editarAtividade(atividade)">

        <img src="@/assets/lixeira.png" class="icone" @click="excluirAtividade(atividade.id)">

      </div>

    </li>

  </ul>

</div>

</template>

<script>
export default {

data(){
  return{
    atividades: [],
    isLogado: false
  }
},

mounted(){
  // verifica se usuário está logado
  this.isLogado = localStorage.getItem("logado") === "true"

  // carrega atividades do backend
  this.carregarAtividades()
},

methods:{

  // 🔹 BUSCAR DO BACKEND
  async carregarAtividades(){
    try {
      const response = await fetch("http://localhost:8080/atividade")

      if (!response.ok) {
        throw new Error("Erro ao buscar atividades")
      }

      const dados = await response.json()

      this.atividades = dados

      // ordenar por data
      this.atividades.sort((a,b)=>{
        return new Date(b.data || 0) - new Date(a.data || 0)
      })

    } catch (error) {
      console.error("Erro ao carregar atividades", error)
    }
  },

  // 🔥 DELETE
  async excluirAtividade(id){

    if(confirm("Deseja excluir esta atividade?")){

      try {
        const response = await fetch(`http://localhost:8080/atividade/${id}`, {
          method: "DELETE"
        })

        if (!response.ok) {
          throw new Error("Erro ao deletar")
        }

        alert("Atividade excluída com sucesso!")

        // atualiza lista
        this.carregarAtividades()

      } catch (error) {
        alert("Erro ao excluir")
        console.error(error)
      }

    }
  },

  // ✏️ EDITAR
  editarAtividade(atividade){

    localStorage.setItem("atividade_editando", JSON.stringify(atividade))

    this.$router.push("/painel")

  }

}

}
</script>