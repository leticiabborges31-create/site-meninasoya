<template>
  <main class="home">

    <!-- HERO -->
    <section class="hero">
      <div class="hero-inner">
        <div class="hero-texto">
          <span class="hero-tag">Rede de apoio e protagonismo</span>
          <h1 class="hero-titulo">
            Inspire,<br />
            <em>Empodere</em><br />
            e Transforme
          </h1>
          <p class="hero-sub">
            O projeto Meninas Oyá incentiva meninas e mulheres nas áreas de
            Computação, Engenharia e Ciências Exatas.
          </p>
          <div class="hero-btns">
            <a href="#sobre" @click.prevent="rolar('sobre')" class="btn-primary">Conheça o projeto</a>
            <a href="#atividades" @click.prevent="rolar('atividades')" class="btn-outline">Ver atividades</a>
          </div>
        </div>
        <div class="hero-imagem">
          <div class="hero-img-wrap">
            <img
              src="https://raw.githubusercontent.com/leticiabborges31-create/-assets-images-/5455c9e515bc4ce5c6691d8819f0c0a789ff524b/assets/images/meninas_oya%20(1).svg"
              alt="Meninas Oyá"
            />
          </div>
          <div class="hero-badge">+500 mulheres impactadas</div>
        </div>
      </div>
      <div class="hero-linha"></div>
    </section>

    <!-- SOBRE -->
    <section id="sobre" class="sobre">
      <div class="sobre-inner">
        <div class="sobre-imagem">
          <div class="sobre-img-wrap">
            <img
              src="https://raw.githubusercontent.com/leticiabborges31-create/-assets-images-/5455c9e515bc4ce5c6691d8819f0c0a789ff524b/assets/images/meninas_oya%20(1).svg"
              alt="Sobre o projeto"
            />
          </div>
          <div class="sobre-numero"><span>01</span>Sobre</div>
        </div>
        <div class="sobre-texto">
          <span class="secao-tag">Nossa missão</span>
          <h2 class="sobre-titulo">
            Uma presença forte para uma causa que importa.
          </h2>
          <p class="sobre-p">
            O projeto busca ampliar o acesso ao Ensino Superior, apoiar a permanência e
            estimular a ascensão de meninas e mulheres nas áreas de Ciências Exatas,
            Engenharias e Computação.
          </p>
          <blockquote class="sobre-quote">
            Constituir uma Rede de Apoio, Acolhimento e Empoderamento (RAAE), com
            abordagem multidisciplinar, que forneça suporte ao acesso ao Ensino Superior,
            permanência e ascensão de meninas e mulheres nas Ciências Exatas,
            Engenharias e Computação.
          </blockquote>
        </div>
      </div>
    </section>

    <!-- NÚMEROS -->
    <section class="numeros">
      <div class="numeros-inner">
        <div class="numero-item">
          <span class="numero-big">4</span>
          <span class="numero-label">Regiões do Brasil</span>
        </div>
        <div class="numero-divider"></div>
        <div class="numero-item">
          <span class="numero-big">8+</span>
          <span class="numero-label">Instituições parceiras</span>
        </div>
        <div class="numero-divider"></div>
        <div class="numero-item">
          <span class="numero-big">500+</span>
          <span class="numero-label">Mulheres impactadas</span>
        </div>
        <div class="numero-divider"></div>
        <div class="numero-item">
          <span class="numero-big">∞</span>
          <span class="numero-label">Potencial feminino</span>
        </div>
      </div>
    </section>

    <!-- ATIVIDADES -->
    <section id="atividades" class="atividades">
      <div class="atividades-header">
        <span class="secao-tag">O que fazemos</span>
        <h2 class="atividades-titulo">Atividades</h2>
      </div>

      <div class="carrossel" v-if="atividades.length > 0">
        <button class="btn-nav" @click="voltar" aria-label="Anterior">&#8592;</button>
        <div class="cards-wrap">
          <div
            class="card-ativ"
            v-for="(atividade, index) in atividadesVisiveis"
            :key="index"
            @click="abrirAtividade(atividade)"
          >
            <div class="card-num">{{ String(index + 1).padStart(2, '0') }}</div>
            <h3 class="card-titulo">{{ atividade.titulo }}</h3>
            <p class="card-desc">{{ atividade.descricao }}</p>
            <span class="card-data">{{ atividade.data }}</span>
          </div>
        </div>
        <button class="btn-nav" @click="avancar" aria-label="Próximo">&#8594;</button>
      </div>

      <div v-else class="sem-ativ">
        <p>Nenhuma atividade cadastrada no momento.</p>
      </div>
    </section>

    <!-- REGIÕES -->
    <section id="regioes" class="regioes">
      <div class="regioes-header">
        <span class="secao-tag">Presença nacional</span>
        <h2 class="regioes-titulo">Quem lidera essa rede<br />em diferentes regiões</h2>
      </div>

      <div class="regioes-inner">
        <div class="mapa-wrap">
          <img
            src="https://raw.githubusercontent.com/leticiabborges31-create/-assets-images-/5455c9e515bc4ce5c6691d8819f0c0a789ff524b/assets/images/meninas_oya%20(1).svg"
            alt="Mapa do Brasil"
            class="mapa-img"
          />
        </div>

        <div class="estados-wrap">
          <div
            class="estado-card"
            v-for="est in estados"
            :key="est.chave"
            :class="{ aberto: estadosAbertos[est.chave] }"
          >
            <button class="estado-header" @click="toggleEstado(est.chave)">
              <span class="estado-nome">{{ est.nome }}</span>
              <span class="estado-icon">{{ estadosAbertos[est.chave] ? '−' : '+' }}</span>
            </button>
            <div class="estado-corpo" v-show="estadosAbertos[est.chave]">
              <p class="estado-uni">{{ est.universidades }}</p>
              <p class="estado-coord">{{ est.coordenadora }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- FINANCIADORES -->
    <section class="financiadores">
      <div class="financiadores-inner">
        <h3 class="financiadores-titulo">Financiado por</h3>
        <div class="logos-financiadores">
          <div class="logo-item">
            <img 
              src="https://portalpadrao.ufma.br/ineof/imagens/logo-cnpq.png" 
              alt="CNPq - Conselho Nacional de Desenvolvimento Científico e Tecnológico"
              class="logo-financiador cnpq"
            />
          </div>
          <div class="logo-divider"></div>
          <div class="logo-item">
            <img 
              src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 200 100'%3E%3Crect fill='%23009600' width='50' height='100'/%3E%3Crect fill='%23FFFF00' x='50' width='50' height='100'/%3E%3Crect fill='%23003399' x='100' width='50' height='100'/%3E%3Crect fill='%23009600' x='150' width='50' height='100'/%3E%3C/svg%3E" 
              alt="Governo Federal do Brasil"
              class="logo-financiador gov"
            />
          </div>
        </div>
      </div>
    </section>

    <!-- RODAPÉ -->
    <footer class="rodape">
      <div class="rodape-inner">
        <div class="rodape-marca">
          <span class="rodape-mo">MO</span>
          <div>
            <strong>Meninas Oyá</strong>
            <span>Rede de apoio e protagonismo</span>
          </div>
        </div>
        <p class="rodape-copy">© 2025 Meninas Oyá · Todos os direitos reservados</p>
      </div>
    </footer>
  </main>
</template>

<script>
export default {
  data() {
    return {
      atividades: [],
      indiceAtual: 0,
      estadosAbertos: {
        maranhao: false,
        bahia: false,
        sul: false,
        sudeste: false
      },
      estados: [
        { chave: 'maranhao', nome: 'Maranhão', universidades: 'UFMA / IFMA', coordenadora: 'Coord. Regina Célia de Sousa' },
        { chave: 'bahia', nome: 'Bahia', universidades: 'IFBA / UFBA', coordenadora: 'Coord. Isabelle Priscila Carneiro de Lima' },
        { chave: 'sul', nome: 'Sul', universidades: 'UFSM / UFRGS', coordenadora: 'Coord. Inés Prieto Schmidt Sauerwein' },
        { chave: 'sudeste', nome: 'Sudeste', universidades: 'UFABC / UNILINS', coordenadora: 'Coord. Graciella Watanabe' }
      ]
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
    carregarAtividades() {
      fetch("http://localhost:8080/atividades")
        .then(res => res.json())
        .then(dados => {
          this.atividades = dados.sort((a, b) => new Date(b.data) - new Date(a.data));
        })
        .catch(err => console.error("Erro ao buscar atividades:", err));
    },

    abrirAtividade(atividade) {
      localStorage.setItem("atividadeSelecionada", JSON.stringify(atividade));
      this.$router.push("/atividade");
    },

    avancar() {
      this.indiceAtual = (this.indiceAtual + 1) % this.atividades.length;
    },

    voltar() {
      this.indiceAtual = (this.indiceAtual - 1 + this.atividades.length) % this.atividades.length;
    },

    toggleEstado(estado) {
      this.estadosAbertos[estado] = !this.estadosAbertos[estado];
    },

    rolar(id) {
      const el = document.getElementById(id);
      if (el) el.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
  }
};
</script>

<style scoped>
/* ── BASE ────────────────────────────────────────────── */
.home {
  font-family: 'Georgia', 'Times New Roman', serif;
  background: #fdfcfa;
  color: #1a1a18;
  overflow-x: hidden;
}

/* ── HERO ────────────────────────────────────────────── */
.hero {
  background-color: #1a3a16 !important;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  position: relative;
  overflow: hidden;
}

.hero::before {
  content: '';
  position: absolute;
  inset: 0;
  background:
    radial-gradient(ellipse 60% 80% at 80% 50%, rgba(217, 95, 28, 0.2) 0%, transparent 70%),
    radial-gradient(ellipse 40% 60% at 10% 80%, rgba(74, 138, 66, 0.2) 0%, transparent 60%);
  pointer-events: none;
  z-index: 0;
}

.hero-inner {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 4rem;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
  padding: 6rem 4rem;
  position: relative;
  z-index: 1;
}

.hero-tag {
  display: inline-block;
  font-family: 'Courier New', monospace;
  font-size: 0.78rem;
  letter-spacing: 0.14em;
  text-transform: uppercase;
  color: #f07030;
  border: 1px solid rgba(240, 112, 48, 0.5);
  padding: 0.35rem 0.9rem;
  border-radius: 999px;
  margin-bottom: 1.5rem;
}

.hero-titulo {
  font-size: clamp(3rem, 6vw, 5.5rem);
  font-weight: 400;
  line-height: 1.05;
  color: #f5f2ec !important;
  margin: 0 0 1.5rem;
  letter-spacing: -0.02em;
}

.hero-titulo em {
  font-style: italic;
  color: #f07030;
}

.hero-sub {
  font-size: 1.15rem;
  color: rgba(245, 242, 236, 0.8) !important;
  line-height: 1.7;
  max-width: 440px;
  margin-bottom: 2.5rem;
  font-family: 'Georgia', serif;
}

.hero-btns {
  display: flex;
  gap: 1rem;
  flex-wrap: wrap;
}

.btn-primary {
  background: #d95f1c;
  color: #fff !important;
  padding: 0.9rem 2rem;
  border-radius: 999px;
  text-decoration: none;
  font-family: 'Courier New', monospace;
  font-size: 0.88rem;
  letter-spacing: 0.06em;
  font-weight: 700;
  transition: background 0.2s, transform 0.2s;
}

.btn-primary:hover {
  background: #f07030;
  transform: translateY(-2px);
}

.btn-outline {
  border: 1.5px solid rgba(245, 242, 236, 0.4);
  color: #f5f2ec !important;
  padding: 0.9rem 2rem;
  border-radius: 999px;
  text-decoration: none;
  font-family: 'Courier New', monospace;
  font-size: 0.88rem;
  letter-spacing: 0.06em;
  transition: border-color 0.2s, background 0.2s;
}

.btn-outline:hover {
  border-color: rgba(245, 242, 236, 0.8);
  background: rgba(245, 242, 236, 0.08);
}

.hero-imagem {
  position: relative;
  display: flex;
  justify-content: center;
}

.hero-img-wrap {
  width: 100%;
  max-width: 460px;
  aspect-ratio: 1;
  border-radius: 2rem;
  overflow: hidden;
  border: 1px solid rgba(240, 112, 48, 0.3);
  background: rgba(74, 138, 66, 0.2);
  display: flex;
  align-items: center;
  justify-content: center;
}

.hero-img-wrap img {
  width: 85%;
  height: 85%;
  object-fit: contain;
}

.hero-badge {
  position: absolute;
  bottom: -1rem;
  left: -1rem;
  background: #d95f1c;
  color: #fff !important;
  padding: 0.8rem 1.3rem;
  border-radius: 1rem;
  font-size: 0.88rem;
  font-family: 'Courier New', monospace;
  font-weight: 700;
  letter-spacing: 0.04em;
  box-shadow: 0 8px 24px rgba(217, 95, 28, 0.4);
}

.hero-linha {
  height: 4px;
  background: linear-gradient(90deg, #d95f1c 0%, #4a8a42 100%);
}

/* ── SOBRE ────────────────────────────────────────────── */
.sobre {
  background: #fdfcfa;
  padding: 7rem 4rem;
}

.sobre-inner {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 5rem;
  align-items: center;
}

.sobre-img-wrap {
  border-radius: 2rem;
  overflow: hidden;
  background: #f0f5ef;
  border: 1px solid rgba(45, 90, 39, 0.15);
  aspect-ratio: 4/5;
  display: flex;
  align-items: center;
  justify-content: center;
}

.sobre-img-wrap img {
  width: 85%;
  object-fit: contain;
}

.sobre-numero {
  margin-top: 1.2rem;
  display: flex;
  align-items: baseline;
  gap: 0.5rem;
  font-family: 'Courier New', monospace;
  font-size: 0.8rem;
  letter-spacing: 0.1em;
  color: #4a4a44;
}

.sobre-numero span {
  font-size: 2.5rem;
  font-weight: 700;
  color: #2d5a27;
  line-height: 1;
}

.secao-tag {
  display: inline-block;
  font-family: 'Courier New', monospace;
  font-size: 0.75rem;
  letter-spacing: 0.14em;
  text-transform: uppercase;
  color: #d95f1c;
  border-bottom: 2px solid #d95f1c;
  padding-bottom: 0.2rem;
  margin-bottom: 1.2rem;
}

.sobre-titulo {
  font-size: clamp(1.9rem, 3.5vw, 2.8rem);
  font-weight: 400;
  line-height: 1.2;
  color: #1a3a16;
  margin: 0 0 1.5rem;
  letter-spacing: -0.02em;
}

.sobre-p {
  font-size: 1.05rem;
  line-height: 1.8;
  color: #4a4a44;
  margin-bottom: 1.5rem;
}

.sobre-quote {
  border-left: 4px solid #d95f1c;
  padding: 1rem 1.5rem;
  margin: 0;
  background: #fff4ed;
  border-radius: 0 1rem 1rem 0;
  font-size: 1rem;
  line-height: 1.75;
  color: #1a1a18;
  font-style: italic;
}

/* ── NÚMEROS ─────────────────────────────────────────── */
.numeros {
  background: #2d5a27;
  padding: 4rem;
}

.numeros-inner {
  max-width: 1100px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 2rem;
  flex-wrap: wrap;
}

.numero-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.4rem;
  flex: 1;
  min-width: 120px;
}

.numero-big {
  font-size: clamp(2.5rem, 5vw, 4rem);
  font-weight: 400;
  color: #f07030;
  line-height: 1;
  font-style: italic;
}

.numero-label {
  font-family: 'Courier New', monospace;
  font-size: 0.78rem;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: rgba(245, 242, 236, 0.75);
  text-align: center;
}

.numero-divider {
  width: 1px;
  height: 60px;
  background: rgba(245, 242, 236, 0.2);
  flex-shrink: 0;
}

/* ── ATIVIDADES ──────────────────────────────────────── */
.atividades {
  background: #f0f5ef;
  padding: 7rem 4rem;
}

.atividades-header {
  max-width: 1200px;
  margin: 0 auto 3.5rem;
}

.atividades-titulo {
  font-size: clamp(2rem, 4vw, 3.2rem);
  font-weight: 400;
  color: #1a3a16;
  margin: 0;
  letter-spacing: -0.02em;
}

.carrossel {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  gap: 1.5rem;
}

.btn-nav {
  background: #2d5a27;
  color: #fff;
  border: none;
  width: 48px;
  height: 48px;
  border-radius: 50%;
  font-size: 1.2rem;
  cursor: pointer;
  flex-shrink: 0;
  transition: background 0.2s, transform 0.2s;
}

.btn-nav:hover {
  background: #d95f1c;
  transform: scale(1.08);
}

.cards-wrap {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1.5rem;
  flex: 1;
}

.card-ativ {
  background: #fff;
  border: 1px solid rgba(45, 90, 39, 0.15);
  border-radius: 1.5rem;
  padding: 2rem;
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s, border-color 0.2s;
  display: flex;
  flex-direction: column;
  gap: 0.8rem;
}

.card-ativ:hover {
  transform: translateY(-4px);
  box-shadow: 0 16px 40px rgba(45, 90, 39, 0.12);
  border-color: #4a8a42;
}

.card-num {
  font-family: 'Courier New', monospace;
  font-size: 0.78rem;
  color: #d95f1c;
  font-weight: 700;
  letter-spacing: 0.1em;
}

.card-titulo {
  font-size: 1.15rem;
  font-weight: 400;
  color: #1a3a16;
  margin: 0;
  line-height: 1.35;
}

.card-desc {
  font-size: 0.93rem;
  color: #4a4a44;
  line-height: 1.65;
  margin: 0;
  flex: 1;
}

.card-data {
  font-family: 'Courier New', monospace;
  font-size: 0.78rem;
  color: #d95f1c;
  letter-spacing: 0.08em;
}

.sem-ativ {
  max-width: 1200px;
  margin: 0 auto;
  text-align: center;
  color: #4a4a44;
  font-style: italic;
  padding: 3rem;
}

/* ── REGIÕES ──────────────────────────────────────────── */
.regioes {
  background: #fdfcfa;
  padding: 7rem 4rem;
}

.regioes-header {
  max-width: 1200px;
  margin: 0 auto 3.5rem;
}

.regioes-titulo {
  font-size: clamp(2rem, 4vw, 3.2rem);
  font-weight: 400;
  color: #1a3a16;
  margin: 0;
  letter-spacing: -0.02em;
  line-height: 1.2;
}

.regioes-inner {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 5rem;
  align-items: start;
}

.mapa-wrap {
  border-radius: 2rem;
  overflow: hidden;
  background: #f0f5ef;
  border: 1px solid rgba(45, 90, 39, 0.15);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2rem;
}

.mapa-img {
  width: 100%;
  max-width: 420px;
  object-fit: contain;
}

.estados-wrap {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.estado-card {
  border: 1.5px solid rgba(45, 90, 39, 0.15);
  border-radius: 1rem;
  overflow: hidden;
  transition: border-color 0.2s;
}

.estado-card.aberto {
  border-color: #4a8a42;
}

.estado-header {
  width: 100%;
  background: #fdfcfa;
  border: none;
  cursor: pointer;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.2rem 1.5rem;
  text-align: left;
  transition: background 0.15s;
}

.estado-header:hover {
  background: #f0f5ef;
}

.estado-card.aberto .estado-header {
  background: #2d5a27;
}

.estado-nome {
  font-size: 1.1rem;
  font-weight: 400;
  color: #1a3a16;
  letter-spacing: -0.01em;
}

.estado-card.aberto .estado-nome {
  color: #f5f2ec !important;
}

.estado-icon {
  font-size: 1.4rem;
  color: #d95f1c;
  font-weight: 300;
  line-height: 1;
}

.estado-corpo {
  padding: 1.2rem 1.5rem;
  background: #fff4ed;
  border-top: 1px solid rgba(217, 95, 28, 0.15);
}

.estado-uni {
  font-size: 1rem;
  font-weight: 700;
  color: #1a3a16;
  margin: 0 0 0.4rem;
}

.estado-coord {
  font-size: 0.9rem;
  color: #d95f1c;
  font-family: 'Courier New', monospace;
  margin: 0;
  letter-spacing: 0.04em;
}

/* ── FINANCIADORES ──────────────────────────────────── */
.financiadores {
  background: #fff4ed;
  padding: 5rem 4rem;
  border-top: 2px solid #f07030;
}

.financiadores-inner {
  max-width: 1200px;
  margin: 0 auto;
  text-align: center;
}

.financiadores-titulo {
  font-size: 1.8rem;
  font-weight: 400;
  color: #1a3a16;
  margin: 0 0 2.5rem;
  letter-spacing: -0.01em;
}

.logos-financiadores {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 4rem;
  flex-wrap: wrap;
}

.logo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100px;
}

.logo-divider {
  width: 2px;
  height: 80px;
  background: rgba(45, 90, 39, 0.2);
}

.logo-financiador {
  max-width: 180px;
  max-height: 100px;
  height: auto;
  object-fit: contain;
  transition: transform 0.3s, filter 0.3s;
}

.logo-financiador:hover {
  transform: scale(1.05);
}

/* ── RODAPÉ ──────────────────────────────────────────── */
.rodape {
  background: #1a3a16;
  padding: 3rem 4rem;
}

.rodape-inner {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 2rem;
  flex-wrap: wrap;
}

.rodape-marca {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.rodape-mo {
  display: grid;
  place-items: center;
  width: 2.75rem;
  height: 2.75rem;
  border-radius: 0.85rem;
  background: linear-gradient(135deg, #f07030, #d95f1c);
  color: #fff;
  font-weight: 700;
  font-size: 0.9rem;
  letter-spacing: 0.04em;
}

.rodape-marca strong {
  display: block;
  font-size: 1rem;
  color: #f5f2ec;
}

.rodape-marca span {
  font-size: 0.82rem;
  color: rgba(245, 242, 236, 0.55);
}

.rodape-copy {
  font-family: 'Courier New', monospace;
  font-size: 0.78rem;
  color: rgba(245, 242, 236, 0.45);
  letter-spacing: 0.06em;
  margin: 0;
}

/* ── RESPONSIVO ──────────────────────────────────────── */
@media (max-width: 900px) {
  .hero-inner,
  .sobre-inner,
  .regioes-inner {
    grid-template-columns: 1fr;
    gap: 3rem;
    padding: 4rem 1.5rem;
  }

  .hero { min-height: auto; }
  .hero-imagem { order: -1; }
  .hero-img-wrap { max-width: 300px; margin: 0 auto; }

  .sobre,
  .atividades,
  .regioes,
  .financiadores { padding: 4rem 1.5rem; }

  .numeros { padding: 3rem 1.5rem; }
  .numeros-inner { justify-content: center; }
  .numero-divider { display: none; }

  .cards-wrap { grid-template-columns: 1fr; }
  .carrossel { flex-direction: column; }

  .logos-financiadores { gap: 2rem; }
  
  .logo-divider {
    display: none;
  }
  
  .logo-financiador {
    max-width: 140px;
    max-height: 80px;
  }

  .rodape { padding: 2.5rem 1.5rem; }
  .rodape-inner { flex-direction: column; text-align: center; }
}
</style>