<template>
  <header class="bg-white shadow-md sticky top-0 z-50">
    <div class="container-custom">
      <div class="flex items-center justify-between h-16">
        <!-- Logo and Company Name -->
        <NuxtLink :to="localePath('/')" class="flex items-center space-x-3">
          <div class="text-2xl font-bold text-primary-600">
            AutoPartCo
          </div>
          <div class="hidden md:block text-sm text-gray-600">
            {{ t('header.tagline') }}
          </div>
        </NuxtLink>

        <!-- Desktop Navigation -->
        <nav class="hidden md:flex items-center space-x-6">
          <NuxtLink
            :to="localePath('/')"
            class="text-gray-700 hover:text-primary-600 transition-colors"
          >
            {{ t('common.home') }}
          </NuxtLink>
          <NuxtLink
            :to="localePath('/products')"
            class="text-gray-700 hover:text-primary-600 transition-colors"
          >
            {{ t('common.products') }}
          </NuxtLink>
          <NuxtLink
            :to="localePath('/categories')"
            class="text-gray-700 hover:text-primary-600 transition-colors"
          >
            {{ t('common.categories') }}
          </NuxtLink>
          <NuxtLink
            :to="localePath('/brands')"
            class="text-gray-700 hover:text-primary-600 transition-colors"
          >
            {{ t('common.brands') }}
          </NuxtLink>
          <NuxtLink
            :to="localePath('/about')"
            class="text-gray-700 hover:text-primary-600 transition-colors"
          >
            {{ t('common.about') }}
          </NuxtLink>
          <NuxtLink
            :to="localePath('/contact')"
            class="text-gray-700 hover:text-primary-600 transition-colors"
          >
            {{ t('common.contact') }}
          </NuxtLink>
        </nav>

        <!-- Language Switcher and Contact -->
        <div class="flex items-center space-x-4">
          <!-- Language Switcher -->
          <div class="relative">
            <button
              @click="toggleLanguageMenu"
              class="flex items-center space-x-1 text-gray-700 hover:text-primary-600 transition-colors"
            >
              <span>{{ currentLocale === 'en' ? 'EN' : '中文' }}</span>
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </button>
            <div
              v-if="languageMenuOpen"
              class="absolute right-0 mt-2 w-32 bg-white rounded-md shadow-lg py-1 border border-gray-200"
            >
              <button
                v-for="locale in availableLocales"
                :key="locale.code"
                @click="switchLanguage(locale.code)"
                class="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
              >
                {{ locale.name }}
              </button>
            </div>
          </div>

          <!-- Contact Button -->
          <NuxtLink
            :to="localePath('/contact')"
            class="btn-primary hidden md:inline-block"
          >
            {{ t('common.contactUs') }}
          </NuxtLink>

          <!-- Mobile Menu Button -->
          <button
            @click="toggleMobileMenu"
            class="md:hidden text-gray-700"
          >
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path
                v-if="!mobileMenuOpen"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M4 6h16M4 12h16M4 18h16"
              />
              <path
                v-else
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M6 18L18 6M6 6l12 12"
              />
            </svg>
          </button>
        </div>
      </div>

      <!-- Mobile Navigation -->
      <div
        v-if="mobileMenuOpen"
        class="md:hidden border-t border-gray-200 py-4"
      >
        <nav class="flex flex-col space-y-3">
          <NuxtLink
            :to="localePath('/')"
            @click="closeMobileMenu"
            class="text-gray-700 hover:text-primary-600 transition-colors px-2"
          >
            {{ t('common.home') }}
          </NuxtLink>
          <NuxtLink
            :to="localePath('/products')"
            @click="closeMobileMenu"
            class="text-gray-700 hover:text-primary-600 transition-colors px-2"
          >
            {{ t('common.products') }}
          </NuxtLink>
          <NuxtLink
            :to="localePath('/categories')"
            @click="closeMobileMenu"
            class="text-gray-700 hover:text-primary-600 transition-colors px-2"
          >
            {{ t('common.categories') }}
          </NuxtLink>
          <NuxtLink
            :to="localePath('/brands')"
            @click="closeMobileMenu"
            class="text-gray-700 hover:text-primary-600 transition-colors px-2"
          >
            {{ t('common.brands') }}
          </NuxtLink>
          <NuxtLink
            :to="localePath('/about')"
            @click="closeMobileMenu"
            class="text-gray-700 hover:text-primary-600 transition-colors px-2"
          >
            {{ t('common.about') }}
          </NuxtLink>
          <NuxtLink
            :to="localePath('/contact')"
            @click="closeMobileMenu"
            class="text-gray-700 hover:text-primary-600 transition-colors px-2"
          >
            {{ t('common.contact') }}
          </NuxtLink>
        </nav>
      </div>
    </div>
  </header>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { t, locale: currentLocale, locales, setLocale } = useI18n()
const localePath = useLocalePath()
const switchLocalePath = useSwitchLocalePath()

const languageMenuOpen = ref(false)
const mobileMenuOpen = ref(false)

const availableLocales = computed(() => {
  return locales.value.filter(loc => loc.code !== currentLocale.value)
})

const toggleLanguageMenu = () => {
  languageMenuOpen.value = !languageMenuOpen.value
}

const toggleMobileMenu = () => {
  mobileMenuOpen.value = !mobileMenuOpen.value
}

const closeMobileMenu = () => {
  mobileMenuOpen.value = false
}

const switchLanguage = async (localeCode) => {
  await setLocale(localeCode)
  languageMenuOpen.value = false
}

// Close menus when clicking outside
onMounted(() => {
  document.addEventListener('click', (e) => {
    if (!e.target.closest('.relative')) {
      languageMenuOpen.value = false
    }
  })
})
</script>
