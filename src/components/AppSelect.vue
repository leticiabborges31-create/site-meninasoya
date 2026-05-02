<template>
  <div class="app-field">
    <label v-if="label" class="app-label">
      {{ label }}
      <span v-if="required" class="required-star" aria-hidden="true">*</span>
    </label>

    <div class="app-select-wrap" :class="{ 'has-error': error }">
      <select
        :value="modelValue"
        :required="required"
        :disabled="disabled"
        class="app-select"
        @change="$emit('update:modelValue', $event.target.value)"
      >
        <option v-if="placeholder" value="" disabled>{{ placeholder }}</option>
        <option
          v-for="opt in options"
          :key="opt.value"
          :value="opt.value"
        >{{ opt.label }}</option>
      </select>

      <!-- chevron -->
      <svg class="select-arrow" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
        <polyline points="6 9 12 15 18 9"/>
      </svg>
    </div>

    <p v-if="error" class="app-field-error">{{ error }}</p>
    <p v-else-if="hint" class="app-field-hint">{{ hint }}</p>
  </div>
</template>

<script>
export default {
  name: 'AppSelect',
  props: {
    label:       { type: String,  default: '' },
    modelValue:  { type: String,  default: '' },
    options:     { type: Array,   required: true },
    placeholder: { type: String,  default: 'Selecione...' },
    required:    { type: Boolean, default: false },
    disabled:    { type: Boolean, default: false },
    error:       { type: String,  default: '' },
    hint:        { type: String,  default: '' }
  },
  emits: ['update:modelValue']
}
</script>

<style scoped>
.app-field {
  display: flex;
  flex-direction: column;
  gap: 0.4rem;
}

.app-label {
  font-size: 0.875rem;
  font-weight: 700;
  color: #32502c;
}

.required-star { color: #d95f1c; margin-left: 2px; }

.app-select-wrap {
  position: relative;
}

.app-select {
  width: 100%;
  padding: 0.9rem 2.5rem 0.9rem 1rem;
  border: 1.5px solid rgba(45, 90, 39, 0.18);
  border-radius: 1rem;
  background: #fffdfa;
  color: #1a1a18;
  font-family: inherit;
  font-size: 0.95rem;
  appearance: none;
  cursor: pointer;
  transition: border-color 0.2s, box-shadow 0.2s, background 0.2s;
}

.app-select:focus {
  outline: none;
  border-color: rgba(217, 95, 28, 0.55);
  box-shadow: 0 0 0 4px rgba(217, 95, 28, 0.1);
  background: #fff;
}

.app-select:disabled {
  background: #f3f3f1;
  color: #999;
  cursor: not-allowed;
}

.has-error .app-select {
  border-color: rgba(185, 28, 28, 0.5);
}

.select-arrow {
  position: absolute;
  right: 0.9rem;
  top: 50%;
  transform: translateY(-50%);
  width: 1rem;
  height: 1rem;
  color: #7a8c78;
  pointer-events: none;
}

.app-field-error {
  font-size: 0.8rem;
  color: #b91c1c;
  margin: 0;
}

.app-field-hint {
  font-size: 0.8rem;
  color: #7a8c78;
  margin: 0;
}
</style>
