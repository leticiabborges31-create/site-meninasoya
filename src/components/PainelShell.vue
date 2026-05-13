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
*, *::before, *::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.admin-wrapper {
  min-height: 100vh;
  background: var(--oya-bg);
  color: var(--oya-char);
  font-family: var(--font-body);
}

.admin-header {
  position: sticky;
  top: 0;
  z-index: 50;
  background: var(--oya-forest);
  backdrop-filter: blur(10px);
  border-bottom: 0.5px solid rgba(107, 170, 138, 0.15);
}

.admin-header-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1.25rem 1.5rem;
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
  color: var(--oya-cream);
  font-family: var(--font-display);
  font-size: clamp(1.2rem, 2vw, 1.6rem);
  margin-bottom: 0.1rem;
}

.header-titulo p {
  color: var(--oya-fern);
  font-size: 0.8rem;
}

/* ── ÍCONES ──────────────────────────────────────────── */
:deep(.icon-header) {
  width: 1.75rem;
  height: 1.75rem;
  color: var(--oya-glow);
  flex-shrink: 0;
}

.icon-btn {
  width: 1.1rem;
  height: 1.1rem;
  flex-shrink: 0;
}

/* ── BOTÃO SAIR ──────────────────────────────────────── */
.btn-logout {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  border: 1px solid rgba(168, 213, 192, 0.2);
  background: rgba(168, 213, 192, 0.07);
  color: var(--oya-mint);
  padding: 0.7rem 1.1rem;
  border-radius: var(--radius-pill);
  font-weight: 500;
  font-size: 0.85rem;
  cursor: pointer;
  transition: background 0.2s, border-color 0.2s;
  font-family: var(--font-body);
}

.btn-logout:hover {
  background: rgba(217, 79, 30, 0.15);
  border-color: rgba(217, 79, 30, 0.3);
  color: var(--oya-warm);
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
