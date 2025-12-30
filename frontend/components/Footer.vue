<template>
  <footer class="bg-gray-900 text-gray-300">
    <div class="container-custom py-12">
      <div class="grid grid-cols-1 md:grid-cols-4 gap-8">
        <!-- Company Information -->
        <div>
          <h3 class="text-white text-lg font-semibold mb-4">
            {{ t('footer.company') }}
          </h3>
          <div class="text-xl font-bold text-primary-400 mb-2">
            AutoPartCo
          </div>
          <p class="text-sm mb-4">
            {{ t('common.company') }}
          </p>
          <p class="text-sm text-gray-400">
            {{ t('header.tagline') }}
          </p>
        </div>

        <!-- Products Links -->
        <div>
          <h3 class="text-white text-lg font-semibold mb-4">
            {{ t('footer.products') }}
          </h3>
          <ul class="space-y-2">
            <li>
              <NuxtLink
                :to="localePath('/products')"
                class="text-sm hover:text-primary-400 transition-colors"
              >
                {{ t('footer.allProducts') }}
              </NuxtLink>
            </li>
            <li>
              <NuxtLink
                :to="localePath('/categories')"
                class="text-sm hover:text-primary-400 transition-colors"
              >
                {{ t('footer.categories') }}
              </NuxtLink>
            </li>
          </ul>
        </div>

        <!-- Support Links -->
        <div>
          <h3 class="text-white text-lg font-semibold mb-4">
            {{ t('footer.support') }}
          </h3>
          <ul class="space-y-2">
            <li>
              <NuxtLink
                :to="localePath('/faq')"
                class="text-sm hover:text-primary-400 transition-colors"
              >
                FAQ
              </NuxtLink>
            </li>
            <li>
              <NuxtLink
                :to="localePath('/quality-guide')"
                class="text-sm hover:text-primary-400 transition-colors"
              >
                {{ locale === 'zh' ? '质量指南' : 'Quality Guide' }}
              </NuxtLink>
            </li>
            <li>
              <NuxtLink
                :to="localePath('/shipping')"
                class="text-sm hover:text-primary-400 transition-colors"
              >
                {{ t('footer.shipping') }}
              </NuxtLink>
            </li>
            <li>
              <NuxtLink
                :to="localePath('/about')"
                class="text-sm hover:text-primary-400 transition-colors"
              >
                {{ t('footer.aboutUs') }}
              </NuxtLink>
            </li>
            <li>
              <NuxtLink
                :to="localePath('/contact')"
                class="text-sm hover:text-primary-400 transition-colors"
              >
                {{ t('footer.contactUs') }}
              </NuxtLink>
            </li>
          </ul>
        </div>

        <!-- Contact Information -->
        <div>
          <h3 class="text-white text-lg font-semibold mb-4">
            {{ t('footer.contact') }}
          </h3>
          <ul class="space-y-2 text-sm">
            <li class="flex items-start">
              <span class="font-medium mr-2">{{ t('footer.phone') }}:</span>
              <a :href="`tel:${config.public.companyPhone}`" class="hover:text-primary-400">
                {{ config.public.companyPhone }}
              </a>
            </li>
            <li class="flex items-start">
              <span class="font-medium mr-2">{{ t('footer.landline') }}:</span>
              <a :href="`tel:${config.public.companyLandline}`" class="hover:text-primary-400">
                {{ config.public.companyLandline }}
              </a>
            </li>
            <li class="flex items-start">
              <span class="font-medium mr-2">{{ t('footer.email') }}:</span>
              <a :href="`mailto:${config.public.companyEmail}`" class="hover:text-primary-400">
                {{ config.public.companyEmail }}
              </a>
            </li>
            <li class="flex items-start">
              <span class="font-medium mr-2">{{ t('footer.whatsapp') }}:</span>
              <a
                :href="`https://wa.me/${config.public.whatsappNumber}`"
                target="_blank"
                rel="noopener noreferrer"
                class="hover:text-primary-400"
              >
                +{{ config.public.whatsappNumber }}
              </a>
            </li>
            <li class="flex items-start">
              <span class="font-medium mr-2">{{ t('footer.wechat') }}:</span>
              <span>{{ config.public.wechatId }}</span>
            </li>
          </ul>
        </div>
      </div>

      <!-- Bottom Bar -->
      <div class="border-t border-gray-800 mt-8 pt-8 text-center text-sm">
        <p>
          {{ t('footer.copyright', { year: currentYear }) }}
        </p>
      </div>
    </div>
  </footer>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { t, locale } = useI18n()
const localePath = useLocalePath()
const config = useRuntimeConfig()

const currentYear = new Date().getFullYear()
</script>
