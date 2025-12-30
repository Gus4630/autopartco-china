<template>
  <div class="bg-gray-50 py-8">
    <div class="container-custom">
      <!-- Breadcrumb -->
      <nav class="mb-6 text-sm">
        <ol class="flex items-center space-x-2">
          <li>
            <NuxtLink :to="localePath('/')" class="text-primary-600 hover:underline">
              {{ t('common.home') }}
            </NuxtLink>
          </li>
          <li class="text-gray-400">/</li>
          <li>
            <NuxtLink :to="localePath('/products')" class="text-primary-600 hover:underline">
              {{ t('common.products') }}
            </NuxtLink>
          </li>
          <li class="text-gray-400">/</li>
          <li class="text-gray-600">{{ (locale === 'zh' ? product?.nameZh : product?.nameEn) || product?.partNumber }}</li>
        </ol>
      </nav>

      <!-- Product Detail -->
      <div v-if="product" class="bg-white rounded-lg shadow-md overflow-hidden">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 p-6 md:p-8">
          <!-- Product Images -->
          <div>
            <div class="aspect-square bg-gray-100 rounded-lg overflow-hidden mb-4">
              <img
                v-if="product.files && product.files.length > 0"
                :src="getImageUrl(product.files[selectedImageIndex].url)"
                :alt="locale === 'zh' ? product.nameZh : product.nameEn"
                class="w-full h-full object-cover"
                loading="lazy"
              />
              <div v-else class="w-full h-full flex items-center justify-center text-gray-400">
                {{ locale === 'zh' ? '暂无图片' : 'No Image Available' }}
              </div>
            </div>

            <!-- Image Thumbnails -->
            <div v-if="product.files && product.files.length > 1" class="grid grid-cols-4 gap-2">
              <button
                v-for="(file, index) in product.files"
                :key="file.id"
                @click="selectedImageIndex = index"
                :class="[
                  'aspect-square rounded-md overflow-hidden border-2',
                  selectedImageIndex === index ? 'border-primary-600' : 'border-gray-200'
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
          </div>

          <!-- Product Information -->
          <div>
            <!-- Product Name -->
            <h1 class="text-3xl font-bold text-gray-900 mb-4">
              {{ locale === 'zh' ? product.nameZh : product.nameEn }}
            </h1>

            <!-- Condition & Quality Badges -->
            <div class="flex flex-wrap gap-2 mb-6">
              <span v-if="product.conditionEn" :class="`badge-${product.conditionEn.toLowerCase()}`">
                {{ locale === 'zh' && product.conditionZh ? product.conditionZh : product.conditionEn }}
              </span>
              <span v-if="product.quality" :class="`badge-${product.quality.toLowerCase()}`">
                {{ t(`product.quality_${product.quality.toLowerCase()}`) }}
              </span>
            </div>

            <!-- Product Details -->
            <div class="space-y-4 mb-6">
              <!-- Part Number -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ t('product.partNumber') }}:</span>
                <p class="font-semibold text-gray-900">{{ product.partNumber }}</p>
              </div>

              <!-- Car Brand -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ locale === 'zh' ? '汽车品牌' : 'Car Brand' }}:</span>
                <p class="font-semibold text-gray-900">{{ locale === 'zh' ? product.carBrandZh : product.carBrandEn }}</p>
              </div>

              <!-- Car Model -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ locale === 'zh' ? '汽车型号' : 'Car Model' }}:</span>
                <p class="font-semibold text-gray-900">{{ locale === 'zh' ? product.carModelZh : product.carModelEn }}</p>
              </div>

              <!-- Category -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ t('product.category') }}:</span>
                <p class="font-semibold text-gray-900">{{ locale === 'zh' ? product.categoryZh : product.categoryEn }}</p>
              </div>

              <!-- Part Year -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ locale === 'zh' ? '零件年份' : 'Part Year' }}:</span>
                <p class="font-semibold text-gray-900">{{ product.partYear }}</p>
              </div>

              <!-- Manufacturing Country -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ locale === 'zh' ? '制造国家' : 'Manufacturing Country' }}:</span>
                <p class="font-semibold text-gray-900">{{ locale === 'zh' ? product.manufacturingCountryZh : product.manufacturingCountryEn }}</p>
              </div>

              <!-- Factory Address -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ locale === 'zh' ? '工厂地址' : 'Factory Address' }}:</span>
                <p class="text-gray-900">{{ locale === 'zh' ? product.factoryAddressZh : product.factoryAddressEn }}</p>
              </div>
            </div>

            <!-- Description -->
            <div class="mb-6">
              <h2 class="text-lg font-semibold text-gray-900 mb-2">
                {{ t('product.description') }}
              </h2>
              <p class="text-gray-700 leading-relaxed">
                {{ locale === 'zh' ? product.descriptionZh : product.descriptionEn }}
              </p>
            </div>

            <!-- Action Buttons -->
            <div class="flex flex-col sm:flex-row gap-4">
              <NuxtLink :to="localePath('/contact')" class="btn-primary flex-1 text-center">
                {{ t('common.getQuote') }}
              </NuxtLink>
              <a
                :href="`https://wa.me/${config.public.whatsappNumber}?text=${whatsappMessage}`"
                target="_blank"
                rel="noopener noreferrer"
                class="btn-secondary flex-1 text-center"
              >
                {{ t('footer.whatsapp') }}
              </a>
            </div>
          </div>
        </div>
      </div>

      <!-- Related Products -->
      <div v-if="relatedProducts && relatedProducts.length > 0" class="mt-12">
        <h2 class="text-2xl font-bold text-gray-900 mb-6">
          {{ t('product.relatedProducts') }}
        </h2>
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
          <NuxtLink
            v-for="relatedProduct in relatedProducts"
            :key="relatedProduct.id"
            :to="localePath(`/product/${relatedProduct.slug}`)"
            class="card hover:shadow-lg transition-shadow"
          >
            <div class="aspect-square bg-gray-100">
              <img
                v-if="relatedProduct.files && relatedProduct.files.length > 0"
                :src="getImageUrl(relatedProduct.files[0].url)"
                :alt="locale === 'zh' ? relatedProduct.nameZh : relatedProduct.nameEn"
                class="w-full h-full object-cover"
                loading="lazy"
              />
            </div>
            <div class="p-4">
              <h3 class="font-semibold text-gray-900 mb-2 line-clamp-2">
                {{ locale === 'zh' ? relatedProduct.nameZh : relatedProduct.nameEn }}
              </h3>
              <p class="text-sm text-gray-600 mb-2">{{ relatedProduct.partNumber }}</p>
              <div class="flex gap-2">
                <span v-if="relatedProduct.conditionEn" :class="`badge-${relatedProduct.conditionEn.toLowerCase()}`" class="text-xs">
                  {{ locale === 'zh' && relatedProduct.conditionZh ? relatedProduct.conditionZh : relatedProduct.conditionEn }}
                </span>
              </div>
            </div>
          </NuxtLink>
        </div>
      </div>

      <!-- Loading State -->
      <div v-if="pending" class="flex items-center justify-center py-12">
        <div class="text-center">
          <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600 mx-auto mb-4"></div>
          <p class="text-gray-600">{{ locale === 'zh' ? '加载中...' : 'Loading product...' }}</p>
        </div>
      </div>

      <!-- Error State -->
      <div v-if="error" class="bg-red-50 border border-red-200 rounded-lg p-6 text-center">
        <p class="text-red-800">{{ error.message || (locale === 'zh' ? '加载产品失败' : 'Failed to load product') }}</p>
        <NuxtLink :to="localePath('/products')" class="btn-primary mt-4 inline-block">
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
        const factoryAddress = locale.value === 'zh' ? product.value.factoryAddressZh : product.value.factoryAddressEn
        const images = product.value.files?.map(file => file.url) || []

        return JSON.stringify({
          '@context': 'https://schema.org',
          '@type': 'Product',
          name: name,
          description: description || '',
          sku: product.value.partNumber,
          brand: {
            '@type': 'Brand',
            name: carBrand || 'AutoPartCo'
          },
          manufacturer: {
            '@type': 'Organization',
            name: factoryAddress || ''
          },
          image: images
        })
      }
    }
  ]
})
</script>
