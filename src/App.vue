<template>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">

<header class="navbar">

<div class="logo">Meninas Oyá</div>
<nav class="menu">
<ul>

<li><a href="#sobre" @click.prevent="irParaSobre">Sobre</a></li>
<li><router-link to="/#noticias">Notícias</router-link></li>

<li><router-link to="/#coordenacao">Coordenação</router-link></li>
<li><router-link to="/parceria">Parceria</router-link></li>
<li><router-link to="/admin">Administração</router-link></li>

</ul>
</nav>
</header>

<router-view />

</template>
