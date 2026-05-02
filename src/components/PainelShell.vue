<template>
  <div class="admin-wrapper">
    <header class="admin-header">
      <div class="admin-header-content">
        <div class="header-titulo">
          <slot name="icon">
            <svg class="icon-header" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <rect x="3" y="3" width="18" height="18" rx="2"/>
            </svg>
          </slot>
          <div>
            <h1>{{ titulo }}</h1>
            <p>{{ subtitulo || 'Meninas Oyá' }}</p>
          </div>
        </div>
        <button @click="$emit('logout')" class="btn-logout">
          <svg class="icon-btn" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/>
            <polyline points="16 17 21 12 16 7"/>
            <line x1="21" y1="12" x2="9" y2="12"/>
          </svg>
          Sair
        </button>
      </div>
    </header>

    <slot name="tabs" />

    <main class="admin-main">
      <slot />
    </main>
  </div>
</template>

<script>
export default {
  name: 'PainelShell',
  props: {
    titulo: {
      type: String,
      required: true
    },
    subtitulo: {
      type: String,
      default: 'Meninas Oyá'
    }
  },
  emits: ['logout']
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700&display=swap');

*, *::before, *::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.admin-wrapper {
  min-height: 100vh;
  background:
    radial-gradient(circle at top left, rgba(240, 112, 48, 0.14), transparent 30%),
    linear-gradient(180deg, #fff7f1 0%, #f7fbf5 100%);
  color: #1a1a18;
  font-family: inherit;
}

.admin-header {
  position: sticky;
  top: 0;
  z-index: 50;
  background: rgba(26, 58, 22, 0.96);
  backdrop-filter: blur(10px);
  border-bottom: 1px solid rgba(255, 255, 255, 0.08);
}

.admin-header-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1.4rem 1.5rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 1rem;
}

.header-titulo {
  display: flex;
  align-items: center;
  gap: 0.9rem;
}

.header-titulo h1 {
  color: #fff8f1;
  font-size: clamp(1.35rem, 2vw, 2rem);
  margin-bottom: 0.15rem;
}

.header-titulo p {
  color: rgba(255, 248, 241, 0.7);
  font-size: 0.9rem;
}

/* ── ÍCONES ──────────────────────────────────────────── */
:deep(.icon-header) {
  width: 2rem;
  height: 2rem;
  color: #f07030;
  flex-shrink: 0;
}

.icon-btn {
  width: 1.15rem;
  height: 1.15rem;
  flex-shrink: 0;
}

/* ── BOTÃO SAIR ──────────────────────────────────────── */
.btn-logout {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  border: 1px solid rgba(255, 255, 255, 0.18);
  background: rgba(255, 255, 255, 0.08);
  color: #fff8f1;
  padding: 0.8rem 1.1rem;
  border-radius: 999px;
  font-weight: 600;
  cursor: pointer;
  transition: 0.2s ease;
  font-family: inherit;
}

.btn-logout:hover {
  background: rgba(240, 112, 48, 0.18);
  border-color: rgba(240, 112, 48, 0.35);
}

/* ── MAIN ────────────────────────────────────────────── */
.admin-main {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1.5rem;
  display: grid;
  gap: 1.5rem;
}
</style>
