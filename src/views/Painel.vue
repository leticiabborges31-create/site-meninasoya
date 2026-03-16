<template>

<div class="painel-container">

<h2 class="titulo-painel">Adicionar Notícia</h2>

<div class="formulario">

<label>Título</label>
<input v-model="titulo" type="text" placeholder="Digite o título da notícia">

<label>Data</label>
<input type="date" v-model="data">

<label>Descrição</label>
<textarea v-model="descricao" placeholder="Digite a descrição da notícia"></textarea>

<label>Foto</label>
<input type="file" @change="uploadImagem">

<div class="botoes">

<button class="btn-salvar" @click="salvar">
Salvar
</button>

<button class="btn-publicar" @click="publicar">
Publicar
</button>

</div>

<div v-if="publicado" class="mensagem-sucesso">

<p>✅ Notícia cadastrada!</p>

<button class="btn-voltar" @click="$router.push('/')">
Voltar ao início
</button>

</div>

</div>

</div>

</template>

<script>
export default{

data(){
return{
titulo:'',
data:'',
descricao:'',
imagem:'',
publicado:false
}
},

methods:{

uploadImagem(e){

const file=e.target.files[0]

const reader=new FileReader()

reader.onload=(event)=>{
this.imagem=event.target.result
}

reader.readAsDataURL(file)

},

salvar(){

const noticia={
titulo:this.titulo,
data:this.data,
descricao:this.descricao,
imagem:this.imagem
}

localStorage.setItem("noticia_temp",JSON.stringify(noticia))

alert("Notícia salva!")

},

publicar(){

const noticia=JSON.parse(localStorage.getItem("noticia_temp"))

let noticias=JSON.parse(localStorage.getItem("noticias")) || []

noticias.push(noticia)

localStorage.setItem("noticias",JSON.stringify(noticias))

this.publicado = true

}

}

}
</script>
