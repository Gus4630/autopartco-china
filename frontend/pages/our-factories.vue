<template>
  <div class="bg-gray-50 py-12">
    <div class="container-custom">
      <!-- Page Header -->
      <div class="text-center mb-12">
        <h1 class="text-4xl font-bold text-gray-900 mb-4">
          {{ locale === 'zh' ? '我们的合作工厂' : 'Our Manufacturing Partners' }}
        </h1>
        <p class="text-lg text-gray-600 max-w-3xl mx-auto">
          {{ locale === 'zh'
            ? '我们与中国领先的汽车零部件制造商合作，为您提供高质量的产品和服务'
            : 'We partner with leading automotive parts manufacturers in China to provide you with high-quality products and services'
          }}
        </p>
      </div>

      <!-- Loading State -->
      <div v-if="pending" class="flex items-center justify-center py-12">
        <div class="text-center">
          <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-accent-600 mx-auto mb-4"></div>
          <p class="text-gray-600">{{ locale === 'zh' ? '加载中...' : 'Loading factories...' }}</p>
        </div>
      </div>

      <!-- Error State -->
      <div v-else-if="error" class="bg-red-50 border border-red-200 rounded-lg p-6 text-center">
        <p class="text-red-800">{{ locale === 'zh' ? '加载工厂信息失败' : 'Failed to load factories' }}</p>
      </div>

      <!-- Factories Grid -->
      <div v-else-if="factories && factories.length > 0" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <div
          v-for="factory in factories"
          :key="factory.id"
          class="bg-white rounded-lg shadow-md hover:shadow-xl transition-shadow duration-300 overflow-hidden flex flex-col"
        >
          <div class="p-6 flex-grow">
            <!-- Factory Name -->
            <h2 class="text-xl font-bold text-gray-900 mb-3">
              {{ locale === 'zh'
                ? (factory.factoryNameZh || factory.factoryNameEn)
                : (factory.factoryNameEn || factory.factoryNameZh)
              }}
            </h2>

            <!-- Factory Details -->
            <div class="space-y-3">
              <!-- Product Type/Specialization -->
              <div v-if="factory.factoryPartEn || factory.factoryPartZh" class="flex items-start">
                <svg class="w-5 h-5 text-accent-600 mt-0.5 mr-2 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
                </svg>
                <div>
                  <p class="text-sm text-gray-500">{{ locale === 'zh' ? '产品类型' : 'Specialization' }}</p>
                  <p class="text-gray-900">
                    {{ locale === 'zh'
                      ? (factory.factoryPartZh || factory.factoryPartEn)
                      : (factory.factoryPartEn || factory.factoryPartZh)
                    }}
                  </p>
                </div>
              </div>

              <!-- Phone Number -->
              <div v-if="factory.factoryPhoneNumber" class="flex items-start">
                <svg class="w-5 h-5 text-accent-600 mt-0.5 mr-2 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                </svg>
                <div>
                  <p class="text-sm text-gray-500">{{ locale === 'zh' ? '电话' : 'Phone' }}</p>
                  <a :href="`tel:${factory.factoryPhoneNumber}`" class="text-gray-900 hover:text-accent-600">
                    {{ factory.factoryPhoneNumber }}
                  </a>
                </div>
              </div>

              <!-- Website -->
              <div v-if="factory.factorySiteUrl" class="flex items-start">
                <svg class="w-5 h-5 text-accent-600 mt-0.5 mr-2 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 12a9 9 0 01-9 9m9-9a9 9 0 00-9-9m9 9H3m9 9a9 9 0 01-9-9m9 9c1.657 0 3-4.03 3-9s-1.343-9-3-9m0 18c-1.657 0-3-4.03-3-9s1.343-9 3-9m-9 9a9 9 0 019-9" />
                </svg>
                <div>
                  <p class="text-sm text-gray-500">{{ locale === 'zh' ? '网站' : 'Website' }}</p>
                  <a
                    :href="ensureProtocol(factory.factorySiteUrl)"
                    target="_blank"
                    rel="noopener noreferrer"
                    class="text-accent-600 hover:underline inline-flex items-center"
                  >
                    {{ locale === 'zh' ? '访问网站' : 'Visit Website' }}
                    <svg class="w-4 h-4 ml-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14" />
                    </svg>
                  </a>
                </div>
              </div>
            </div>
          </div>

          <!-- Footer Badge -->
          <div class="bg-gray-50 px-6 py-3 border-t border-gray-100">
            <div class="flex items-center text-sm text-gray-600">
              <svg class="w-4 h-4 text-green-600 mr-1" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
              </svg>
              {{ locale === 'zh' ? '认证合作伙伴' : 'Verified Partner' }}
            </div>
          </div>
        </div>
      </div>

      <!-- Empty State -->
      <div v-else class="text-center py-12">
        <svg class="w-16 h-16 text-gray-300 mx-auto mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
        </svg>
        <p class="text-gray-600">{{ locale === 'zh' ? '暂无工厂信息' : 'No factories available' }}</p>
      </div>

      <!-- CTA Section -->
      <div class="mt-16 bg-gradient-to-br from-gray-900 to-gray-800 rounded-lg shadow-xl overflow-hidden relative">
        <div class="absolute inset-0 bg-gradient-to-r from-transparent via-accent-900/20 to-transparent rounded-lg"></div>
        <div class="px-6 py-12 sm:px-12 text-center relative z-10">
          <h2 class="text-3xl font-bold text-white mb-4">
            {{ locale === 'zh' ? '需要帮助找到合适的零件？' : 'Need Help Finding the Right Parts?' }}
          </h2>
          <p class="text-gray-300 text-lg mb-6 max-w-2xl mx-auto">
            {{ locale === 'zh'
              ? '我们的专家团队随时为您提供帮助，找到最适合您需求的汽车零部件'
              : 'Our expert team is ready to help you find the perfect automotive parts for your needs'
            }}
          </p>
          <NuxtLink :to="localePath('/contact')" class="btn-secondary inline-block">
            {{ locale === 'zh' ? '联系我们' : 'Contact Us' }}
          </NuxtLink>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { locale } = useI18n()
const localePath = useLocalePath()
const config = useRuntimeConfig()

// Ensure URL has protocol
const ensureProtocol = (url) => {
  if (!url) return ''
  const trimmedUrl = url.trim()
  if (trimmedUrl.startsWith('http://') || trimmedUrl.startsWith('https://')) {
    return trimmedUrl
  }
  return `http://${trimmedUrl}`
}

// Fetch factories data
const { data: factories, pending, error } = await useFetch(
  `${config.public.apiBase}/factories`,
  { key: 'factories' }
)

// SEO Meta Tags
useSeoMeta({
  title: locale.value === 'zh' ? '我们的合作工厂 - AutoPartCo China' : 'Our Manufacturing Partners - AutoPartCo China',
  description: locale.value === 'zh'
    ? '了解我们在中国的合作制造工厂，提供高质量的汽车零部件'
    : 'Discover our manufacturing partners in China providing high-quality automotive parts',
  ogTitle: locale.value === 'zh' ? '我们的合作工厂 - AutoPartCo China' : 'Our Manufacturing Partners - AutoPartCo China',
  ogDescription: locale.value === 'zh'
    ? '了解我们在中国的合作制造工厂，提供高质量的汽车零部件'
    : 'Discover our manufacturing partners in China providing high-quality automotive parts',
  ogType: 'website'
})
</script>
