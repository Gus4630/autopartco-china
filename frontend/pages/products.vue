<template>
  <div class="bg-gray-50 py-8">
    <div class="container-custom">
      <!-- Page Header with Red Accent -->
      <div class="text-center mb-8">
        <h1 class="text-4xl font-bold text-gray-900 mb-3">{{ t('common.products') }}</h1>
        <div class="accent-divider mx-auto"></div>
        <p class="text-gray-600 mt-4 max-w-2xl mx-auto">
          {{ locale === 'zh'
            ? '浏览我们的高质量汽车零部件精选系列'
            : 'Browse our curated selection of high-quality automotive parts'
          }}
        </p>
      </div>

      <!-- Filters and Search -->
      <div class="bg-white rounded-xl shadow-lg p-6 mb-8 border-t-4 border-accent-500">
        <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
          <!-- Search -->
          <div class="md:col-span-2">
            <label class="block text-sm font-medium text-gray-700 mb-2">
              {{ locale === 'zh' ? '搜索产品' : 'Search Products' }}
            </label>
            <div class="relative">
              <input
                v-model="searchQuery"
                type="text"
                :placeholder="t('common.searchPlaceholder')"
                class="w-full px-4 py-3 pl-10 border border-gray-300 rounded-lg focus:ring-2 focus:ring-accent-500 focus:border-accent-500 transition-all"
                @input="debouncedSearch"
              />
              <svg class="absolute left-3 top-3.5 w-5 h-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
              </svg>
            </div>
          </div>

          <!-- Category Filter -->
          <div class="md:col-span-2">
            <label class="block text-sm font-medium text-gray-700 mb-2">
              {{ locale === 'zh' ? '产品类别' : 'Category' }}
            </label>
            <div class="relative">
              <select
                v-model="selectedCategory"
                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-accent-500 focus:border-accent-500 appearance-none bg-white transition-all"
                @change="handleFilterChange"
              >
                <option value="">{{ locale === 'zh' ? '所有分类' : 'All Categories' }}</option>
                <option v-for="category in categories" :key="category.nameEn" :value="category.nameEn">
                  {{ locale === 'zh' ? category.nameZh : category.nameEn }}
                </option>
              </select>
              <svg class="absolute right-3 top-3.5 w-5 h-5 text-gray-400 pointer-events-none" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </div>
          </div>
        </div>
      </div>

      <!-- Products Grid -->
      <div v-if="products && products.length > 0" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
        <NuxtLink
          v-for="product in products"
          :key="product.id"
          :to="localePath(`/product/${product.slug}`)"
          class="group relative bg-white rounded-xl shadow-md hover:shadow-2xl transition-all duration-300 overflow-hidden border border-gray-100 hover:border-accent-200"
        >
          <!-- Red Accent Corner -->
          <div class="absolute top-0 right-0 w-20 h-20 bg-gradient-to-br from-accent-500 to-accent-600 opacity-0 group-hover:opacity-10 transition-opacity duration-300 rounded-bl-full"></div>

          <!-- Product Image -->
          <div class="aspect-square bg-gradient-to-br from-gray-50 to-gray-100 relative overflow-hidden">
            <img
              v-if="product.files && product.files.length > 0"
              :src="getImageUrl(product.files[0].url)"
              :alt="locale === 'zh' ? product.nameZh : product.nameEn"
              class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-500"
              loading="lazy"
            />
            <!-- Red accent line on hover -->
            <div class="absolute bottom-0 left-0 right-0 h-1 bg-gradient-to-r from-accent-500 to-accent-600 transform scale-x-0 group-hover:scale-x-100 transition-transform duration-300 origin-left"></div>
          </div>

          <!-- Product Info -->
          <div class="p-4">
            <!-- Badges -->
            <div class="flex flex-wrap gap-2 mb-3">
              <span v-if="product.conditionEn" :class="`badge-${product.conditionEn.toLowerCase()}`">
                {{ locale === 'zh' && product.conditionZh ? product.conditionZh : product.conditionEn }}
              </span>
              <span v-if="product.quality" :class="`badge-${product.quality.toLowerCase()}`">
                {{ t(`product.quality_${product.quality.toLowerCase()}`) }}
              </span>
            </div>

            <!-- Product Name -->
            <h3 class="font-bold text-gray-900 mb-2 line-clamp-2 group-hover:text-accent-600 transition-colors duration-200">
              {{ locale === 'zh' ? product.nameZh : product.nameEn }}
            </h3>

            <!-- Part Number -->
            <div class="flex items-center gap-2 mb-2">
              <span class="text-xs font-medium text-accent-600 bg-accent-50 px-2 py-1 rounded">
                {{ product.partNumber }}
              </span>
            </div>

            <!-- Car Info -->
            <p class="text-sm text-gray-600 flex items-center gap-1">
              <svg class="w-4 h-4 text-accent-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 17a2 2 0 11-4 0 2 2 0 014 0zM19 17a2 2 0 11-4 0 2 2 0 014 0z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16V6a1 1 0 00-1-1H4a1 1 0 00-1 1v10a1 1 0 001 1h1m8-1a1 1 0 01-1 1H9m4-1V8a1 1 0 011-1h2.586a1 1 0 01.707.293l3.414 3.414a1 1 0 01.293.707V16a1 1 0 01-1 1h-1m-6-1a1 1 0 001 1h1M5 17a2 2 0 104 0m-4 0a2 2 0 114 0m6 0a2 2 0 104 0m-4 0a2 2 0 114 0" />
              </svg>
              {{ locale === 'zh' ? product.carBrandZh : product.carBrandEn }} -
              {{ locale === 'zh' ? product.carModelZh : product.carModelEn }}
            </p>
          </div>
        </NuxtLink>
      </div>

      <!-- Empty State with Red Accent -->
      <div v-else-if="!loading && !initialLoading" class="text-center py-20">
        <div class="inline-block p-6 bg-accent-50 rounded-full mb-4">
          <svg class="w-16 h-16 text-accent-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4" />
          </svg>
        </div>
        <h3 class="text-xl font-semibold text-gray-900 mb-2">
          {{ locale === 'zh' ? '未找到产品' : 'No products found' }}
        </h3>
        <p class="text-gray-600">
          {{ locale === 'zh' ? '请尝试调整您的搜索或筛选条件' : 'Try adjusting your search or filters' }}
        </p>
      </div>

      <!-- Loading State with Red Accent -->
      <div v-if="initialLoading" class="flex items-center justify-center py-20">
        <div class="text-center">
          <div class="relative inline-block">
            <div class="animate-spin rounded-full h-16 w-16 border-4 border-accent-100"></div>
            <div class="animate-spin rounded-full h-16 w-16 border-t-4 border-accent-600 absolute top-0 left-0"></div>
          </div>
          <p class="text-gray-600 mt-4 font-medium">{{ locale === 'zh' ? '加载中...' : 'Loading...' }}</p>
        </div>
      </div>

      <!-- Load More Button -->
      <div v-if="hasMore && !loading" class="text-center mt-12">
        <button
          @click="loadMore"
          class="btn-accent px-8 py-3 text-lg"
        >
          {{ locale === 'zh' ? '加载更多产品' : 'Load More Products' }}
          <svg class="inline-block w-5 h-5 ml-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
          </svg>
        </button>
      </div>

      <!-- Loading More Indicator -->
      <div v-if="loading && !initialLoading" class="flex items-center justify-center py-8">
        <div class="text-center">
          <div class="relative inline-block">
            <div class="animate-spin rounded-full h-10 w-10 border-3 border-accent-100"></div>
            <div class="animate-spin rounded-full h-10 w-10 border-t-3 border-accent-600 absolute top-0 left-0"></div>
          </div>
          <p class="text-gray-600 text-sm mt-2">{{ locale === 'zh' ? '加载更多...' : 'Loading more...' }}</p>
        </div>
      </div>

      <!-- Infinite Scroll Trigger -->
      <div ref="scrollTrigger" class="h-10"></div>
    </div>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'
import { useDebounceFn } from '@vueuse/core'

const { t, locale } = useI18n()
const localePath = useLocalePath()
const config = useRuntimeConfig()
const { getImageUrl } = useImageUrl()

const searchQuery = ref('')
const selectedCategory = ref('')
const products = ref([])
const currentPage = ref(0)
const totalPages = ref(0)
const loading = ref(false)
const initialLoading = ref(true)
const scrollTrigger = ref(null)

// Fetch categories
const { data: categories } = await useFetch(
  `${config.public.apiBase}/categories`,
  { key: 'categories', lazy: true }
)

// Computed: Check if there are more pages
const hasMore = computed(() => currentPage.value < totalPages.value - 1)

// Function to fetch products
const fetchProducts = async (page = 0, append = false) => {
  try {
    loading.value = true

    // Build query parameters
    const params = new URLSearchParams()
    params.append('page', page.toString())
    params.append('size', '20')

    if (selectedCategory.value) {
      params.append('category', selectedCategory.value)
    }

    if (searchQuery.value.trim()) {
      params.append('search', searchQuery.value.trim())
    }

    const response = await $fetch(`${config.public.apiBase}/auto-parts?${params.toString()}`)

    if (append) {
      // Append to existing products
      products.value = [...products.value, ...(response.content || [])]
    } else {
      // Replace products
      products.value = response.content || []
    }

    currentPage.value = response.number || 0
    totalPages.value = response.totalPages || 0

  } catch (error) {
    console.error('Error fetching products:', error)
  } finally {
    loading.value = false
    initialLoading.value = false
  }
}

// Initial load
onMounted(async () => {
  await fetchProducts(0, false)

  // Setup intersection observer for infinite scroll
  if (scrollTrigger.value) {
    const observer = new IntersectionObserver(
      (entries) => {
        if (entries[0].isIntersecting && hasMore.value && !loading.value) {
          loadMore()
        }
      },
      { threshold: 0.1 }
    )
    observer.observe(scrollTrigger.value)

    // Cleanup
    onBeforeUnmount(() => {
      if (scrollTrigger.value) {
        observer.unobserve(scrollTrigger.value)
      }
    })
  }
})

// Load more products
const loadMore = async () => {
  if (hasMore.value && !loading.value) {
    await fetchProducts(currentPage.value + 1, true)
  }
}

// Handle filter change (category)
const handleFilterChange = async () => {
  await fetchProducts(0, false)
}

// Debounced search
const debouncedSearch = useDebounceFn(async () => {
  await fetchProducts(0, false)
}, 500)

// SEO
useSeoMeta({
  title: t('meta.products.title'),
  description: t('meta.products.description')
})
</script>
