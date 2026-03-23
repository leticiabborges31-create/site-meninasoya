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
            Constituir uma Rede de Apoio, Acolhimento e Empoderamento (RAAE), com abordagem
            multidisciplinar, que forneça suporte ao acesso ao Ensino Superior, permanência e
            ascensão de meninas e mulheres nas áreas de Ciências Exatas, Engenharias e Computação.
          </p>
        </div>
      </div>
    </section>

    <section id="noticias" class="secao clara noticias">
      <h2 class="titulo-secao">Atividades</h2>

      <p v-if="carregando">Carregando...</p>
      <p v-if="erro" class="mensagem-erro">{{ erro }}</p>

      <div class="grid-noticias">
        <div
          class="card-noticia"
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
          <img src="@/assets/carolina.png" class="foto-coordenador" />
          <h3>Carolina Pereira Aranha</h3>
          <p>Coordenadora Geral</p>
        </div>

        <div class="card-coordenador">
          <img src="@/assets/regina.png.gif" class="foto-coordenador" />
          <h3>Regina Célia de Sousa</h3>
          <p>Coordenadora do Maranhão</p>
        </div>

        <div class="card-coordenador">
          <img src="@/assets/isabelle.png" class="foto-coordenador" />
          <h3>Isabelle Priscila Carneiro de Lima</h3>
          <p>Coordenadora da Bahia</p>
        </div>

        <div class="card-coordenador">
          <img src="@/assets/ines.png" class="foto-coordenador" />
          <h3>Inés Prieto Schmidt Sauerwein</h3>
          <p>Coordenadora do Sul</p>
        </div>

        <div class="card-coordenador">
          <img src="@/assets/graciella.png" class="foto-coordenador" />
          <h3>Graciella Watanabe</h3>
          <p>Coordenadora do Sudeste</p>
        </div>

      </div>
    </section>

    <footer class="rodape">
      <div class="rodape-container">

        <div class="rodape-bloco">
          <h3>Meninas Oyá</h3>
          <p>Projeto que incentiva meninas e mulheres nas áreas de Computação, Engenharia e Ciências Exatas.</p>
        </div>

        <div class="rodape-bloco">
          <h3>Contato</h3>
          <p>Email: contato@meninasoya.com</p>
          <p>Telefone: (00) 00000-0000</p>
        </div>

        <div class="rodape-bloco">
          <h3>Localização</h3>
          <p>Instituto Federal</p>
          <p>Brasil</p>
        </div>

        <div class="rodape-bloco">
          <h3>Redes Sociais</h3>
          <a href="https://www.instagram.com/meninasoya/" target="_blank">
            <img src="https://cdn-icons-png.flaticon.com/512/2111/2111463.png" />
          </a>
        </div>

      </div>
      <div class="copyright">
        © 2025 Meninas Oyá • Todos os direitos reservados
      </div>
    </footer>

  </main>
</template>

<script>
import api from '../service/api.js'

export default {
  data() {
    return {
      atividades: [],
      carregando: false,
      erro: ''
    }
  },

  async mounted() {
    this.carregando = true
    try {
      const response = await api.get('/atividade')
      this.atividades = response.data
    } catch {
      this.erro = 'Não foi possível carregar as atividades.'
    } finally {
      this.carregando = false
    }
  }
}
</script>
