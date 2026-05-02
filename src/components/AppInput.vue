<template>
  <div class="app-field">
    <label v-if="label" class="app-label">
      {{ label }}
      <span v-if="required" class="required-star" aria-hidden="true">*</span>
    </label>

    <div class="app-input-wrap" :class="{ 'has-error': error, 'has-prefix': $slots.prefix, 'has-suffix': $slots.suffix }">
      <span v-if="$slots.prefix" class="input-slot prefix">
        <slot name="prefix" />
      </span>

      <input
        v-bind="$attrs"
        :type="type"
        :value="modelValue"
        :placeholder="placeholder"
        :required="required"
        :disabled="disabled"
        class="app-input"
        @input="$emit('update:modelValue', $event.target.value)"
      />

      <span v-if="$slots.suffix" class="input-slot suffix">
        <slot name="suffix" />
      </span>
    </div>

    <p v-if="error" class="app-field-error">{{ error }}</p>
    <p v-else-if="hint" class="app-field-hint">{{ hint }}</p>
  </div>
</template>

<script>
export default {
  name: 'AppInput',
  inheritAttrs: false,
  props: {
    label:       { type: String,  default: '' },
    modelValue:  { type: [String, Number], default: '' },
    type:        { type: String,  default: 'text' },
    placeholder: { type: String,  default: '' },
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

.app-input-wrap {
  position: relative;
  display: flex;
  align-items: center;
}

.app-input {
  width: 100%;
  padding: 0.9rem 1rem;
  border: 1.5px solid rgba(45, 90, 39, 0.18);
  border-radius: 1rem;
  background: #fffdfa;
  color: #1a1a18;
  font-family: inherit;
  font-size: 0.95rem;
  transition: border-color 0.2s, box-shadow 0.2s, background 0.2s;
}

.app-input:focus {
  outline: none;
  border-color: rgba(217, 95, 28, 0.55);
  box-shadow: 0 0 0 4px rgba(217, 95, 28, 0.1);
  background: #fff;
}

.app-input:disabled {
  background: #f3f3f1;
  color: #999;
  cursor: not-allowed;
}

.has-error .app-input {
  border-color: rgba(185, 28, 28, 0.5);
}

.has-error .app-input:focus {
  box-shadow: 0 0 0 4px rgba(185, 28, 28, 0.1);
  border-color: #b91c1c;
}

.has-prefix .app-input { padding-left: 2.8rem; }
.has-suffix .app-input { padding-right: 2.8rem; }

.input-slot {
  position: absolute;
  display: flex;
  align-items: center;
  color: #888;
  pointer-events: none;
}
.input-slot.prefix { left: 0.9rem; }
.input-slot.suffix { right: 0.9rem; }

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
