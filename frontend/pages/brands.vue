<template>
  <div class="bg-gray-50 py-12">
    <div class="container-custom">
      <h1 class="text-4xl font-bold text-gray-900 mb-8">{{ t('common.brands') }}</h1>

      <div v-if="brands && brands.length > 0" class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
        <NuxtLink
          v-for="brand in brands"
          :key="brand.id"
          :to="localePath(`/products?brand=${brand.id}`)"
          class="card hover:shadow-lg transition-shadow p-6 text-center"
        >
          <div v-if="brand.logo" class="aspect-square bg-gray-100 rounded-lg mb-4 flex items-center justify-center p-4">
            <NuxtImg
              :src="brand.logo"
              :alt="brand.name[locale]"
              class="max-w-full max-h-full object-contain"
              loading="lazy"
            />
          </div>
          <div v-else class="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <svg class="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
            </svg>
          </div>
          <h3 class="text-lg font-semibold text-gray-900 mb-2">
            {{ brand.name[locale] }}
          </h3>
          <p v-if="brand.description" class="text-sm text-gray-600">
            {{ brand.description[locale] }}
          </p>
        </NuxtLink>
      </div>

      <div v-else-if="!pending" class="text-center py-12">
        <p class="text-gray-600">{{ locale === 'zh' ? '暂无品牌' : 'No brands available' }}</p>
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
const { fetchBrands } = useMockApi()

// USING MOCK DATA
const { data: brands, pending } = await useAsyncData('all-brands', () => fetchBrands())

// Real API (uncomment when backend is ready):
// const config = useRuntimeConfig()
// const { data: brands, pending } = await useFetch(
//   `${config.public.apiBase}/brands`,
//   { key: 'all-brands', lazy: true }
// )
</script>
