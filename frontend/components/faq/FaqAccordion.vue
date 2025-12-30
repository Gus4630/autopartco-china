<template>
  <div class="border-b border-gray-200">
    <button
      @click="toggle"
      class="w-full flex items-center justify-between py-4 px-2 text-left hover:bg-gray-50 transition-colors"
      :aria-expanded="isOpen"
      :aria-controls="`faq-${id}`"
    >
      <span class="font-medium text-gray-900">{{ question }}</span>
      <svg
        :class="['w-5 h-5 text-gray-500 transition-transform duration-200', isOpen && 'transform rotate-180']"
        fill="none"
        stroke="currentColor"
        viewBox="0 0 24 24"
      >
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
      </svg>
    </button>
    <div
      :id="`faq-${id}`"
      :class="['overflow-hidden transition-all duration-300 ease-in-out', isOpen ? 'max-h-96' : 'max-h-0']"
    >
      <div class="px-2 pb-4">
        <p class="text-gray-700 leading-relaxed">{{ answer }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  id: {
    type: String,
    required: true
  },
  question: {
    type: String,
    required: true
  },
  answer: {
    type: String,
    required: true
  }
})

const isOpen = ref(false)

const toggle = () => {
  isOpen.value = !isOpen.value
}
</script>
