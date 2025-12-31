<template>
  <div>
    <!-- Hero Section -->
    <section class="bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 text-white py-20 relative overflow-hidden">
      <!-- Waving Chinese Flag Background (GIF) -->
      <div class="absolute inset-0 opacity-70">
      <video autoplay muted loop class="w-full h-full object-cover" playbackRate="0.5">
        <source src="/china-flag-waving.mp4" type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      </div>

      <!-- Subtle red accent overlay -->
      <div class="absolute inset-0 bg-gradient-to-r from-accent-900/30 via-transparent to-accent-900/20"></div>

      <div class="container-custom relative z-10">
        <div class="max-w-3xl">
          <h1 class="text-4xl md:text-5xl font-bold mb-6">
            {{ t('home.hero.title') }}
          </h1>
          <p class="text-xl md:text-2xl mb-8 text-gray-200">
            {{ t('home.hero.subtitle') }}
          </p>
          <NuxtLink :to="localePath('/products')" class="btn-accent inline-block">
            {{ t('home.hero.cta') }}
          </NuxtLink>
        </div>
      </div>
    </section>

    <!-- Features Section -->
    <section class="py-16 bg-white">
      <div class="container-custom">
        <div class="text-center mb-12">
          <h2 class="text-3xl font-bold text-gray-900 mb-3">
            {{ t('home.features.title') }}
          </h2>
          <div class="accent-divider mx-auto"></div>
        </div>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          <!-- Quality Feature -->
          <div class="text-center group">
            <div class="w-16 h-16 bg-gradient-to-br from-accent-50 to-accent-100 rounded-full flex items-center justify-center mx-auto mb-4 group-hover:shadow-lg group-hover:from-accent-100 group-hover:to-accent-200 transition-all duration-200">
              <svg class="w-8 h-8 text-accent-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
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
          <div class="text-center group">
            <div class="w-16 h-16 bg-gradient-to-br from-gray-100 to-gray-200 rounded-full flex items-center justify-center mx-auto mb-4 group-hover:shadow-lg group-hover:from-accent-50 group-hover:to-accent-100 transition-all duration-200">
              <svg class="w-8 h-8 text-gray-700 group-hover:text-accent-500 transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24">
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
          <div class="text-center group">
            <div class="w-16 h-16 bg-gradient-to-br from-gray-100 to-gray-200 rounded-full flex items-center justify-center mx-auto mb-4 group-hover:shadow-lg group-hover:from-accent-50 group-hover:to-accent-100 transition-all duration-200">
              <svg class="w-8 h-8 text-gray-700 group-hover:text-accent-500 transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24">
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
          <div class="text-center group">
            <div class="w-16 h-16 bg-gradient-to-br from-gray-100 to-gray-200 rounded-full flex items-center justify-center mx-auto mb-4 group-hover:shadow-lg group-hover:from-accent-50 group-hover:to-accent-100 transition-all duration-200">
              <svg class="w-8 h-8 text-gray-700 group-hover:text-accent-500 transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24">
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
          <NuxtLink :to="localePath('/products')" class="text-accent-500 hover:text-accent-600 font-medium transition-colors">
            {{ t('common.viewDetails') }} →
          </NuxtLink>
        </div>

        <div v-if="featuredProducts && featuredProducts.length > 0" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
          <NuxtLink
            v-for="product in featuredProducts"
            :key="product.id"
            :to="localePath(`/product/${product.slug}`)"
            class="group relative bg-white rounded-xl shadow-md hover:shadow-xl transition-all duration-300 overflow-hidden border border-gray-200 hover:border-accent-300"
          >
            <!-- Subtle red accent corner -->
            <div class="absolute top-0 right-0 w-16 h-16 bg-gradient-to-br from-accent-100 to-accent-200 opacity-0 group-hover:opacity-100 transition-opacity duration-300 rounded-bl-full"></div>

            <!-- Product Image -->
            <div class="aspect-square bg-gradient-to-br from-gray-50 to-gray-100 relative overflow-hidden">
              <img
                v-if="product.files && product.files.length > 0"
                :src="getImageUrl(product.files[0].url)"
                :alt="locale === 'zh' ? product.nameZh : product.nameEn"
                class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500"
                loading="lazy"
              />
              <!-- Subtle red accent line on hover -->
              <div class="absolute bottom-0 left-0 right-0 h-0.5 bg-gradient-to-r from-accent-400 via-accent-500 to-accent-400 transform scale-x-0 group-hover:scale-x-100 transition-transform duration-300"></div>
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
                <span class="text-xs font-medium text-accent-700 bg-accent-50 px-2 py-1 rounded border border-accent-200">
                  {{ product.partNumber }}
                </span>
              </div>

              <!-- Car Info -->
              <p class="text-sm text-gray-600 flex items-center gap-1">
                <svg class="w-4 h-4 text-gray-400 group-hover:text-accent-400 transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 17a2 2 0 11-4 0 2 2 0 014 0zM19 17a2 2 0 11-4 0 2 2 0 014 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16V6a1 1 0 00-1-1H4a1 1 0 00-1 1v10a1 1 0 001 1h1m8-1a1 1 0 01-1 1H9m4-1V8a1 1 0 011-1h2.586a1 1 0 01.707.293l3.414 3.414a1 1 0 01.293.707V16a1 1 0 01-1 1h-1m-6-1a1 1 0 001 1h1M5 17a2 2 0 104 0m-4 0a2 2 0 114 0m6 0a2 2 0 104 0m-4 0a2 2 0 114 0" />
                </svg>
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
            <h2 class="text-3xl font-bold text-gray-900 mb-3">
              {{ t('home.about.title') }}
            </h2>
            <div class="accent-divider mb-6"></div>
            <p class="text-gray-700 text-lg leading-relaxed mb-6">
              {{ t('home.about.description') }}
            </p>
            <div class="grid grid-cols-3 gap-6">
              <div class="text-center">
                <div class="text-4xl font-bold text-accent-600 mb-2">10+</div>
                <div class="text-sm text-gray-600">{{ t('home.about.experience') }}</div>
              </div>
              <div class="text-center">
                <div class="text-4xl font-bold text-accent-600 mb-2">500+</div>
                <div class="text-sm text-gray-600">{{ t('home.about.customers') }}</div>
              </div>
              <div class="text-center">
                <div class="text-4xl font-bold text-accent-600 mb-2">5000+</div>
                <div class="text-sm text-gray-600">{{ t('home.about.parts') }}</div>
              </div>
            </div>
          </div>
          <div class="flex items-center justify-center">
            <div class="relative">
              <!-- Decorative background with Chinese red accent -->
              <div class="absolute inset-0 bg-gradient-to-br from-accent-100 to-accent-200 rounded-2xl transform rotate-3"></div>

              <!-- Company Image Container -->
              <div class="relative bg-white p-3 rounded-2xl shadow-2xl border-4 border-accent-50">
                <img
                  :src="locale === 'zh' ? '/company_pic_zh.png' : '/company_pic_en.png'"
                  :alt="t('home.about.title')"
                  class="rounded-xl w-full h-auto object-cover"
                  loading="lazy"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- CTA Section -->
    <section class="py-16 bg-gradient-to-br from-gray-900 to-gray-800 text-white relative overflow-hidden">
      <!-- Subtle red accent overlay -->
      <div class="absolute inset-0 bg-gradient-to-r from-transparent via-accent-900/20 to-transparent"></div>
      <div class="container-custom text-center relative z-10">
        <h2 class="text-3xl font-bold mb-4">
          {{ locale === 'zh' ? '准备好订购了吗？' : 'Ready to Order?' }}
        </h2>
        <p class="text-xl text-gray-300 mb-8">
          {{ locale === 'zh' ? '立即联系我们获取报价和更多信息' : 'Contact us today for quotes and more information' }}
        </p>
        <div class="flex flex-col sm:flex-row gap-4 justify-center">
          <NuxtLink :to="localePath('/contact')" class="btn-accent">
            {{ t('common.contactUs') }}
          </NuxtLink>
          <a
            :href="`https://wa.me/${config.public.whatsappNumber}`"
            target="_blank"
            rel="noopener noreferrer"
            class="bg-green-600 hover:bg-green-700 text-white font-medium py-2 px-4 rounded-lg transition-colors duration-200 shadow-md hover:shadow-lg inline-flex items-center justify-center"
          >
            <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 24 24">
              <path d="M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347m-5.421 7.403h-.004a9.87 9.87 0 01-5.031-1.378l-.361-.214-3.741.982.998-3.648-.235-.374a9.86 9.86 0 01-1.51-5.26c.001-5.45 4.436-9.884 9.888-9.884 2.64 0 5.122 1.03 6.988 2.898a9.825 9.825 0 012.893 6.994c-.003 5.45-4.437 9.884-9.885 9.884m8.413-18.297A11.815 11.815 0 0012.05 0C5.495 0 .16 5.335.157 11.892c0 2.096.547 4.142 1.588 5.945L.057 24l6.305-1.654a11.882 11.882 0 005.683 1.448h.005c6.554 0 11.89-5.335 11.893-11.893a11.821 11.821 0 00-3.48-8.413z"/>
            </svg>
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
  `${config.public.apiBase}/auto-parts?page=0&size=8`,
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
