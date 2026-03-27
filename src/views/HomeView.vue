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

    <!-- ATIVIDADES -->
    <section id="atividades" class="secao clara noticias">
      <h2 class="titulo-secao">Atividades</h2>

      <div class="carrossel" v-if="atividades.length > 0">
        <button class="btn-carrossel esquerda" @click="voltar">❮</button>

        <div class="carrossel-container">
          <div
            class="card-noticia"
            v-for="(atividade, index) in atividadesVisiveis"
            :key="index"
            @click="abrirAtividade(atividade)"
          >
            <h3>{{ atividade.titulo }}</h3>
            <p>{{ atividade.descricao }}</p>
            <p class="data">{{ atividade.data }}</p>
          </div>
        </div>

        <button class="btn-carrossel direita" @click="avancar">❯</button>
      </div>

      <div v-else class="sem-noticias">
        <p>Nenhuma atividade cadastrada no momento.</p>
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
          <a href="http://lattes.cnpq.br/7906512702835414" target="_blank" class="link-lattes">
            Currículo Lattes
          </a>
          <p>Coordenadora do Sul</p>
        </div>

        <div class="card-coordenador">
          <img src="@/assets/graciella.png" class="foto-coordenador">
          <h3>Graciella Watanabe</h3>
          <a href="http://lattes.cnpq.br/0022322386442215" target="_blank" class="link-lattes">
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
      atividades: [],
      indiceAtual: 0
    };
  },

  computed: {
    atividadesVisiveis() {
      if (this.atividades.length === 0) return [];

      if (this.atividades.length < 3) return this.atividades;

      return [
        this.atividades[this.indiceAtual],
        this.atividades[(this.indiceAtual + 1) % this.atividades.length],
        this.atividades[(this.indiceAtual + 2) % this.atividades.length]
      ];
    }
  },

  mounted() {
    this.carregarAtividades();
  },

  methods: {

    // ✅ FUNÇÃO CENTRAL (ESSA É A CORREÇÃO)
    carregarAtividades() {
      fetch("http://localhost:8080/atividade")
        .then(res => res.json())
        .then(dados => {
          this.atividades = dados;

          // 🔥 CORREÇÃO DA DATA (funciona com yyyy-MM-dd)
          this.atividades.sort((a, b) => {
            return new Date(b.data) - new Date(a.data);
          });
        })
        .catch(error => console.error("Erro ao buscar atividades:", error));
    },

    abrirAtividade(atividade) {
      localStorage.setItem("atividadeSelecionada", JSON.stringify(atividade));
      this.$router.push("/atividade");
    },

    avancar() {
      this.indiceAtual =
        (this.indiceAtual + 1) % this.atividades.length;
    },

    voltar() {
      this.indiceAtual =
        (this.indiceAtual - 1 + this.atividades.length) % this.atividades.length;
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