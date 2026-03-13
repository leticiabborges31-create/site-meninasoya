<template>

<main>

<section class="hero">
<div class="hero-texto">
<h1>Inspire, Empodere e Transforme</h1>
<p>
O projeto Meninas Oyá incentiva meninas e mulheres nas áreas de Computação,
Engenharia e Ciências Exatas.
</p>
</div>
</section>

<section id="inicio" class="secao clara fade-in">
<h2 class="titulo-secao">Sobre o Projeto</h2>

<div class="bloco-flex">

<img
src="https://raw.githubusercontent.com/leticiabborges31-create/-assets-images-/5455c9e515bc4ce5c6691d8819f0c0a789ff524b/assets/images/meninas_oya%20(1).svg"
class="imagem-redonda"
/>

<div class="texto-lado">

<p>
Constituir uma Rede de Apoio, Acolhimento e Empoderamento (RAAE), com abordagem multidisciplinar, que forneça suporte ao acesso ao Ensino Superior, permanência e ascensão de meninas e mulheres nas áreas de Ciências Exatas, Engenharias e Computação, em especial, daquelas oriundas de comunidades periféricas e/ou em situação de vulnerabilidade, envolvendo as regiões Nordeste, Sudeste e Sul do país.

</p>

</div>
</div>
</section>

<section id="noticias" class="secao clara noticias">

<h2 class="titulo-secao">Notícias</h2>

<div class="grid-noticias">

<div
class="card-noticia fade"
v-for="atividade in atividades"
:key="atividade.id"
>

<h3>{{ atividade.titulo }}</h3>
<p>{{ atividade.descricao }}</p>

</div>

</div>

</section>

<section id="coordenacao" class="secao clara coordenacao">

<h2 class="titulo-secao">Coordenação</h2>

<div class="grid-coordenacao">

<div class="card-coordenador">
<img src="https://via.placeholder.com/150" class="foto-coordenador">
<h3>Profª Carolina</h3>
<p>Coordenadora Geral</p>
</div>

<div class="card-coordenador">
<img src="https://via.placeholder.com/150" class="foto-coordenador">
<h3>Profª Regina</h3>
<p>Coordenadora</p>
</div>

<div class="card-coordenador">
<img src="https://via.placeholder.com/150" class="foto-coordenador">
<h3>Profª Regina</h3>
<p>Coordenadora</p>
</div>

</div>

</section>

<footer class="rodape">

<div class="rodape-conteudo">

<p class="rodape-texto">Siga-nos no Instagram</p>

<a href="https://www.instagram.com/meninasoya/" target="_blank">
<img src="https://cdn-icons-png.flaticon.com/512/2111/2111463.png">
</a>

<p class="copyright">
© 2025 Meninas Oyá
</p>

</div>

</footer>

</main>

</template>

<script>
export default {

data(){
return{
atividades:[]
}
},

mounted(){

fetch("http://localhost:8080/atividade")
.then(res => res.json())
.then(dados => {

this.atividades = dados

})

}

}
</script>