<template>
  <div class="painel-container">

    <!-- BOTÃO SAIR -->
    <button class="logout" @click="logout">Sair</button>

    <h2>{{ editando ? "Editar Atividade" : "Adicionar Atividade" }}</h2>

    <div class="formulario">

      <label>Título</label>
      <input v-model="titulo" type="text" placeholder="Digite o título" />

      <label>Data</label>
      <input type="date" v-model="dataNoticia" />

      <label>Descrição</label>
      <textarea v-model="descricao" placeholder="Digite a descrição"></textarea>

      <label>Imagem</label>
      <input type="file" @change="uploadImagem" />

      <br><br>

      <button @click="publicar">Publicar</button>

      <div v-if="publicado">
        <p>✅ Salvo com sucesso</p>
      </div>

    </div>

  </div>
</template>

<script>
export default {

  data(){
    return{
      titulo:'',
      dataNoticia:'',
      descricao:'',
      file:null,
      publicado:false,
      editando:false
    }
  },

  methods:{

    uploadImagem(e){
      this.file = e.target.files[0]
    },

    async publicar(){
  try {

    const formData = new FormData()
    formData.append("titulo", this.titulo)
    formData.append("descricao", this.descricao)
    formData.append("data", this.dataNoticia)

    if(this.file){
      formData.append("foto", this.file)
    }

    const response = await fetch("http://localhost:8080/atividade", {
      method: "POST",
      body: formData
    })

    if (!response.ok) {
      const erro = await response.text()
      throw new Error(erro)
    }

    // ✅ NOVO: BUSCAR ATIVIDADES DE NOVO
    await fetch("http://localhost:8080/atividade")
      .then(res => res.json())
      .then(dados => {
        this.$emit("atualizarAtividades", dados) // 🔥 importante
      })

    this.publicado = true

    // limpar formulário
    this.titulo = ''
    this.dataNoticia = ''
    this.descricao = ''
    this.file = null

    alert("Salvo com sucesso!")

  } catch (e) {
    console.error(e)
    alert("Erro ao salvar: " + e.message)
  }
},

    // 🔴 LOGOUT
    logout(){
      localStorage.removeItem("logado")
      this.$router.push("/admin")
    }

  }
}
</script>

<style scoped>

.painel-container {
  max-width: 500px;
  margin: auto;
  padding: 20px;
}

.formulario {
  display: flex;
  flex-direction: column;
}

input, textarea {
  margin-bottom: 10px;
  padding: 8px;
}

button {
  padding: 10px;
  background: orange;
  color: white;
  border: none;
  cursor: pointer;
}

/* BOTÃO SAIR */
.logout {
  background: red;
  margin-bottom: 20px;
}

</style>