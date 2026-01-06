<template>
  <div class="bg-gray-50 py-12">
    <div class="container-custom">
      <h1 class="text-4xl font-bold text-gray-900 mb-8">{{ t('common.categories') }}</h1>
      <div v-if="categories && categories.length > 0" class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
        <NuxtLink
          v-for="category in categories"
          :key="category.nameEn"
          :to="localePath(`/products?category=${category.nameEn}`)"
          class="group card hover:shadow-xl transition-all p-6 text-center border border-gray-200 hover:border-accent-300"
        >
          <div class="w-16 h-16 bg-gradient-to-br from-gray-100 to-gray-200 group-hover:from-accent-50 group-hover:to-accent-100 rounded-full flex items-center justify-center mx-auto mb-4 transition-all duration-200">
            <svg class="w-8 h-8 text-gray-700 group-hover:text-accent-500 transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z" />
            </svg>
          </div>
          <h3 class="text-lg font-semibold text-gray-900 mb-2 group-hover:text-accent-600 transition-colors">
            {{ locale === 'zh' ? category.nameZh : category.nameEn }}
          </h3>
          <p class="text-sm text-gray-600">
            {{ category.displayCount }} {{ locale === 'zh' ? '个产品' : 'products' }}
          </p>
        </NuxtLink>
      </div>
      <div v-else-if="!pending" class="text-center py-12">
        <p class="text-gray-600">{{ locale === 'zh' ? '暂无分类' : 'No categories available' }}</p>
      </div>
      <div v-if="pending" class="flex items-center justify-center py-12">
        <div class="relative inline-block">
          <div class="animate-spin rounded-full h-12 w-12 border-3 border-accent-100"></div>
          <div class="animate-spin rounded-full h-12 w-12 border-t-3 border-accent-600 absolute top-0 left-0"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { t, locale } = useI18n()
const localePath = useLocalePath()
const config = useRuntimeConfig()

// Enhanced product count logic
const enhanceProductCount = (category) => {
  const actualCount = category.productCount || 0
  
  let boosted
  
  if (actualCount < 100) {
    boosted = Math.floor(actualCount * 5.8)
  } else if (actualCount < 150) {
    boosted = Math.floor(actualCount * 3)
  } else if (actualCount < 250) {
    boosted = Math.floor(actualCount * 2.3)
  } else if (actualCount < 400) {
    boosted = Math.floor(actualCount * 1.8)
  } else if (actualCount < 700) {
    boosted = Math.floor(actualCount * 1.5)
  } else {
    boosted = Math.floor(actualCount * 1.3)
  }
  
  return boosted
}

const enhanceCategory = (category) => ({
  ...category,
  displayCount: enhanceProductCount(category),
  actualCount: category.productCount
})

// Fetch and enhance categories
const { data: categoriesRaw, pending } = await useFetch(
  `${config.public.apiBase}/categories`,
  { key: 'all-categories', lazy: true }
)

// Transform categories with enhanced counts
const categories = computed(() => 
  categoriesRaw.value?.map(enhanceCategory) || []
)
</script>