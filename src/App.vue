<template>
  <div class="app-shell">
    <header class="navbar">
      <router-link to="/" class="brand">
        <span class="brand-mark">MO</span>
        <div class="brand-copy">
          <strong>Meninas Oya</strong>
          <span>Rede de apoio e protagonismo</span>
        </div>
      </router-link>

      <nav class="menu" aria-label="Menu principal">
        <a href="#sobre" @click.prevent="navegarPara('sobre')">Sobre</a>
        <router-link to="/atividades">Atividades</router-link>
        <router-link to="/coordenacao">Coordenação</router-link>
        <router-link to="/parceria">Parceria</router-link>
        <router-link to="/admin" class="menu-cta">Administração</router-link>
      </nav>
    </header>

    <router-view />
  </div>
</template>

<script>
export default {
  mounted() {
    this.rolarParaHash(this.$route.hash);
  },

  watch: {
    '$route.hash'(hash) {
      this.$nextTick(() => this.rolarParaHash(hash));
    }
  },

  methods: {
    navegarPara(id) {
      if (this.$route.path !== '/') {
        this.$router.push({ path: '/', hash: `#${id}` });
        return;
      }

      this.rolarParaHash(`#${id}`);
    },

    rolarParaHash(hash) {
      if (!hash) return;

      const alvo = document.querySelector(hash);

      if (alvo) {
        alvo.scrollIntoView({ behavior: 'smooth', block: 'start' });
      }
    }
  }
};
</script>

<style scoped>
.app-shell {
  min-height: 100vh;
}

.navbar {
  position: sticky;
  top: 0;
  z-index: 1000;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 1.5rem;
  padding: 1rem 4.5rem;
  background: rgba(255, 243, 231, 0.9);
  backdrop-filter: blur(18px);
  border-bottom: 1px solid rgba(121, 76, 42, 0.12);
}

.brand {
  display: inline-flex;
  align-items: center;
  gap: 0.9rem;
  color: #1f1b17;
  text-decoration: none;
}

.brand-mark {
  display: grid;
  place-items: center;
  width: 2.75rem;
  height: 2.75rem;
  border-radius: 0.95rem;
  background: linear-gradient(135deg, #ff8a3d, #c84f1d);
  color: #fff9f4;
  font-weight: 700;
  letter-spacing: 0.04em;
  box-shadow: 0 14px 30px rgba(200, 79, 29, 0.24);
}

.brand-copy {
  display: flex;
  flex-direction: column;
}

.brand-copy strong {
  font-size: 1rem;
  font-weight: 700;
}

.brand-copy span {
  color: #736152;
  font-size: 0.82rem;
}

.menu {
  display: flex;
  align-items: center;
  gap: 0.9rem;
  flex-wrap: wrap;
}

.menu a {
  padding: 0.72rem 1rem;
  border-radius: 999px;
  color: #3f3228;
  text-decoration: none;
  font-size: 0.95rem;
  font-weight: 600;
  transition: background-color 0.25s ease, color 0.25s ease, transform 0.25s ease;
}

.menu a:hover,
.menu a.router-link-active {
  background: rgba(200, 79, 29, 0.1);
  color: #b2481f;
  transform: translateY(-1px);
}

.menu-cta {
  background: linear-gradient(135deg, #20160f, #4b2d20);
  color: #fff6f1 !important;
  box-shadow: 0 14px 30px rgba(32, 22, 15, 0.18);
}

.menu-cta:hover,
.menu-cta.router-link-active {
  background: linear-gradient(135deg, #ff8a3d, #c84f1d) !important;
  color: #fffdfb !important;
}

@media (max-width: 980px) {
  .navbar {
    padding: 1rem 1.25rem;
    flex-direction: column;
    align-items: stretch;
  }

  .menu {
    justify-content: center;
  }
}

@media (max-width: 640px) {
  .brand {
    justify-content: center;
  }

  .menu {
    gap: 0.5rem;
  }

  .menu a {
    flex: 1 1 calc(50% - 0.5rem);
    text-align: center;
  }
}
</style>
