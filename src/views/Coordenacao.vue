<script setup>
import carolinaFoto from '../assets/carolina.png'
import graciellaFoto from '../assets/graciella.png'
import inesFoto from '../assets/ines.png'
import isabelleFoto from '../assets/isabelle.png'
import reginaFoto from '../assets/regina.png'

const coordenadoras = [
  {
    nome: 'Carolina Pereira Aranha',
    lattes: 'https://lattes.cnpq.br/',
    regiao: 'Geral',
    foto: carolinaFoto,
  },
  {
    nome: 'Graciella Watanabe',
    lattes: 'https://lattes.cnpq.br/',
    regiao: 'Sudeste',
    foto: graciellaFoto,
  },
  {
    nome: ' Inés Prieto Schmidt Sauerwein',
    lattes: 'https://lattes.cnpq.br/',
    regiao: 'Sul',
    foto: inesFoto,
  },
  {
    nome: 'Isabelle Priscila Carneiro de Lima',
    lattes: 'https://lattes.cnpq.br/',
    regiao: 'Bahia',
    foto: isabelleFoto,
  },
  {
    nome: 'Regina Célia de Sousa ',
    lattes: 'https://lattes.cnpq.br/',
    regiao: 'Maranhão',
    foto: reginaFoto,
  },
]
</script>

<template>
  <main class="coord-page">

    <!-- HEADER DA PÁGINA -->
    <section class="coord-hero">
      <div class="coord-hero-inner">
        <div class="coord-hero-texto">
          <span class="coord-tag">Coordenação</span>
          <h1 class="coord-titulo">
           As lideranças que fazem a <br />
            rede acontecer<br />
            
          </h1>
        </div>
        <div class="coord-hero-detalhe">
          <p class="coord-sub">
            Conheça as mulheres incríveis que coordenam o projeto
            Meninas Oyá em cada região do Brasil.
          </p>
          <div class="coord-linha-detalhe"></div>
        </div>
      </div>
    </section>

    <!-- GRID DE COORDENADORAS -->
    <section class="coord-grid-section">
      <div class="coord-grid">
        <article class="coord-card" v-for="(coord, i) in coordenadoras" :key="i">
          <div class="coord-foto-wrap">
            <img :src="coord.foto" :alt="coord.nome" class="coord-foto" />
            <div class="coord-regiao-badge">{{ coord.regiao }}</div>
          </div>
          <div class="coord-info">
            <h3 class="coord-nome">{{ coord.nome }}</h3>
            <a :href="coord.lattes" target="_blank" rel="noopener noreferrer" class="coord-lattes">
              <span>Currículo Lattes</span>
              <svg width="12" height="12" viewBox="0 0 12 12" fill="none"><path d="M2.5 9.5L9.5 2.5M9.5 2.5H4M9.5 2.5V8" stroke="currentColor" stroke-width="1.6" stroke-linecap="round" stroke-linejoin="round"/></svg>
            </a>
          </div>
        </article>
      </div>
    </section>

  </main>
</template>



<style scoped>
.coord-page {
  background: #fdfcfa;
  font-family: inherit;
  color: #1a1a18;
}

/* ── HERO ──────────────────────────────────────────────── */
.coord-hero {
  background-color: #1a3a16;
  padding: 7rem 4rem 5rem;
  position: relative;
  overflow: hidden;
}

.coord-hero::before {
  content: '';
  position: absolute;
  inset: 0;
  background:
    radial-gradient(ellipse 50% 80% at 90% 50%, rgba(217, 95, 28, 0.18) 0%, transparent 65%),
    radial-gradient(ellipse 40% 60% at 5% 90%, rgba(74, 138, 66, 0.2) 0%, transparent 60%);
  pointer-events: none;
}

.coord-hero-inner {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 4rem;
  align-items: center;
  position: relative;
  z-index: 1;
}

.coord-tag {
  display: inline-flex;
  align-items: center;
  font-size: 0.72rem;
  letter-spacing: 0.12em;
  text-transform: uppercase;
  font-weight: 700;
  color: #f07030;
  background: rgba(240, 112, 48, 0.15);
  padding: 0.3rem 0.85rem;
  border-radius: 999px;
  margin-bottom: 1.5rem;
}

.coord-titulo {
  font-size: clamp(2.8rem, 5.5vw, 5rem);
  font-weight: 800;
  line-height: 1.06;
  color: #f5f2ec;
  margin: 0;
  letter-spacing: -0.03em;
}

.coord-titulo em {
  font-style: italic;
  color: #f07030;
}

.coord-sub {
  font-size: 1rem;
  line-height: 1.75;
  font-weight: 400;
  color: rgba(245, 242, 236, 0.68);
  margin-bottom: 2rem;
}

.coord-linha-detalhe {
  height: 3px;
  width: 80px;
  background: linear-gradient(90deg, #d95f1c, #4a8a42);
  border-radius: 2px;
}

/* ── GRID ──────────────────────────────────────────────── */
.coord-grid-section {
  padding: 5rem 4rem 7rem;
  background: #fdfcfa;
}

.coord-grid {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(210px, 1fr));
  gap: 1.5rem;
}

.coord-card {
  display: flex;
  flex-direction: column;
  background: #fff;
  border: 1px solid rgba(45, 90, 39, 0.12);
  border-radius: 1.5rem;
  overflow: hidden;
  transition: transform 0.25s ease, box-shadow 0.25s ease, border-color 0.25s ease;
}

.coord-card:hover {
  transform: translateY(-6px);
  box-shadow: 0 20px 48px rgba(45, 90, 39, 0.12);
  border-color: rgba(217, 95, 28, 0.25);
}

.coord-foto-wrap {
  position: relative;
  aspect-ratio: 1;
  background: #f0f5ef;
  overflow: hidden;
}

.coord-foto {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
  transition: transform 0.5s ease;
}

.coord-card:hover .coord-foto {
  transform: scale(1.06);
}

.coord-regiao-badge {
  position: absolute;
  top: 0.75rem;
  left: 0.75rem;
  background: rgba(217, 95, 28, 0.92);
  backdrop-filter: blur(4px);
  color: #fff;
  font-size: 0.65rem;
  font-weight: 700;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  padding: 0.28rem 0.7rem;
  border-radius: 999px;
}

.coord-info {
  padding: 1.1rem 1.25rem 1.4rem;
  display: flex;
  flex-direction: column;
  gap: 0.6rem;
  flex: 1;
  justify-content: space-between;
}

.coord-nome {
  font-size: 0.95rem;
  font-weight: 700;
  color: #1a3a16;
  margin: 0;
  line-height: 1.35;
}

.coord-lattes {
  display: inline-flex;
  align-items: center;
  gap: 0.4rem;
  align-self: flex-start;
  font-size: 0.75rem;
  font-weight: 700;
  color: #d95f1c;
  background: rgba(217, 95, 28, 0.08);
  padding: 0.35rem 0.85rem 0.35rem 0.85rem;
  border-radius: 999px;
  text-decoration: none;
  transition: background 0.18s ease, color 0.18s ease;
}

.coord-lattes:hover {
  background: rgba(217, 95, 28, 0.16);
  color: #b84e14;
}

/* ── RESPONSIVO ──────────────────────────────────────── */
@media (max-width: 900px) {
  .coord-hero {
    padding: 5rem 1.5rem 4rem;
  }

  .coord-hero-inner {
    grid-template-columns: 1fr;
    gap: 2rem;
  }

  .coord-grid-section {
    padding: 4rem 1.5rem;
  }

  .coord-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 480px) {
  .coord-grid {
    grid-template-columns: 1fr;
  }
}
</style>
