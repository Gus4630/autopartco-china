<template>
  <div class="bg-gray-50">
    <!-- Hero Section -->
    <section class="bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 text-white py-16 relative overflow-hidden">
      <!-- Subtle red accent overlay -->
      <div class="absolute inset-0 bg-gradient-to-r from-accent-900/30 via-transparent to-accent-900/20"></div>
      <div class="container-custom relative z-10">
        <h1 class="text-4xl md:text-5xl font-bold mb-4">
          {{ t('shipping.title') }}
        </h1>
        <p class="text-xl text-gray-200">
          {{ t('shipping.subtitle') }}
        </p>
      </div>
    </section>

    <!-- Shipping Methods -->
    <section class="py-16 bg-white">
      <div class="container-custom">
        <h2 class="text-3xl font-bold text-gray-900 mb-8 text-center">
          {{ t('shipping.methods.title') }}
        </h2>
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
          <!-- Sea Freight -->
          <div class="card p-6">
            <div class="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mx-auto mb-4">
              <svg class="w-8 h-8 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4" />
              </svg>
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-3 text-center">
              {{ t('shipping.methods.sea.title') }}
            </h3>
            <p class="text-gray-700 mb-4">
              {{ t('shipping.methods.sea.description') }}
            </p>
            <p class="text-sm text-accent-600 font-medium">
              {{ t('shipping.methods.sea.bestFor') }}
            </p>
          </div>

          <!-- Air Freight -->
          <div class="card p-6">
            <div class="w-16 h-16 bg-sky-100 rounded-full flex items-center justify-center mx-auto mb-4">
              <svg class="w-8 h-8 text-sky-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8" />
              </svg>
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-3 text-center">
              {{ t('shipping.methods.air.title') }}
            </h3>
            <p class="text-gray-700 mb-4">
              {{ t('shipping.methods.air.description') }}
            </p>
            <p class="text-sm text-accent-600 font-medium">
              {{ t('shipping.methods.air.bestFor') }}
            </p>
          </div>

          <!-- Express Courier -->
          <div class="card p-6">
            <div class="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
              <svg class="w-8 h-8 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
              </svg>
            </div>
            <h3 class="text-xl font-semibold text-gray-900 mb-3 text-center">
              {{ t('shipping.methods.express.title') }}
            </h3>
            <p class="text-gray-700 mb-4">
              {{ t('shipping.methods.express.description') }}
            </p>
            <p class="text-sm text-accent-600 font-medium">
              {{ t('shipping.methods.express.bestFor') }}
            </p>
          </div>
        </div>
      </div>
    </section>

    <!-- Shipping Process -->
    <section class="py-16 bg-gray-50">
      <div class="container-custom">
        <h2 class="text-3xl font-bold text-gray-900 mb-12 text-center">
          {{ t('shipping.process.title') }}
        </h2>
        <div class="max-w-4xl mx-auto">
          <div class="space-y-8">
            <!-- Step 1 -->
            <div class="flex gap-6">
              <div class="flex-shrink-0">
                <div class="w-12 h-12 bg-accent-600 text-white rounded-full flex items-center justify-center font-bold text-lg">
                  1
                </div>
              </div>
              <div class="flex-1">
                <h3 class="text-xl font-semibold text-gray-900 mb-2">
                  {{ t('shipping.process.step1.title') }}
                </h3>
                <p class="text-gray-700">
                  {{ t('shipping.process.step1.description') }}
                </p>
              </div>
            </div>

            <!-- Step 2 -->
            <div class="flex gap-6">
              <div class="flex-shrink-0">
                <div class="w-12 h-12 bg-accent-600 text-white rounded-full flex items-center justify-center font-bold text-lg">
                  2
                </div>
              </div>
              <div class="flex-1">
                <h3 class="text-xl font-semibold text-gray-900 mb-2">
                  {{ t('shipping.process.step2.title') }}
                </h3>
                <p class="text-gray-700">
                  {{ t('shipping.process.step2.description') }}
                </p>
              </div>
            </div>

            <!-- Step 3 -->
            <div class="flex gap-6">
              <div class="flex-shrink-0">
                <div class="w-12 h-12 bg-accent-600 text-white rounded-full flex items-center justify-center font-bold text-lg">
                  3
                </div>
              </div>
              <div class="flex-1">
                <h3 class="text-xl font-semibold text-gray-900 mb-2">
                  {{ t('shipping.process.step3.title') }}
                </h3>
                <p class="text-gray-700">
                  {{ t('shipping.process.step3.description') }}
                </p>
              </div>
            </div>

            <!-- Step 4 -->
            <div class="flex gap-6">
              <div class="flex-shrink-0">
                <div class="w-12 h-12 bg-accent-600 text-white rounded-full flex items-center justify-center font-bold text-lg">
                  4
                </div>
              </div>
              <div class="flex-1">
                <h3 class="text-xl font-semibold text-gray-900 mb-2">
                  {{ t('shipping.process.step4.title') }}
                </h3>
                <p class="text-gray-700">
                  {{ t('shipping.process.step4.description') }}
                </p>
              </div>
            </div>

            <!-- Step 5 -->
            <div class="flex gap-6">
              <div class="flex-shrink-0">
                <div class="w-12 h-12 bg-accent-600 text-white rounded-full flex items-center justify-center font-bold text-lg">
                  5
                </div>
              </div>
              <div class="flex-1">
                <h3 class="text-xl font-semibold text-gray-900 mb-2">
                  {{ t('shipping.process.step5.title') }}
                </h3>
                <p class="text-gray-700">
                  {{ t('shipping.process.step5.description') }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Additional Information -->
    <section class="py-16 bg-white">
      <div class="container-custom">
        <h2 class="text-3xl font-bold text-gray-900 mb-8 text-center">
          {{ t('shipping.info.title') }}
        </h2>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-8 max-w-5xl mx-auto">
          <!-- Insurance -->
          <div class="bg-gray-50 rounded-lg p-6">
            <div class="flex items-start gap-4">
              <div class="flex-shrink-0">
                <svg class="w-8 h-8 text-accent-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
                </svg>
              </div>
              <div>
                <h3 class="text-lg font-semibold text-gray-900 mb-2">
                  {{ t('shipping.info.insurance.title') }}
                </h3>
                <p class="text-gray-700">
                  {{ t('shipping.info.insurance.description') }}
                </p>
              </div>
            </div>
          </div>

          <!-- Tracking -->
          <div class="bg-gray-50 rounded-lg p-6">
            <div class="flex items-start gap-4">
              <div class="flex-shrink-0">
                <svg class="w-8 h-8 text-accent-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                </svg>
              </div>
              <div>
                <h3 class="text-lg font-semibold text-gray-900 mb-2">
                  {{ t('shipping.info.tracking.title') }}
                </h3>
                <p class="text-gray-700">
                  {{ t('shipping.info.tracking.description') }}
                </p>
              </div>
            </div>
          </div>

          <!-- Customs -->
          <div class="bg-gray-50 rounded-lg p-6">
            <div class="flex items-start gap-4">
              <div class="flex-shrink-0">
                <svg class="w-8 h-8 text-accent-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
                </svg>
              </div>
              <div>
                <h3 class="text-lg font-semibold text-gray-900 mb-2">
                  {{ t('shipping.info.customs.title') }}
                </h3>
                <p class="text-gray-700">
                  {{ t('shipping.info.customs.description') }}
                </p>
              </div>
            </div>
          </div>

          <!-- Packaging -->
          <div class="bg-gray-50 rounded-lg p-6">
            <div class="flex items-start gap-4">
              <div class="flex-shrink-0">
                <svg class="w-8 h-8 text-accent-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4" />
                </svg>
              </div>
              <div>
                <h3 class="text-lg font-semibold text-gray-900 mb-2">
                  {{ t('shipping.info.packaging.title') }}
                </h3>
                <p class="text-gray-700">
                  {{ t('shipping.info.packaging.description') }}
                </p>
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
          {{ t('shipping.cta.title') }}
        </h2>
        <p class="text-xl text-gray-300 mb-8 max-w-2xl mx-auto">
          {{ t('shipping.cta.description') }}
        </p>
        <NuxtLink :to="localePath('/contact')" class="btn-accent inline-block">
          {{ t('shipping.cta.button') }}
        </NuxtLink>
      </div>
    </section>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
const localePath = useLocalePath()

// SEO Meta Tags
useSeoMeta({
  title: t('shipping.title'),
  description: t('shipping.subtitle'),
  ogTitle: `${t('shipping.title')} - AutoPartCo China`,
  ogDescription: t('shipping.subtitle'),
  ogType: 'website'
})
</script>
