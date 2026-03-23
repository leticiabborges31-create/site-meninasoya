<template>
  <div class="painel-container">

    <h2 class="titulo-painel">Adicionar Atividade</h2>

    <div class="formulario">

      <label>Título</label>
      <input v-model="titulo" type="text" placeholder="Digite o título da atividade" />

      <label>Descrição</label>
      <textarea v-model="descricao" placeholder="Digite a descrição da atividade"></textarea>

      <div class="botoes">
        <button class="btn-publicar" @click="publicar" :disabled="carregando">
          {{ carregando ? 'Publicando...' : 'Publicar' }}
        </button>
      </div>

      <div v-if="publicado" class="mensagem-sucesso">
        <p>Atividade publicada com sucesso!</p>
        <button class="btn-voltar" @click="$router.push('/')">
          Voltar ao início
        </button>
      </div>

      <p v-if="erro" class="mensagem-erro">{{ erro }}</p>

    </div>

  </div>
</template>

<script>
import api from '../service/api.js'

export default {
  data() {
    return {
      titulo: '',
      descricao: '',
      publicado: false,
      carregando: false,
      erro: ''
    }
  },

  methods: {
    async publicar() {
      this.erro = ''

      if (!this.titulo || !this.descricao) {
        this.erro = 'Preencha título e descrição.'
        return
      }

      this.carregando = true

      try {
        await api.post('/atividade', {
          titulo: this.titulo,
          descricao: this.descricao
        })

        this.publicado = true
        this.titulo = ''
        this.descricao = ''
      } catch {
        this.erro = 'Erro ao publicar atividade. Tente novamente.'
      } finally {
        this.carregando = false
      }
    }
  }
}
</script>
