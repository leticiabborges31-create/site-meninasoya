<template>
  <main class="coord-page">

    <!-- HEADER DA PÁGINA -->
    <section class="coord-hero">
      <div class="coord-hero-inner">
        <div class="coord-hero-texto">
          <span class="coord-tag">Coordenação</span>
          <h1 class="coord-titulo">
           As lideranças que fazem a .<br />
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
        <div class="coord-card" v-for="(coord, i) in coordenadoras" :key="i">
          <div class="coord-foto-wrap">
            <img :src="coord.foto" :alt="coord.nome" class="coord-foto" />
            <div class="coord-regiao-badge">{{ coord.regiao }}</div>
          </div>
          <div class="coord-info">
            <h3 class="coord-nome">{{ coord.nome }}</h3>
            <p class="coord-uni">{{ coord.universidade }}</p>
            <p class="coord-estado">{{ coord.estado }}</p>
          </div>
        </div>
      </div>
    </section>

  </main>
</template>



<style scoped>
.coord-page {
  background: #fdfcfa;
  font-family: 'Georgia', 'Times New Roman', serif;
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
  display: inline-block;
  font-family: 'Courier New', monospace;
  font-size: 0.75rem;
  letter-spacing: 0.16em;
  text-transform: uppercase;
  color: #f07030;
  border: 1px solid rgba(240, 112, 48, 0.45);
  padding: 0.3rem 0.85rem;
  border-radius: 999px;
  margin-bottom: 1.5rem;
}

.coord-titulo {
  font-size: clamp(2.8rem, 5.5vw, 5rem);
  font-weight: 400;
  line-height: 1.08;
  color: #f5f2ec;
  margin: 0;
  letter-spacing: -0.02em;
}

.coord-titulo em {
  font-style: italic;
  color: #f07030;
}

.coord-sub {
  font-size: 1.1rem;
  line-height: 1.75;
  color: rgba(245, 242, 236, 0.72);
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
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 2rem;
}

.coord-card {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  transition: transform 0.25s;
}

.coord-card:hover {
  transform: translateY(-5px);
}

.coord-foto-wrap {
  position: relative;
  border-radius: 1.2rem;
  overflow: hidden;
  aspect-ratio: 1;
  background: #f0f5ef;
  border: 1px solid rgba(45, 90, 39, 0.15);
}

.coord-foto {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
  transition: transform 0.4s ease;
}

.coord-card:hover .coord-foto {
  transform: scale(1.04);
}

.coord-regiao-badge {
  position: absolute;
  top: 0.8rem;
  left: 0.8rem;
  background: #d95f1c;
  color: #fff;
  font-family: 'Courier New', monospace;
  font-size: 0.7rem;
  font-weight: 700;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  padding: 0.3rem 0.75rem;
  border-radius: 999px;
}

.coord-info {
  padding: 0 0.25rem;
}

.coord-nome {
  font-size: 1.05rem;
  font-weight: 400;
  color: #1a3a16;
  margin: 0 0 0.35rem;
  line-height: 1.3;
}

.coord-uni {
  font-family: 'Courier New', monospace;
  font-size: 0.78rem;
  color: #d95f1c;
  font-weight: 700;
  letter-spacing: 0.06em;
  margin: 0 0 0.2rem;
}

.coord-estado {
  font-size: 0.88rem;
  color: #4a4a44;
  margin: 0;
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