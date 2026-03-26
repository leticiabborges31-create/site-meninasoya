<template>

<div class="detalhe-container" v-if="atividade">

  <h1>{{ atividade.titulo }}</h1>

  <p><strong>Data:</strong> {{ atividade.data }}</p>

  <p>{{ atividade.descricao }}</p>

  <button @click="$router.push('/atividades')">
    Voltar
  </button>

</div>

</template>

<script>
export default {

  data(){
    return{
      atividade:null
    }
  },

  mounted(){
    this.atividade = JSON.parse(localStorage.getItem("atividadeSelecionada"))
  }

}
</script>