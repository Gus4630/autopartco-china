<template>
  <div class="bg-gray-50 py-12">
    <div class="container-custom">
      <div class="max-w-4xl mx-auto">
        <h1 class="text-4xl font-bold text-gray-900 mb-8 text-center">
          {{ t('common.about') }}
        </h1>

        <div class="bg-white rounded-lg shadow-md p-8 mb-8">
          <h2 class="text-2xl font-bold text-gray-900 mb-3">
            {{ t('common.company') }}
          </h2>
          <div class="accent-divider mb-4"></div>

          <!-- Company Image - Language Specific -->
          <div class="mb-6 flex justify-center">
            <div class="relative max-w-2xl w-full">
              <!-- Decorative background with Chinese red accent -->
              <div class="absolute inset-0 bg-gradient-to-br from-accent-100 to-accent-200 rounded-2xl transform rotate-2"></div>

              <!-- Company Image Container -->
              <div class="relative bg-white p-3 rounded-2xl shadow-2xl border-4 border-accent-50">
                <img
                  :src="locale === 'zh' ? '/company_pic_zh.png' : '/company_pic_en.png'"
                  :alt="t('common.company')"
                  class="rounded-xl w-full h-auto object-cover"
                  loading="lazy"
                />
              </div>
            </div>
          </div>

          <p class="text-gray-700 leading-relaxed mb-6">
            {{ t('home.about.description') }}
          </p>

          <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
            <div class="text-center p-6 bg-gradient-to-br from-accent-50 to-accent-100 rounded-lg border border-accent-200">
              <div class="text-4xl font-bold text-accent-600 mb-2">10+</div>
              <div class="text-gray-700">{{ t('home.about.experience') }}</div>
            </div>
            <div class="text-center p-6 bg-gradient-to-br from-accent-50 to-accent-100 rounded-lg border border-accent-200">
              <div class="text-4xl font-bold text-accent-600 mb-2">500+</div>
              <div class="text-gray-700">{{ t('home.about.customers') }}</div>
            </div>
            <div class="text-center p-6 bg-gradient-to-br from-accent-50 to-accent-100 rounded-lg border border-accent-200">
              <div class="text-4xl font-bold text-accent-600 mb-2">5000+</div>
              <div class="text-gray-700">{{ t('home.about.parts') }}</div>
            </div>
          </div>

          <h3 class="text-xl font-bold text-gray-900 mb-3">
            {{ locale === 'zh' ? '我们的专长' : 'Our Expertise' }}
          </h3>
          <div class="accent-divider mb-4"></div>
          <ul class="space-y-3 mb-6">
            <li class="flex items-start">
              <svg class="w-6 h-6 text-accent-600 mr-3 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
              </svg>
              <span class="text-gray-700">
                {{ locale === 'zh' ? '全系列汽车内部零部件' : 'Complete range of automotive internal parts' }}
              </span>
            </li>
            <li class="flex items-start">
              <svg class="w-6 h-6 text-accent-600 mr-3 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
              </svg>
              <span class="text-gray-700">
                {{ locale === 'zh' ? '发动机、传动系统和底盘部件' : 'Engine, transmission and chassis components' }}
              </span>
            </li>
            <li class="flex items-start">
              <svg class="w-6 h-6 text-accent-600 mr-3 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
              </svg>
              <span class="text-gray-700">
                {{ locale === 'zh' ? '原厂配件和高质量副厂配件' : 'Genuine OEM and high-quality aftermarket parts' }}
              </span>
            </li>
            <li class="flex items-start">
              <svg class="w-6 h-6 text-accent-600 mr-3 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
              </svg>
              <span class="text-gray-700">
                {{ locale === 'zh' ? '快速报价和专业技术支持' : 'Fast quotations and professional technical support' }}
              </span>
            </li>
            <li class="flex items-start">
              <svg class="w-6 h-6 text-accent-600 mr-3 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
              </svg>
              <span class="text-gray-700">
                {{ locale === 'zh' ? '可靠的国际物流和包装服务' : 'Reliable international logistics and packaging services' }}
              </span>
            </li>
          </ul>

          <h3 class="text-xl font-bold text-gray-900 mb-3">
            {{ locale === 'zh' ? '为什么选择我们' : 'Why Choose Us' }}
          </h3>
          <div class="accent-divider mb-4"></div>
          <p class="text-gray-700 leading-relaxed">
            {{ locale === 'zh'
              ? '我们位于中国山东省，靠近主要制造商，这使我们能够以具有竞争力的价格提供正品配件。我们的团队拥有丰富的重型卡车零部件经验，可以帮助您找到适合您需求的合适零件。我们为全球客户提供专业的双语服务，确保清晰的沟通和高效的交易。'
              : 'Located in Shandong Province, China, we are close to major manufacturers, enabling us to offer genuine parts at competitive prices. Our team has extensive experience with heavy truck parts and can help you find the right parts for your needs. We provide professional bilingual service to customers worldwide, ensuring clear communication and efficient transactions.'
            }}
          </p>
        </div>

        <div class="text-center">
          <NuxtLink :to="localePath('/contact')" class="btn-primary inline-block">
            {{ t('common.contactUs') }}
          </NuxtLink>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { t, locale } = useI18n()
const localePath = useLocalePath()

// SEO
useSeoMeta({
  title: t('meta.about.title'),
  description: t('meta.about.description')
})
</script>
