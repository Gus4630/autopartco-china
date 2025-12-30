<template>
  <div class="mb-12">
    <div class="flex items-center gap-4 mb-6">
      <div class="w-12 h-12 bg-primary-100 rounded-full flex items-center justify-center flex-shrink-0">
        <slot name="icon">
          <svg class="w-6 h-6 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8.228 9c.549-1.165 2.03-2 3.772-2 2.21 0 4 1.343 4 3 0 1.4-1.278 2.575-3.006 2.907-.542.104-.994.54-.994 1.093m0 3h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
        </slot>
      </div>
      <h2 class="text-2xl font-bold text-gray-900">{{ title }}</h2>
    </div>
    <div class="bg-white rounded-lg shadow-md">
      <slot></slot>
    </div>
  </div>
</template>

<script setup>
defineProps({
  title: {
    type: String,
    required: true
  }
})
</script>
