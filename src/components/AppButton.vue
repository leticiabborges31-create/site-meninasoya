<template>
  <button
    :type="type"
    :disabled="disabled || loading"
    :class="['app-btn', `app-btn--${variant}`, { 'app-btn--full': full, 'app-btn--loading': loading }]"
    v-bind="$attrs"
  >
    <svg v-if="loading" class="btn-spinner" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
      <path d="M12 2v4M12 18v4M4.93 4.93l2.83 2.83M16.24 16.24l2.83 2.83M2 12h4M18 12h4M4.93 19.07l2.83-2.83M16.24 7.76l2.83-2.83"/>
    </svg>
    <slot />
  </button>
</template>

<script>
export default {
  name: 'AppButton',
  inheritAttrs: false,
  props: {
    variant:  { type: String,  default: 'primary' }, // primary | secondary | danger | ghost
    type:     { type: String,  default: 'button' },
    loading:  { type: Boolean, default: false },
    disabled: { type: Boolean, default: false },
    full:     { type: Boolean, default: false }
  }
}
</script>

<style scoped>
.app-btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  padding: 0.9rem 1.5rem;
  border: none;
  border-radius: 999px;
  font-family: inherit;
  font-size: 0.95rem;
  font-weight: 700;
  cursor: pointer;
  transition: transform 0.15s ease, box-shadow 0.15s ease, background 0.15s ease, opacity 0.15s;
  white-space: nowrap;
}

.app-btn:disabled {
  opacity: 0.55;
  cursor: not-allowed;
  transform: none !important;
  box-shadow: none !important;
}

.app-btn--full { width: 100%; }

/* PRIMARY — dark green */
.app-btn--primary {
  background: linear-gradient(135deg, #1a3a16, #2d5a27);
  color: #fff8f1;
  box-shadow: 0 8px 20px rgba(26, 58, 22, 0.18);
}
.app-btn--primary:not(:disabled):hover {
  transform: translateY(-2px);
  box-shadow: 0 14px 30px rgba(26, 58, 22, 0.24);
}

/* SECONDARY — orange accent */
.app-btn--secondary {
  background: linear-gradient(135deg, #d95f1c, #f07030);
  color: #fff8f1;
  box-shadow: 0 8px 20px rgba(217, 95, 28, 0.2);
}
.app-btn--secondary:not(:disabled):hover {
  transform: translateY(-2px);
  box-shadow: 0 14px 30px rgba(217, 95, 28, 0.28);
}

/* DANGER — red */
.app-btn--danger {
  background: #fff1ec;
  color: #c95420;
  border: 1px solid rgba(185, 28, 28, 0.15);
}
.app-btn--danger:not(:disabled):hover {
  background: #ffe4d8;
}

/* GHOST — outlined */
.app-btn--ghost {
  background: transparent;
  color: #32502c;
  border: 1.5px solid rgba(45, 90, 39, 0.2);
}
.app-btn--ghost:not(:disabled):hover {
  background: rgba(45, 90, 39, 0.06);
}

/* Spinner */
.btn-spinner {
  width: 1rem;
  height: 1rem;
  animation: spin 0.8s linear infinite;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}
</style>
