<template>
  <main>
    <!-- HERO -->
    <section class="hero">
      <div class="hero-texto">
        <h1>Inspire, Empodere e Transforme</h1>
        <p>
          O projeto Meninas Oyá incentiva meninas e mulheres nas áreas de Computação,
          Engenharia e Ciências Exatas.
        </p>
      </div>
    </section>

    <!-- SOBRE -->
    <section id="sobre" class="secao clara fade-in">
      <h2 class="titulo-secao">Sobre o Projeto</h2>

      <div class="bloco-flex">
        <img
          src="https://raw.githubusercontent.com/leticiabborges31-create/-assets-images-/5455c9e515bc4ce5c6691d8819f0c0a789ff524b/assets/images/meninas_oya%20(1).svg"
          class="imagem-redonda"
          alt="Logo Meninas Oyá"
        />

        <div class="texto-lado">
          <p>
            Constituir uma Rede de Apoio, Acolhimento e Empoderamento (RAAE),
            com abordagem multidisciplinar, que forneça suporte ao acesso ao Ensino Superior,
            permanência e ascensão de meninas e mulheres nas áreas de Ciências Exatas,
            Engenharias e Computação.
          </p>
        </div>
      </div>
    </section>

    <!-- NOTÍCIAS -->
    <section id="noticias" class="secao clara noticias">
      <h2 class="titulo-secao">Notícias</h2>

      <div class="carrossel" v-if="noticias.length > 0">
        <button class="btn-carrossel esquerda" @click="voltar">❮</button>

        <div class="carrossel-container">
          <div
            class="card-noticia"
            v-for="(noticia, index) in noticiasVisiveis"
            :key="index"
            @click="abrirNoticia(noticia)"
          >
            <h3>{{ noticia.titulo }}</h3>
            <p>{{ noticia.descricao }}</p>
            <p class="data">{{ noticia.data }}</p>
          </div>
        </div>

        <button class="btn-carrossel direita" @click="avancar">❯</button>
      </div>

      <div v-else class="sem-noticias">
        <p>Nenhuma notícia cadastrada no momento.</p>
      </div>
    </section>

    <!-- COORDENAÇÃO -->
    <section id="coordenacao" class="secao clara coordenacao">
      <h2 class="titulo-secao">Coordenação</h2>

      <div class="grid-coordenacao">
        <div class="card-coordenador">
          <img src="@/assets/carolina.png" class="foto-coordenador">
          <h3>Carolina Pereira Aranha</h3>
          <a href="http://lattes.cnpq.br/4758149397073322" target="_blank" class="link-lattes">
            Currículo Lattes
          </a>
          <p>Coordenadora Geral</p>
        </div>

        <div class="card-coordenador">
          <img src="@/assets/regina.png.gif" class="foto-coordenador">
          <h3>Regina Célia de Sousa</h3>
          <a href="http://lattes.cnpq.br/4758149397073322" target="_blank" class="link-lattes">
            Currículo Lattes
          </a>
          <p>Coordenadora do Maranhão</p>
        </div>

        <div class="card-coordenador">
          <img src="@/assets/isabelle.png" class="foto-coordenador">
          <h3>Isabelle Priscila Carneiro de Lima</h3>
          <a href="http://lattes.cnpq.br/0324601203015395" target="_blank" class="link-lattes">
            Currículo Lattes
          </a>
          <p>Coordenadora da Bahia</p>
        </div>

        <div class="card-coordenador">
          <img src="@/assets/ines.png" class="foto-coordenador">
          <h3>Inés Prieto Schmidt Sauerwein</h3>
          <a href="
http://lattes.cnpq.br/7906512702835414" target="_blank" class="link-lattes">
            Currículo Lattes
          </a>
          <p>Coordenadora do Sul</p>
        </div>

        <div class="card-coordenador">
          <img src="@/assets/graciella.png" class="foto-coordenador">
          <h3>Graciella Watanabe</h3>
          <a href=" http://lattes.cnpq.br/0022322386442215" target="_blank" class="link-lattes">
            Currículo Lattes
          </a>
          <p>Coordenadora do Sudeste</p>
        </div>
      </div>
    </section>

    <!-- RODAPÉ -->
    <footer class="rodape">
      <div class="rodape-container">
        <div class="rodape-bloco">
          <h3>Meninas Oyá</h3>
          <p>
            Projeto que incentiva meninas e mulheres nas áreas de Computação,
            Engenharia e Ciências Exatas.
          </p>
        </div>

        <div class="rodape-bloco">
          <h3>Contato</h3>
          <p>Email: meninas_oya@ufma.br</p>
        </div>

        <div class="rodape-bloco">
          <h3>Localização</h3>
          <p>UFMA - Brasil</p>
        </div>

        <div class="rodape-bloco">
          <h3>Redes Sociais</h3>
          <a href="https://www.instagram.com/meninasoya/" target="_blank">
            <img src="https://cdn-icons-png.flaticon.com/512/2111/2111463.png" width="30">
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
export default {
  data() {
    return {
      noticias: [],
      atividades: [],
      indiceAtual: 0
    };
  },

  computed: {
    noticiasVisiveis() {
      if (this.noticias.length === 0) return [];

      if (this.noticias.length < 3) return this.noticias;

      return [
        this.noticias[this.indiceAtual],
        this.noticias[(this.indiceAtual + 1) % this.noticias.length],
        this.noticias[(this.indiceAtual + 2) % this.noticias.length]
      ];
    }
  },

  mounted() {
    fetch("http://localhost:8080/atividade")
      .then(res => res.json())
      .then(dados => {
        this.atividades = dados;
      })
      .catch(error => console.error(error));

    const dadosNoticias = localStorage.getItem("noticias");
    if (dadosNoticias) {
      this.noticias = JSON.parse(dadosNoticias);
      this.noticias.sort((a, b) => new Date(b.data) - new Date(a.data));
    }
  },

  methods: {
    abrirNoticia(noticia) {
      localStorage.setItem("noticiaSelecionada", JSON.stringify(noticia));
      this.$router.push("/noticia");
    },

    avancar() {
      this.indiceAtual =
        (this.indiceAtual + 1) % this.noticias.length;
    },

    voltar() {
      this.indiceAtual =
        (this.indiceAtual - 1 + this.noticias.length) % this.noticias.length;
    }
  }
};
</script>

<style>
.link-lattes {
  display: block;
  margin: 5px 0;
  color: #ff8a3d;
  font-size: 14px;
  font-weight: bold;
  text-decoration: none;
}

.link-lattes:hover {
  text-decoration: underline;
}
</style>