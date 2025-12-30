<template>
  <div class="bg-gray-50 py-8">
    <div class="container-custom">
      <h1 class="text-4xl font-bold text-gray-900 mb-8">{{ t('common.products') }}</h1>

      <!-- Filters and Search -->
      <div class="bg-white rounded-lg shadow-md p-6 mb-8">
        <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
          <!-- Search -->
          <div class="md:col-span-2">
            <input
              v-model="searchQuery"
              type="text"
              :placeholder="t('common.searchPlaceholder')"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            />
          </div>

          <!-- Category Filter -->
          <div class="md:col-span-2">
            <select
              v-model="selectedCategory"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            >
              <option value="">{{ locale === 'zh' ? '所有分类' : 'All Categories' }}</option>
              <option v-for="category in categories" :key="category.nameEn" :value="category.nameEn">
                {{ locale === 'zh' ? category.nameZh : category.nameEn }}
              </option>
            </select>
          </div>
        </div>
      </div>

      <!-- Products Grid -->
      <div v-if="filteredProducts && filteredProducts.length > 0" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
        <NuxtLink
          v-for="product in filteredProducts"
          :key="product.id"
          :to="localePath(`/product/${product.slug}`)"
          class="card hover:shadow-lg transition-shadow"
        >
          <div class="aspect-square bg-gray-100">
            <img
              v-if="product.files && product.files.length > 0"
              :src="getImageUrl(product.files[0].url)"
              :alt="locale === 'zh' ? product.nameZh : product.nameEn"
              class="w-full h-full object-cover"
              loading="lazy"
            />
          </div>
          <div class="p-4">
            <div class="flex gap-2 mb-2">
              <span v-if="product.conditionEn" :class="`badge-${product.conditionEn.toLowerCase()}`">
                {{ locale === 'zh' && product.conditionZh ? product.conditionZh : product.conditionEn }}
              </span>
              <span v-if="product.quality" :class="`badge-${product.quality.toLowerCase()}`">
                {{ t(`product.quality_${product.quality.toLowerCase()}`) }}
              </span>
            </div>
            <h3 class="font-semibold text-gray-900 mb-2 line-clamp-2">
              {{ locale === 'zh' ? product.nameZh : product.nameEn }}
            </h3>
            <p class="text-sm text-gray-600 mb-1">{{ product.partNumber }}</p>
            <p class="text-sm text-gray-600">
              {{ locale === 'zh' ? product.carBrandZh : product.carBrandEn }} -
              {{ locale === 'zh' ? product.carModelZh : product.carModelEn }}
            </p>
          </div>
        </NuxtLink>
      </div>

      <!-- Empty State -->
      <div v-else-if="!pending" class="text-center py-12">
        <p class="text-gray-600 text-lg">{{ locale === 'zh' ? '未找到产品' : 'No products found' }}</p>
      </div>

      <!-- Loading State -->
      <div v-if="pending" class="flex items-center justify-center py-12">
        <div class="text-center">
          <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600 mx-auto mb-4"></div>
          <p class="text-gray-600">{{ locale === 'zh' ? '加载中...' : 'Loading...' }}</p>
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
const { getImageUrl } = useImageUrl()

const searchQuery = ref('')
const selectedCategory = ref('')

// Fetch products from real API
const { data: productsData, pending } = await useFetch(
  `${config.public.apiBase}/auto-parts?page=0&size=100`,
  { key: 'all-products', lazy: true }
)

// Fetch categories from real API
const { data: categories } = await useFetch(
  `${config.public.apiBase}/categories`,
  { key: 'categories', lazy: true }
)

// Extract products from paginated response
const products = computed(() => productsData.value?.content || productsData.value || [])

// Filtered products
const filteredProducts = computed(() => {
  if (!products.value) return []

  let filtered = products.value

  if (searchQuery.value) {
    const query = searchQuery.value.toLowerCase()
    filtered = filtered.filter(product => {
      const nameEn = product.nameEn?.toLowerCase() || ''
      const nameZh = product.nameZh?.toLowerCase() || ''
      const partNumber = product.partNumber?.toLowerCase() || ''
      return nameEn.includes(query) || nameZh.includes(query) || partNumber.includes(query)
    })
  }

  if (selectedCategory.value) {
    filtered = filtered.filter(product => product.categoryEn === selectedCategory.value)
  }

  return filtered
})

// SEO
useSeoMeta({
  title: t('meta.products.title'),
  description: t('meta.products.description')
})
</script>
