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
          <div>
            <select
              v-model="selectedCategory"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            >
              <option value="">{{ locale === 'zh' ? '所有分类' : 'All Categories' }}</option>
              <option v-for="category in categories" :key="category.id" :value="category.id">
                {{ category.name[locale] }}
              </option>
            </select>
          </div>

          <!-- Brand Filter -->
          <div>
            <select
              v-model="selectedBrand"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            >
              <option value="">{{ locale === 'zh' ? '所有品牌' : 'All Brands' }}</option>
              <option v-for="brand in brands" :key="brand.id" :value="brand.id">
                {{ brand.name[locale] }}
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
            <NuxtImg
              v-if="product.images && product.images.length > 0"
              :src="product.images[0]"
              :alt="product.name[locale]"
              class="w-full h-full object-cover"
              loading="lazy"
            />
          </div>
          <div class="p-4">
            <div class="flex gap-2 mb-2">
              <span :class="`badge-${product.condition.toLowerCase()}`">
                {{ t(`product.condition_${product.condition.toLowerCase()}`) }}
              </span>
              <span :class="`badge-${product.quality.toLowerCase()}`">
                {{ t(`product.quality_${product.quality.toLowerCase()}`) }}
              </span>
            </div>
            <h3 class="font-semibold text-gray-900 mb-2 line-clamp-2">
              {{ product.name[locale] }}
            </h3>
            <p class="text-sm text-gray-600 mb-1">{{ product.partNumber }}</p>
            <p class="text-sm text-gray-600">{{ product.carBrand[locale] }} - {{ product.carModel[locale] }}</p>
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
const { fetchProducts, fetchCategories, fetchBrands } = useMockApi()

const searchQuery = ref('')
const selectedCategory = ref('')
const selectedBrand = ref('')

// Fetch products - USING MOCK DATA
const { data: products, pending } = await useAsyncData('all-products', () => fetchProducts())
const { data: categories } = await useAsyncData('categories', () => fetchCategories())
const { data: brands } = await useAsyncData('brands', () => fetchBrands())

// Real API (uncomment when backend is ready):
// const { data: products, pending } = await useFetch(
//   `${config.public.apiBase}/products`,
//   { key: 'all-products', lazy: true }
// )
// const { data: categories } = await useFetch(
//   `${config.public.apiBase}/categories`,
//   { key: 'categories', lazy: true }
// )
// const { data: brands } = await useFetch(
//   `${config.public.apiBase}/brands`,
//   { key: 'brands', lazy: true }
// )

// Filtered products
const filteredProducts = computed(() => {
  if (!products.value) return []

  let filtered = products.value

  if (searchQuery.value) {
    const query = searchQuery.value.toLowerCase()
    filtered = filtered.filter(product =>
      product.name[locale.value].toLowerCase().includes(query) ||
      product.partNumber.toLowerCase().includes(query)
    )
  }

  if (selectedCategory.value) {
    filtered = filtered.filter(product => product.category?.id === selectedCategory.value)
  }

  if (selectedBrand.value) {
    filtered = filtered.filter(product => product.brand?.id === selectedBrand.value)
  }

  return filtered
})

// SEO
useSeoMeta({
  title: t('meta.products.title'),
  description: t('meta.products.description')
})
</script>
