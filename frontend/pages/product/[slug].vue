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
          <li class="text-gray-600">{{ product?.name?.[locale] || product?.partNumber }}</li>
        </ol>
      </nav>

      <!-- Product Detail -->
      <div v-if="product" class="bg-white rounded-lg shadow-md overflow-hidden">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 p-6 md:p-8">
          <!-- Product Images -->
          <div>
            <div class="aspect-square bg-gray-100 rounded-lg overflow-hidden mb-4">
              <NuxtImg
                v-if="product.images && product.images.length > 0"
                :src="product.images[selectedImageIndex]"
                :alt="product.name[locale]"
                class="w-full h-full object-cover"
                loading="lazy"
              />
              <div v-else class="w-full h-full flex items-center justify-center text-gray-400">
                {{ locale === 'zh' ? '暂无图片' : 'No Image Available' }}
              </div>
            </div>

            <!-- Image Thumbnails -->
            <div v-if="product.images && product.images.length > 1" class="grid grid-cols-4 gap-2">
              <button
                v-for="(image, index) in product.images"
                :key="index"
                @click="selectedImageIndex = index"
                :class="[
                  'aspect-square rounded-md overflow-hidden border-2',
                  selectedImageIndex === index ? 'border-primary-600' : 'border-gray-200'
                ]"
              >
                <NuxtImg
                  :src="image"
                  :alt="`${product.name[locale]} - Image ${index + 1}`"
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
              {{ product.name[locale] }}
            </h1>

            <!-- Condition & Quality Badges -->
            <div class="flex flex-wrap gap-2 mb-6">
              <span :class="`badge-${product.condition.toLowerCase()}`">
                {{ t(`product.condition_${product.condition.toLowerCase()}`) }}
              </span>
              <span :class="`badge-${product.quality.toLowerCase()}`">
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
                <p class="font-semibold text-gray-900">{{ product.carBrand[locale] }}</p>
              </div>

              <!-- Car Model -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ locale === 'zh' ? '汽车型号' : 'Car Model' }}:</span>
                <p class="font-semibold text-gray-900">{{ product.carModel[locale] }}</p>
              </div>

              <!-- Category -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ t('product.category') }}:</span>
                <p class="font-semibold text-gray-900">{{ product.category.name[locale] }}</p>
              </div>

              <!-- Part Year -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ locale === 'zh' ? '零件年份' : 'Part Year' }}:</span>
                <p class="font-semibold text-gray-900">{{ product.partYear }}</p>
              </div>

              <!-- Manufacturing Country -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ locale === 'zh' ? '制造国家' : 'Manufacturing Country' }}:</span>
                <p class="font-semibold text-gray-900">{{ product.manufacturingCountry[locale] }}</p>
              </div>

              <!-- Factory Address -->
              <div class="border-b border-gray-200 pb-3">
                <span class="text-sm text-gray-600 block mb-1">{{ locale === 'zh' ? '工厂地址' : 'Factory Address' }}:</span>
                <p class="text-gray-900">{{ product.factoryAddress[locale] }}</p>
              </div>
            </div>

            <!-- Description -->
            <div class="mb-6">
              <h2 class="text-lg font-semibold text-gray-900 mb-2">
                {{ t('product.description') }}
              </h2>
              <p class="text-gray-700 leading-relaxed">
                {{ product.description[locale] }}
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
              <NuxtImg
                v-if="relatedProduct.images && relatedProduct.images.length > 0"
                :src="relatedProduct.images[0]"
                :alt="relatedProduct.name[locale]"
                class="w-full h-full object-cover"
                loading="lazy"
              />
            </div>
            <div class="p-4">
              <h3 class="font-semibold text-gray-900 mb-2 line-clamp-2">
                {{ relatedProduct.name[locale] }}
              </h3>
              <p class="text-sm text-gray-600 mb-2">{{ relatedProduct.partNumber }}</p>
              <div class="flex gap-2">
                <span :class="`badge-${relatedProduct.condition.toLowerCase()}`" class="text-xs">
                  {{ t(`product.condition_${relatedProduct.condition.toLowerCase()}`) }}
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
const { fetchProductBySlug, fetchRelatedProducts } = useMockApi()

const selectedImageIndex = ref(0)

// Fetch product data - USING MOCK DATA
const { data: product, pending, error } = await useAsyncData(
  `product-${route.params.slug}`,
  () => fetchProductBySlug(route.params.slug)
)

// Fetch related products
const { data: relatedProducts } = await useAsyncData(
  `related-${route.params.slug}`,
  () => product.value ? fetchRelatedProducts(product.value.id) : []
)

// Real API (uncomment when backend is ready):
// const { data: product, pending, error } = await useFetch(
//   `${config.public.apiBase}/products/${route.params.slug}`,
//   { key: `product-${route.params.slug}`, lazy: true }
// )
// const { data: relatedProducts } = await useFetch(
//   `${config.public.apiBase}/products/${route.params.slug}/related`,
//   { key: `related-${route.params.slug}`, lazy: true }
// )

// WhatsApp message
const whatsappMessage = computed(() => {
  if (!product.value) return ''
  const productName = product.value.name[locale.value]
  const partNumber = product.value.partNumber
  return encodeURIComponent(`Hello, I'm interested in ${productName} (Part #${partNumber})`)
})

// SEO Meta Tags
useSeoMeta({
  title: () => product.value ? `${product.value.name[locale.value]} - ${product.value.partNumber}` : 'Product',
  description: () => product.value?.description?.[locale.value] || '',
  ogTitle: () => product.value ? `${product.value.name[locale.value]} - AutoPartCo China` : 'Product',
  ogDescription: () => product.value?.description?.[locale.value] || '',
  ogImage: () => product.value?.images?.[0] || '',
  ogType: 'product'
})

// Structured Data for SEO
useHead({
  script: [
    {
      type: 'application/ld+json',
      children: () => {
        if (!product.value) return '{}'
        return JSON.stringify({
          '@context': 'https://schema.org',
          '@type': 'Product',
          name: product.value.name[locale.value],
          description: product.value.description?.[locale.value] || '',
          sku: product.value.partNumber,
          brand: {
            '@type': 'Brand',
            name: product.value.carBrand?.[locale.value] || 'AutoPartCo'
          },
          manufacturer: {
            '@type': 'Organization',
            name: product.value.factoryAddress?.[locale.value] || ''
          },
          image: product.value.images || []
        })
      }
    }
  ]
})
</script>
