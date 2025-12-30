<template>
  <div class="bg-white rounded-lg shadow-md p-6 border-t-4" :class="borderClass">
    <div class="flex items-start justify-between mb-4">
      <div>
        <span :class="['px-3 py-1 rounded-full text-sm font-semibold', badgeClass]">
          {{ grade.badge }}
        </span>
        <h3 class="text-2xl font-bold text-gray-900 mt-3">
          {{ grade.title }}
        </h3>
      </div>
      <div class="flex items-center gap-1">
        <svg
          v-for="i in 5"
          :key="i"
          :class="['w-5 h-5', i <= grade.level ? 'text-yellow-400' : 'text-gray-300']"
          fill="currentColor"
          viewBox="0 0 20 20"
        >
          <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
        </svg>
      </div>
    </div>

    <p class="text-gray-700 mb-4">{{ grade.description }}</p>

    <div class="grid grid-cols-2 gap-4 mb-4 p-4 bg-gray-50 rounded-lg">
      <div>
        <p class="text-sm text-gray-600 font-medium">{{ t('qualityGuide.labels.price') }}</p>
        <p class="text-lg font-semibold text-gray-900">{{ grade.price }}</p>
      </div>
      <div>
        <p class="text-sm text-gray-600 font-medium">{{ t('qualityGuide.labels.warranty') }}</p>
        <p class="text-lg font-semibold text-gray-900">{{ grade.warranty }}</p>
      </div>
    </div>

    <div class="mb-4" v-if="grade.pros && grade.pros.length > 0">
      <h4 class="font-semibold text-gray-900 mb-2">{{ prosLabel }}</h4>
      <ul class="space-y-1">
        <li v-for="(pro, index) in grade.pros" :key="index" class="flex items-start text-sm text-gray-700">
          <svg class="w-5 h-5 text-green-500 mr-2 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
          </svg>
          {{ pro }}
        </li>
      </ul>
    </div>

    <div class="mb-4" v-if="grade.cons && grade.cons.length > 0">
      <h4 class="font-semibold text-gray-900 mb-2">{{ consLabel }}</h4>
      <ul class="space-y-1">
        <li v-for="(con, index) in grade.cons" :key="index" class="flex items-start text-sm text-gray-700">
          <svg class="w-5 h-5 text-red-500 mr-2 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
          </svg>
          {{ con }}
        </li>
      </ul>
    </div>

    <div class="pt-4 border-t border-gray-200">
      <p class="text-sm font-medium text-gray-900 mb-1">{{ recommendedLabel }}</p>
      <p class="text-sm text-gray-700">{{ grade.bestFor }}</p>
    </div>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { t } = useI18n()

const props = defineProps({
  grade: {
    type: Object,
    required: true
  },
  prosLabel: {
    type: String,
    default: 'Pros'
  },
  consLabel: {
    type: String,
    default: 'Cons'
  },
  recommendedLabel: {
    type: String,
    default: 'Recommended For'
  }
})

const badgeClass = computed(() => {
  const level = props.grade.level || 3
  if (level >= 5) return 'bg-green-100 text-green-800'
  if (level >= 4) return 'bg-blue-100 text-blue-800'
  if (level >= 3) return 'bg-yellow-100 text-yellow-800'
  return 'bg-gray-100 text-gray-800'
})

const borderClass = computed(() => {
  const level = props.grade.level || 3
  if (level >= 5) return 'border-green-500'
  if (level >= 4) return 'border-blue-500'
  if (level >= 3) return 'border-yellow-500'
  return 'border-gray-500'
})
</script>
