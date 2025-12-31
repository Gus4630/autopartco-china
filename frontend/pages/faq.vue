<template>
  <div class="bg-gray-50">
    <!-- Hero Section -->
    <section class="bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 text-white py-16 relative overflow-hidden">
      <!-- Subtle red accent overlay -->
      <div class="absolute inset-0 bg-gradient-to-r from-accent-900/30 via-transparent to-accent-900/20"></div>
      <div class="container-custom relative z-10">
        <h1 class="text-4xl md:text-5xl font-bold mb-4">
          {{ t('faq.title') }}
        </h1>
        <p class="text-xl text-gray-200 mb-8">
          {{ t('faq.subtitle') }}
        </p>

        <!-- Search Bar -->
        <div class="max-w-2xl">
          <div class="relative">
            <input
              v-model="searchQuery"
              type="text"
              :placeholder="t('faq.search')"
              class="w-full px-6 py-4 rounded-lg text-gray-900 placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-accent-500"
            />
            <svg class="absolute right-4 top-4 w-6 h-6 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
            </svg>
          </div>
        </div>
      </div>
    </section>

    <!-- FAQ Categories -->
    <section class="py-16 bg-white">
      <div class="container-custom max-w-5xl">
        <!-- Products Category -->
        <FaqCategory :title="t('faq.categories.products')">
          <template #icon>
            <svg class="w-6 h-6 text-accent-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4" />
            </svg>
          </template>
          <FaqAccordion
            v-for="(item, key) in filteredFaqItems.products"
            :key="`products-${key}`"
            :id="`products-${key}`"
            :question="item.question"
            :answer="item.answer"
          />
        </FaqCategory>

        <!-- Ordering Category -->
        <FaqCategory :title="t('faq.categories.ordering')">
          <template #icon>
            <svg class="w-6 h-6 text-accent-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01" />
            </svg>
          </template>
          <FaqAccordion
            v-for="(item, key) in filteredFaqItems.ordering"
            :key="`ordering-${key}`"
            :id="`ordering-${key}`"
            :question="item.question"
            :answer="item.answer"
          />
        </FaqCategory>

        <!-- Shipping Category -->
        <FaqCategory :title="t('faq.categories.shipping')">
          <template #icon>
            <svg class="w-6 h-6 text-accent-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path d="M9 17a2 2 0 11-4 0 2 2 0 014 0zM19 17a2 2 0 11-4 0 2 2 0 014 0z" />
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16V6a1 1 0 00-1-1H4a1 1 0 00-1 1v10a1 1 0 001 1h1m8-1a1 1 0 01-1 1H9m4-1V8a1 1 0 011-1h2.586a1 1 0 01.707.293l3.414 3.414a1 1 0 01.293.707V16a1 1 0 01-1 1h-1m-6-1a1 1 0 001 1h1M5 17a2 2 0 104 0m-4 0a2 2 0 114 0m6 0a2 2 0 104 0m-4 0a2 2 0 114 0" />
            </svg>
          </template>
          <FaqAccordion
            v-for="(item, key) in filteredFaqItems.shipping"
            :key="`shipping-${key}`"
            :id="`shipping-${key}`"
            :question="item.question"
            :answer="item.answer"
          />
        </FaqCategory>

        <!-- Payment Category -->
        <FaqCategory :title="t('faq.categories.payment')">
          <template #icon>
            <svg class="w-6 h-6 text-accent-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 10h18M7 15h1m4 0h1m-7 4h12a3 3 0 003-3V8a3 3 0 00-3-3H6a3 3 0 00-3 3v8a3 3 0 003 3z" />
            </svg>
          </template>
          <FaqAccordion
            v-for="(item, key) in filteredFaqItems.payment"
            :key="`payment-${key}`"
            :id="`payment-${key}`"
            :question="item.question"
            :answer="item.answer"
          />
        </FaqCategory>

        <!-- Technical Category -->
        <FaqCategory :title="t('faq.categories.technical')">
          <template #icon>
            <svg class="w-6 h-6 text-accent-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z" />
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
            </svg>
          </template>
          <FaqAccordion
            v-for="(item, key) in filteredFaqItems.technical"
            :key="`technical-${key}`"
            :id="`technical-${key}`"
            :question="item.question"
            :answer="item.answer"
          />
        </FaqCategory>

        <!-- No Results Message -->
        <div v-if="hasSearchQuery && !hasResults" class="text-center py-12">
          <svg class="w-16 h-16 text-gray-400 mx-auto mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
          <p class="text-gray-600 text-lg">{{ t('faq.noResults') }}</p>
        </div>
      </div>
    </section>

    <!-- CTA Section -->
    <section class="py-16 bg-gradient-to-br from-gray-900 to-gray-800 text-white relative overflow-hidden">
      <!-- Subtle red accent overlay -->
      <div class="absolute inset-0 bg-gradient-to-r from-transparent via-accent-900/20 to-transparent"></div>
      <div class="container-custom text-center relative z-10">
        <h2 class="text-3xl font-bold mb-4">
          {{ t('faq.cta.title') }}
        </h2>
        <p class="text-xl text-gray-300 mb-8 max-w-2xl mx-auto">
          {{ t('faq.cta.description') }}
        </p>
        <NuxtLink :to="localePath('/contact')" class="btn-accent inline-block">
          {{ t('faq.cta.button') }}
        </NuxtLink>
      </div>
    </section>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'
import FaqCategory from '~/components/faq/FaqCategory.vue'
import FaqAccordion from '~/components/faq/FaqAccordion.vue'

const { t, tm } = useI18n()
const localePath = useLocalePath()

// Search functionality
const searchQuery = ref('')

// Get all FAQ items from translations
const getFaqCategory = (category) => {
  const items = {}
  for (let i = 1; i <= 5; i++) {
    const key = `q${i}`
    items[key] = {
      question: t(`faq.${category}.${key}.question`),
      answer: t(`faq.${category}.${key}.answer`)
    }
  }
  return items
}

const faqItems = computed(() => ({
  products: getFaqCategory('products'),
  ordering: getFaqCategory('ordering'),
  shipping: getFaqCategory('shipping'),
  payment: getFaqCategory('payment'),
  technical: getFaqCategory('technical')
}))

// Filter FAQ items based on search query
const filteredFaqItems = computed(() => {
  if (!searchQuery.value) return faqItems.value

  const query = searchQuery.value.toLowerCase()
  const filtered = {}

  Object.keys(faqItems.value).forEach(category => {
    const categoryItems = faqItems.value[category]
    const matchingItems = {}

    Object.keys(categoryItems).forEach(key => {
      const item = categoryItems[key]
      if (
        item.question?.toLowerCase().includes(query) ||
        item.answer?.toLowerCase().includes(query)
      ) {
        matchingItems[key] = item
      }
    })

    filtered[category] = matchingItems
  })

  return filtered
})

// Check if there's a search query
const hasSearchQuery = computed(() => searchQuery.value.trim().length > 0)

// Check if there are any results
const hasResults = computed(() => {
  return Object.values(filteredFaqItems.value).some(category => Object.keys(category).length > 0)
})

// SEO
useSeoMeta({
  title: t('meta.faq.title'),
  description: t('meta.faq.description'),
  ogTitle: t('meta.faq.title'),
  ogDescription: t('meta.faq.description'),
  ogType: 'website'
})
</script>
