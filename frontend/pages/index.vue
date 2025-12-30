<template>
  <div>
    <!-- Hero Section -->
    <section class="bg-gradient-to-r from-primary-600 to-primary-800 text-white py-20">
      <div class="container-custom">
        <div class="max-w-3xl">
          <h1 class="text-4xl md:text-5xl font-bold mb-6">
            {{ t('home.hero.title') }}
          </h1>
          <p class="text-xl md:text-2xl mb-8 text-primary-100">
            {{ t('home.hero.subtitle') }}
          </p>
          <NuxtLink :to="localePath('/products')" class="btn-primary bg-white text-primary-600 hover:bg-gray-100 inline-block">
            {{ t('home.hero.cta') }}
          </NuxtLink>
        </div>
      </div>
    </section>

    <!-- Features Section -->
    <section class="py-16 bg-white">
      <div class="container-custom">
        <h2 class="text-3xl font-bold text-center text-gray-900 mb-12">
          {{ t('home.features.title') }}
        </h2>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          <!-- Quality Feature -->
          <div class="text-center">
            <div class="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
              <svg class="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-2">
              {{ t('home.features.quality.title') }}
            </h3>
            <p class="text-gray-600">
              {{ t('home.features.quality.description') }}
            </p>
          </div>

          <!-- Pricing Feature -->
          <div class="text-center">
            <div class="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
              <svg class="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-2">
              {{ t('home.features.pricing.title') }}
            </h3>
            <p class="text-gray-600">
              {{ t('home.features.pricing.description') }}
            </p>
          </div>

          <!-- Shipping Feature -->
          <div class="text-center">
            <div class="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
              <svg class="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path d="M9 17a2 2 0 11-4 0 2 2 0 014 0zM19 17a2 2 0 11-4 0 2 2 0 014 0z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16V6a1 1 0 00-1-1H4a1 1 0 00-1 1v10a1 1 0 001 1h1m8-1a1 1 0 01-1 1H9m4-1V8a1 1 0 011-1h2.586a1 1 0 01.707.293l3.414 3.414a1 1 0 01.293.707V16a1 1 0 01-1 1h-1m-6-1a1 1 0 001 1h1M5 17a2 2 0 104 0m-4 0a2 2 0 114 0m6 0a2 2 0 104 0m-4 0a2 2 0 114 0" />
              </svg>
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-2">
              {{ t('home.features.shipping.title') }}
            </h3>
            <p class="text-gray-600">
              {{ t('home.features.shipping.description') }}
            </p>
          </div>

          <!-- Support Feature -->
          <div class="text-center">
            <div class="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
              <svg class="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18.364 5.636l-3.536 3.536m0 5.656l3.536 3.536M9.172 9.172L5.636 5.636m3.536 9.192l-3.536 3.536M21 12a9 9 0 11-18 0 9 9 0 0118 0zm-5 0a4 4 0 11-8 0 4 4 0 018 0z" />
              </svg>
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-2">
              {{ t('home.features.support.title') }}
            </h3>
            <p class="text-gray-600">
              {{ t('home.features.support.description') }}
            </p>
          </div>
        </div>
      </div>
    </section>

    <!-- Featured Products Section -->
    <section class="py-16 bg-gray-50">
      <div class="container-custom">
        <div class="flex justify-between items-center mb-8">
          <h2 class="text-3xl font-bold text-gray-900">
            {{ t('common.products') }}
          </h2>
          <NuxtLink :to="localePath('/products')" class="text-primary-600 hover:text-primary-700 font-medium">
            {{ t('common.viewDetails') }} →
          </NuxtLink>
        </div>

        <div v-if="featuredProducts && featuredProducts.length > 0" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
          <NuxtLink
            v-for="product in featuredProducts"
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

        <div v-else class="text-center py-12">
          <p class="text-gray-600">Loading products...</p>
        </div>
      </div>
    </section>

    <!-- About Section -->
    <section class="py-16 bg-white">
      <div class="container-custom">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
          <div>
            <h2 class="text-3xl font-bold text-gray-900 mb-6">
              {{ t('home.about.title') }}
            </h2>
            <p class="text-gray-700 text-lg leading-relaxed mb-6">
              {{ t('home.about.description') }}
            </p>
            <div class="grid grid-cols-3 gap-6">
              <div class="text-center">
                <div class="text-4xl font-bold text-primary-600 mb-2">10+</div>
                <div class="text-sm text-gray-600">{{ t('home.about.experience') }}</div>
              </div>
              <div class="text-center">
                <div class="text-4xl font-bold text-primary-600 mb-2">500+</div>
                <div class="text-sm text-gray-600">{{ t('home.about.customers') }}</div>
              </div>
              <div class="text-center">
                <div class="text-4xl font-bold text-primary-600 mb-2">5000+</div>
                <div class="text-sm text-gray-600">{{ t('home.about.parts') }}</div>
              </div>
            </div>
          </div>
          <div class="bg-gray-100 rounded-lg aspect-video flex items-center justify-center">
            <svg class="w-24 h-24 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
            </svg>
          </div>
        </div>
      </div>
    </section>

    <!-- CTA Section -->
    <section class="py-16 bg-primary-600 text-white">
      <div class="container-custom text-center">
        <h2 class="text-3xl font-bold mb-4">
          {{ locale === 'zh' ? '准备好订购了吗？' : 'Ready to Order?' }}
        </h2>
        <p class="text-xl text-primary-100 mb-8">
          {{ locale === 'zh' ? '立即联系我们获取报价和更多信息' : 'Contact us today for quotes and more information' }}
        </p>
        <div class="flex flex-col sm:flex-row gap-4 justify-center">
          <NuxtLink :to="localePath('/contact')" class="btn-primary bg-white text-primary-600 hover:bg-gray-100">
            {{ t('common.contactUs') }}
          </NuxtLink>
          <a
            :href="`https://wa.me/${config.public.whatsappNumber}`"
            target="_blank"
            rel="noopener noreferrer"
            class="btn-secondary bg-primary-700 hover:bg-primary-800 text-white border-white"
          >
            {{ t('footer.whatsapp') }}
          </a>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { t, locale } = useI18n()
const localePath = useLocalePath()
const config = useRuntimeConfig()
const { getImageUrl } = useImageUrl()

// Fetch featured products from real API
const { data: productsData, pending } = await useFetch(
  `${config.public.apiBase}/auto-parts?page=0&size=6`,
  {
    key: 'featured-products',
    lazy: true
  }
)

// Extract products from paginated response
const featuredProducts = computed(() => productsData.value?.content || [])

// SEO Meta Tags
useSeoMeta({
  title: t('meta.home.title'),
  description: t('meta.home.description'),
  ogTitle: t('meta.home.title'),
  ogDescription: t('meta.home.description'),
  ogType: 'website'
})

// Structured Data for Organization
useHead({
  script: [
    {
      type: 'application/ld+json',
      children: JSON.stringify({
        '@context': 'https://schema.org',
        '@type': 'Organization',
        name: 'Shandong Baijian International Trade Co., Ltd',
        alternateName: '山东百基安国际贸易有限公司',
        url: config.public.siteUrl,
        logo: `${config.public.siteUrl}/logo.png`,
        contactPoint: {
          '@type': 'ContactPoint',
          telephone: config.public.companyPhone,
          contactType: 'sales',
          areaServed: 'Worldwide',
          availableLanguage: ['English', 'Chinese']
        },
        sameAs: [
          `https://wa.me/${config.public.whatsappNumber}`
        ]
      })
    }
  ]
})
</script>
