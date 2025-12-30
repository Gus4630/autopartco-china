<template>
  <div class="bg-gray-50 py-12">
    <div class="container-custom">
      <h1 class="text-4xl font-bold text-gray-900 mb-8">{{ t('common.categories') }}</h1>

      <div v-if="categories && categories.length > 0" class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
        <NuxtLink
          v-for="category in categories"
          :key="category.nameEn"
          :to="localePath(`/products?category=${category.nameEn}`)"
          class="card hover:shadow-lg transition-shadow p-6 text-center"
        >
          <div class="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <svg class="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z" />
            </svg>
          </div>
          <h3 class="text-lg font-semibold text-gray-900 mb-2">
            {{ locale === 'zh' ? category.nameZh : category.nameEn }}
          </h3>
          <p class="text-sm text-gray-600">
            {{ category.productCount }} {{ locale === 'zh' ? '个产品' : 'products' }}
          </p>
        </NuxtLink>
      </div>

      <div v-else-if="!pending" class="text-center py-12">
        <p class="text-gray-600">{{ locale === 'zh' ? '暂无分类' : 'No categories available' }}</p>
      </div>

      <div v-if="pending" class="flex items-center justify-center py-12">
        <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600"></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { t, locale } = useI18n()
const localePath = useLocalePath()
const config = useRuntimeConfig()

// Using real API
const { data: categories, pending } = await useFetch(
  `${config.public.apiBase}/categories`,
  { key: 'all-categories', lazy: true }
)
</script>
