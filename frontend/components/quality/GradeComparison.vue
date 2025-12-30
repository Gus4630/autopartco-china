<template>
  <div class="bg-white rounded-lg shadow-md overflow-hidden">
    <div class="overflow-x-auto">
      <table class="w-full">
        <thead class="bg-gray-50">
          <tr>
            <th class="px-6 py-4 text-left text-sm font-semibold text-gray-900">{{ headers.grade }}</th>
            <th class="px-6 py-4 text-left text-sm font-semibold text-gray-900">{{ headers.quality }}</th>
            <th class="px-6 py-4 text-left text-sm font-semibold text-gray-900">{{ headers.price }}</th>
            <th class="px-6 py-4 text-left text-sm font-semibold text-gray-900">{{ headers.warranty }}</th>
            <th class="px-6 py-4 text-left text-sm font-semibold text-gray-900">{{ headers.bestFor }}</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-gray-200">
          <tr v-for="(grade, index) in grades" :key="index" class="hover:bg-gray-50">
            <td class="px-6 py-4 whitespace-nowrap">
              <span :class="['px-3 py-1 rounded-full text-sm font-semibold', getBadgeClass(grade.level)]">
                {{ grade.name }}
              </span>
            </td>
            <td class="px-6 py-4">
              <div class="flex items-center gap-1">
                <svg
                  v-for="i in 5"
                  :key="i"
                  :class="['w-4 h-4', i <= grade.level ? 'text-yellow-400' : 'text-gray-300']"
                  fill="currentColor"
                  viewBox="0 0 20 20"
                >
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                </svg>
              </div>
            </td>
            <td class="px-6 py-4 whitespace-nowrap">
              <span class="text-sm font-medium text-gray-900">{{ grade.price }}</span>
            </td>
            <td class="px-6 py-4 whitespace-nowrap">
              <span class="text-sm text-gray-700">{{ grade.warranty }}</span>
            </td>
            <td class="px-6 py-4">
              <span class="text-sm text-gray-700">{{ grade.bestFor }}</span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
defineProps({
  grades: {
    type: Array,
    required: true
  },
  headers: {
    type: Object,
    required: true
  }
})

const getBadgeClass = (level) => {
  if (level >= 5) return 'bg-green-100 text-green-800'
  if (level >= 4) return 'bg-blue-100 text-blue-800'
  if (level >= 3) return 'bg-yellow-100 text-yellow-800'
  return 'bg-gray-100 text-gray-800'
}
</script>
