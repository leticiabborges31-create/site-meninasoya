<template>

<header class="navbar">

<div class="logo">Meninas Oyá</div>

<nav class="menu">
<ul>
<li><router-link to="/">Sobre</router-link></li>
<li><router-link to="/#noticias">Notícias</router-link></li>
<li><router-link to="/#coordenacao">Coordenação</router-link></li>
<li><router-link to="/admin">Administração</router-link></li>
</ul>
</nav>

</header>

<router-view />

</template>
