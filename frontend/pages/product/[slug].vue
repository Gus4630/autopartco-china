<template>
  <div class="bg-gray-50 py-8">
    <div class="container-custom">
      <!-- Breadcrumb with Red Accent -->
      <nav class="mb-6">
        <ol class="flex items-center space-x-2 text-sm">
          <li>
            <NuxtLink :to="localePath('/')" class="text-accent-600 hover:text-accent-700 font-medium transition-colors">
              {{ t('common.home') }}
            </NuxtLink>
          </li>
          <li class="text-gray-400">
            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
            </svg>
          </li>
          <li>
            <NuxtLink :to="localePath('/products')" class="text-accent-600 hover:text-accent-700 font-medium transition-colors">
              {{ t('common.products') }}
            </NuxtLink>
          </li>
          <li class="text-gray-400">
            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
            </svg>
          </li>
          <li class="text-gray-700 font-medium line-clamp-1">
            {{ (locale === 'zh' ? product?.nameZh : product?.nameEn) || product?.partNumber }}
          </li>
        </ol>
      </nav>

      <!-- Product Detail -->
      <div v-if="product" class="bg-white rounded-2xl shadow-xl overflow-hidden border-t-4 border-accent-500">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 p-6 md:p-10">
          <!-- Product Images -->
          <div>
            <!-- Main Image -->
            <div class="aspect-square bg-gradient-to-br from-gray-50 to-gray-100 rounded-xl overflow-hidden mb-4 shadow-lg border-2 border-gray-100 relative group">
              <img
                v-if="product.files && product.files.length > 0"
                :src="getImageUrl(product.files[selectedImageIndex].url)"
                :alt="locale === 'zh' ? product.nameZh : product.nameEn"
                class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500"
                loading="lazy"
              />
              <div v-else class="w-full h-full flex items-center justify-center text-gray-400">
                <div class="text-center">
                  <svg class="w-20 h-20 mx-auto mb-2 text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" />
                  </svg>
                  <p class="text-sm">{{ locale === 'zh' ? '暂无图片' : 'No Image Available' }}</p>
                </div>
              </div>
              <!-- Red accent corner -->
              <div class="absolute top-0 right-0 w-24 h-24 bg-gradient-to-br from-accent-500 to-accent-600 opacity-0 group-hover:opacity-10 transition-opacity duration-300 rounded-bl-full"></div>
            </div>

            <!-- Image Thumbnails -->
            <div v-if="product.files && product.files.length > 1" class="grid grid-cols-5 gap-2 mb-6">
              <button
                v-for="(file, index) in product.files"
                :key="file.id"
                @click="selectedImageIndex = index"
                :class="[
                  'aspect-square rounded-lg overflow-hidden border-3 transition-all duration-200',
                  selectedImageIndex === index
                    ? 'border-accent-600 shadow-lg ring-2 ring-accent-200 scale-105'
                    : 'border-gray-200 hover:border-accent-300 hover:shadow-md'
                ]"
              >
                <img
                  :src="getImageUrl(file.url)"
                  :alt="`${locale === 'zh' ? product.nameZh : product.nameEn} - Image ${index + 1}`"
                  class="w-full h-full object-cover"
                  loading="lazy"
                />
              </button>
            </div>

            <!-- Description (moved under images) -->
            <div class="p-6 bg-gray-50 rounded-xl">
              <h2 class="text-lg font-bold text-gray-900 mb-3 flex items-center gap-2">
                <span class="w-1 h-6 bg-accent-600 rounded-full"></span>
                {{ t('product.description') }}
              </h2>
              <p class="text-gray-700 leading-relaxed">
                {{ locale === 'zh' ? product.descriptionZh : product.descriptionEn }}
              </p>
            </div>
          </div>

          <!-- Product Information -->
          <div>
            <!-- Product Name -->
            <h1 class="text-3xl lg:text-4xl font-bold text-gray-900 mb-4 leading-tight">
              {{ locale === 'zh' ? product.nameZh : product.nameEn }}
            </h1>

            <!-- Condition & Quality Badges -->
            <div class="flex flex-wrap gap-2 mb-6">
              <span v-if="product.conditionEn" :class="`badge-${product.conditionEn.toLowerCase()}`" class="text-sm">
                {{ locale === 'zh' && product.conditionZh ? product.conditionZh : product.conditionEn }}
              </span>
              <span v-if="product.quality" :class="`badge-${product.quality.toLowerCase()}`" class="text-sm">
                {{ t(`product.quality_${product.quality.toLowerCase()}`) }}
              </span>
            </div>

            <!-- Product Details -->
            <div class="space-y-4 mb-8">
              <!-- Part Number -->
              <div class="bg-gradient-to-r from-accent-50 to-transparent p-4 rounded-lg border-l-4 border-accent-500">
                <span class="text-xs text-accent-700 font-semibold uppercase tracking-wide block mb-1">{{ t('product.partNumber') }}</span>
                <p class="font-bold text-gray-900 text-lg">{{ product.partNumber }}</p>
              </div>

              <!-- Car Brand -->
              <div class="flex items-center gap-3 p-3 border-l-4 border-gray-200 hover:border-accent-300 transition-colors">
                <svg class="w-5 h-5 text-accent-500 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 17a2 2 0 11-4 0 2 2 0 014 0zM19 17a2 2 0 11-4 0 2 2 0 014 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16V6a1 1 0 00-1-1H4a1 1 0 00-1 1v10a1 1 0 001 1h1m8-1a1 1 0 01-1 1H9m4-1V8a1 1 0 011-1h2.586a1 1 0 01.707.293l3.414 3.414a1 1 0 01.293.707V16a1 1 0 01-1 1h-1m-6-1a1 1 0 001 1h1M5 17a2 2 0 104 0m-4 0a2 2 0 114 0m6 0a2 2 0 104 0m-4 0a2 2 0 114 0" />
                </svg>
                <div class="flex-1">
                  <span class="text-xs text-gray-500 block">{{ locale === 'zh' ? '汽车品牌' : 'Car Brand' }}</span>
                  <p class="font-semibold text-gray-900">{{ locale === 'zh' ? product.carBrandZh : product.carBrandEn }}</p>
                </div>
              </div>

              <!-- Car Model -->
              <div class="flex items-center gap-3 p-3 border-l-4 border-gray-200 hover:border-accent-300 transition-colors">
                <svg class="w-5 h-5 text-accent-500 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z" />
                </svg>
                <div class="flex-1">
                  <span class="text-xs text-gray-500 block">{{ locale === 'zh' ? '汽车型号' : 'Car Model' }}</span>
                  <p class="font-semibold text-gray-900">{{ locale === 'zh' ? product.carModelZh : product.carModelEn }}</p>
                </div>
              </div>

              <!-- Category -->
              <div class="flex items-center gap-3 p-3 border-l-4 border-gray-200 hover:border-accent-300 transition-colors">
                <svg class="w-5 h-5 text-accent-500 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 21a4 4 0 01-4-4V5a2 2 0 012-2h4a2 2 0 012 2v12a4 4 0 01-4 4zm0 0h12a2 2 0 002-2v-4a2 2 0 00-2-2h-2.343M11 7.343l1.657-1.657a2 2 0 012.828 0l2.829 2.829a2 2 0 010 2.828l-8.486 8.485M7 17h.01" />
                </svg>
                <div class="flex-1">
                  <span class="text-xs text-gray-500 block">{{ t('product.category') }}</span>
                  <p class="font-semibold text-gray-900">{{ locale === 'zh' ? product.categoryZh : product.categoryEn }}</p>
                </div>
              </div>

              <!-- Part Year & Country in Grid -->
              <div class="grid grid-cols-2 gap-4">
                <div class="bg-gray-50 p-3 rounded-lg">
                  <span class="text-xs text-gray-500 block mb-1">{{ locale === 'zh' ? '零件年份' : 'Part Year' }}</span>
                  <p class="font-semibold text-gray-900">{{ product.partYear }}</p>
                </div>
                <div class="bg-gray-50 p-3 rounded-lg">
                  <span class="text-xs text-gray-500 block mb-1">{{ locale === 'zh' ? '产地' : 'Made In' }}</span>
                  <p class="font-semibold text-gray-900">{{ locale === 'zh' ? product.manufacturingCountryZh : product.manufacturingCountryEn }}</p>
                </div>
              </div>

              <!-- Factory Information -->
              <div class="bg-gradient-to-r from-blue-50 to-transparent p-4 rounded-lg border-l-4 border-blue-500">
                <div class="flex items-start gap-3">
                  <svg class="w-5 h-5 text-blue-600 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
                  </svg>
                  <div class="flex-1">
                    <span class="text-xs text-blue-700 font-semibold uppercase tracking-wide block mb-2">{{ locale === 'zh' ? '工厂信息' : 'Factory Information' }}</span>
                    <div v-if="product.factory && (product.factory.factoryNameEn || product.factory.factoryNameZh)" class="space-y-2">
                      <p class="font-bold text-gray-900">
                        {{ locale === 'zh' ? (product.factory.factoryNameZh || product.factory.factoryNameEn) : (product.factory.factoryNameEn || product.factory.factoryNameZh) }}
                      </p>
                      <p v-if="product.factory.factoryPartEn || product.factory.factoryPartZh" class="text-sm text-gray-700">
                        <span class="text-gray-500">{{ locale === 'zh' ? '产品类型' : 'Product Type' }}:</span>
                        {{ locale === 'zh' ? (product.factory.factoryPartZh || product.factory.factoryPartEn) : (product.factory.factoryPartEn || product.factory.factoryPartZh) }}
                      </p>
                      <p v-if="product.factory.factoryPhoneNumber" class="text-sm text-gray-700 flex items-center gap-2">
                        <svg class="w-4 h-4 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                        </svg>
                        {{ product.factory.factoryPhoneNumber }}
                      </p>
                      <a v-if="product.factory.factorySiteUrl" :href="ensureProtocol(product.factory.factorySiteUrl)" target="_blank" rel="noopener noreferrer" class="inline-flex items-center gap-2 text-sm text-accent-600 hover:text-accent-700 font-medium transition-colors">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14" />
                        </svg>
                        {{ locale === 'zh' ? '访问网站' : 'Visit Website' }}
                      </a>
                    </div>
                    <p v-else class="text-gray-500 text-sm italic">
                      {{ locale === 'zh' ? '暂无信息' : 'Not Available' }}
                    </p>
                  </div>
                </div>
              </div>
            </div>

            <!-- Action Buttons -->
            <div class="flex flex-col sm:flex-row gap-4">
              <NuxtLink :to="localePath('/contact')" class="btn-primary flex-1 text-center py-4 text-lg font-semibold shadow-lg hover:shadow-xl">
                <svg class="inline-block w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                </svg>
                {{ t('common.getQuote') }}
              </NuxtLink>
              <a
                :href="`https://wa.me/${config.public.whatsappNumber}?text=${whatsappMessage}`"
                target="_blank"
                rel="noopener noreferrer"
                class="bg-green-600 hover:bg-green-700 text-white flex-1 text-center py-4 text-lg font-semibold rounded-lg transition-colors duration-200 shadow-lg hover:shadow-xl"
              >
                <svg class="inline-block w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 24 24">
                  <path d="M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347m-5.421 7.403h-.004a9.87 9.87 0 01-5.031-1.378l-.361-.214-3.741.982.998-3.648-.235-.374a9.86 9.86 0 01-1.51-5.26c.001-5.45 4.436-9.884 9.888-9.884 2.64 0 5.122 1.03 6.988 2.898a9.825 9.825 0 012.893 6.994c-.003 5.45-4.437 9.884-9.885 9.884m8.413-18.297A11.815 11.815 0 0012.05 0C5.495 0 .16 5.335.157 11.892c0 2.096.547 4.142 1.588 5.945L.057 24l6.305-1.654a11.882 11.882 0 005.683 1.448h.005c6.554 0 11.89-5.335 11.893-11.893a11.821 11.821 0 00-3.48-8.413z"/>
                </svg>
                {{ t('footer.whatsapp') }}
              </a>
            </div>
          </div>
        </div>
      </div>

      <!-- Related Products -->
      <div v-if="relatedProducts && relatedProducts.length > 0" class="mt-16">
        <div class="mb-8">
          <h2 class="text-3xl font-bold text-gray-900 mb-3">
            {{ t('product.relatedProducts') }}
          </h2>
          <div class="accent-divider"></div>
        </div>
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
          <NuxtLink
            v-for="relatedProduct in relatedProducts"
            :key="relatedProduct.id"
            :to="localePath(`/product/${relatedProduct.slug}`)"
            class="group relative bg-white rounded-xl shadow-md hover:shadow-2xl transition-all duration-300 overflow-hidden border border-gray-100 hover:border-accent-200"
          >
            <!-- Red Accent Corner -->
            <div class="absolute top-0 right-0 w-20 h-20 bg-gradient-to-br from-accent-500 to-accent-600 opacity-0 group-hover:opacity-10 transition-opacity duration-300 rounded-bl-full"></div>

            <!-- Product Image -->
            <div class="aspect-square bg-gradient-to-br from-gray-50 to-gray-100 relative overflow-hidden">
              <img
                v-if="relatedProduct.files && relatedProduct.files.length > 0"
                :src="getImageUrl(relatedProduct.files[0].url)"
                :alt="locale === 'zh' ? relatedProduct.nameZh : relatedProduct.nameEn"
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
                <span v-if="relatedProduct.conditionEn" :class="`badge-${relatedProduct.conditionEn.toLowerCase()}`">
                  {{ locale === 'zh' && relatedProduct.conditionZh ? relatedProduct.conditionZh : relatedProduct.conditionEn }}
                </span>
                <span v-if="relatedProduct.quality" :class="`badge-${relatedProduct.quality.toLowerCase()}`">
                  {{ t(`product.quality_${relatedProduct.quality.toLowerCase()}`) }}
                </span>
              </div>

              <!-- Product Name -->
              <h3 class="font-bold text-gray-900 mb-2 line-clamp-2 group-hover:text-accent-600 transition-colors duration-200">
                {{ locale === 'zh' ? relatedProduct.nameZh : relatedProduct.nameEn }}
              </h3>

              <!-- Part Number -->
              <div class="flex items-center gap-2 mb-2">
                <span class="text-xs font-medium text-accent-600 bg-accent-50 px-2 py-1 rounded">
                  {{ relatedProduct.partNumber }}
                </span>
              </div>

              <!-- Car Info -->
              <p class="text-sm text-gray-600 flex items-center gap-1">
                <svg class="w-4 h-4 text-accent-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 17a2 2 0 11-4 0 2 2 0 014 0zM19 17a2 2 0 11-4 0 2 2 0 014 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16V6a1 1 0 00-1-1H4a1 1 0 00-1 1v10a1 1 0 001 1h1m8-1a1 1 0 01-1 1H9m4-1V8a1 1 0 011-1h2.586a1 1 0 01.707.293l3.414 3.414a1 1 0 01.293.707V16a1 1 0 01-1 1h-1m-6-1a1 1 0 001 1h1M5 17a2 2 0 104 0m-4 0a2 2 0 114 0m6 0a2 2 0 104 0m-4 0a2 2 0 114 0" />
                </svg>
                {{ locale === 'zh' ? relatedProduct.carBrandZh : relatedProduct.carBrandEn }} -
                {{ locale === 'zh' ? relatedProduct.carModelZh : relatedProduct.carModelEn }}
              </p>
            </div>
          </NuxtLink>
        </div>
      </div>

      <!-- Loading State -->
      <div v-if="pending" class="flex items-center justify-center py-20">
        <div class="text-center">
          <div class="relative inline-block">
            <div class="animate-spin rounded-full h-16 w-16 border-4 border-accent-100"></div>
            <div class="animate-spin rounded-full h-16 w-16 border-t-4 border-accent-600 absolute top-0 left-0"></div>
          </div>
          <p class="text-gray-600 mt-4 font-medium">{{ locale === 'zh' ? '加载中...' : 'Loading product...' }}</p>
        </div>
      </div>

      <!-- Error State -->
      <div v-if="error" class="bg-gradient-to-r from-red-50 to-transparent border-l-4 border-red-500 rounded-lg p-8 text-center shadow-lg">
        <svg class="w-16 h-16 text-red-500 mx-auto mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
        <p class="text-red-800 text-lg font-semibold mb-4">
          {{ error.message || (locale === 'zh' ? '加载产品失败' : 'Failed to load product') }}
        </p>
        <NuxtLink :to="localePath('/products')" class="btn-accent inline-block">
          <svg class="inline-block w-4 h-4 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 19l-7-7m0 0l7-7m-7 7h18" />
          </svg>
          {{ t('common.products') }}
        </NuxtLink>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const route = useRoute()
const { t, locale } = useI18n()
const localePath = useLocalePath()
const config = useRuntimeConfig()
const { getImageUrl } = useImageUrl()

const selectedImageIndex = ref(0)

// Ensure URL has protocol
const ensureProtocol = (url) => {
  if (!url) return ''
  const trimmedUrl = url.trim()
  if (trimmedUrl.startsWith('http://') || trimmedUrl.startsWith('https://')) {
    return trimmedUrl
  }
  return `http://${trimmedUrl}`
}

// Fetch product data from real API
const { data: product, pending, error } = await useFetch(
  `${config.public.apiBase}/auto-parts/${route.params.slug}`,
  { key: `product-${route.params.slug}`, lazy: true }
)

// Fetch related products only after product is loaded
const relatedProducts = ref(null)
watch(() => product.value?.id, async (productId) => {
  if (productId) {
    const { data } = await useFetch(
      `${config.public.apiBase}/auto-parts/${productId}/related?limit=4`,
      { key: `related-${productId}` }
    )
    relatedProducts.value = data.value
  }
}, { immediate: true })

// WhatsApp message
const whatsappMessage = computed(() => {
  if (!product.value) return ''
  const productName = locale.value === 'zh' ? product.value.nameZh : product.value.nameEn
  const partNumber = product.value.partNumber
  return encodeURIComponent(`Hello, I'm interested in ${productName} (Part #${partNumber})`)
})

// SEO Meta Tags
useSeoMeta({
  title: () => {
    if (!product.value) return 'Product'
    const name = locale.value === 'zh' ? product.value.nameZh : product.value.nameEn
    return `${name} - ${product.value.partNumber}`
  },
  description: () => {
    if (!product.value) return ''
    return locale.value === 'zh' ? product.value.descriptionZh : product.value.descriptionEn
  },
  ogTitle: () => {
    if (!product.value) return 'Product'
    const name = locale.value === 'zh' ? product.value.nameZh : product.value.nameEn
    return `${name} - AutoPartCo China`
  },
  ogDescription: () => {
    if (!product.value) return ''
    return locale.value === 'zh' ? product.value.descriptionZh : product.value.descriptionEn
  },
  ogImage: () => product.value?.files?.[0]?.url || '',
  ogType: 'product'
})

// Structured Data for SEO
useHead({
  script: [
    {
      type: 'application/ld+json',
      children: () => {
        if (!product.value) return '{}'
        const name = locale.value === 'zh' ? product.value.nameZh : product.value.nameEn
        const description = locale.value === 'zh' ? product.value.descriptionZh : product.value.descriptionEn
        const carBrand = locale.value === 'zh' ? product.value.carBrandZh : product.value.carBrandEn
        const factoryName = product.value.factory
          ? (locale.value === 'zh' ? product.value.factory.factoryNameZh : product.value.factory.factoryNameEn)
          : null
        const images = product.value.files?.map(file => file.url) || []

        const structuredData = {
          '@context': 'https://schema.org',
          '@type': 'Product',
          name: name,
          description: description || '',
          sku: product.value.partNumber,
          brand: {
            '@type': 'Brand',
            name: carBrand || 'AutoPartCo'
          },
          image: images
        }

        if (factoryName) {
          structuredData.manufacturer = {
            '@type': 'Organization',
            name: factoryName
          }
          if (product.value.factory.factorySiteUrl) {
            structuredData.manufacturer.url = product.value.factory.factorySiteUrl
          }
          if (product.value.factory.factoryPhoneNumber) {
            structuredData.manufacturer.telephone = product.value.factory.factoryPhoneNumber
          }
        }

        return JSON.stringify(structuredData)
      }
    }
  ]
})
</script>
