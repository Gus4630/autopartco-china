<template>
  <div class="bg-gray-50">
    <!-- Hero Section -->
    <section class="bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 text-white py-16 relative overflow-hidden">
      <!-- Subtle red accent overlay -->
      <div class="absolute inset-0 bg-gradient-to-r from-accent-900/30 via-transparent to-accent-900/20"></div>
      <div class="container-custom relative z-10">
        <h1 class="text-4xl md:text-5xl font-bold mb-4">
          {{ t('qualityGuide.title') }}
        </h1>
        <p class="text-xl text-gray-200 mb-6">
          {{ t('qualityGuide.subtitle') }}
        </p>
        <p class="text-lg text-gray-300 max-w-3xl">
          {{ t('qualityGuide.intro') }}
        </p>
      </div>
    </section>

    <!-- Comparison Table -->
    <section class="py-16 bg-white">
      <div class="container-custom">
        <h2 class="text-3xl font-bold text-gray-900 mb-8 text-center">
          {{ t('qualityGuide.comparisonTitle') }}
        </h2>
        <GradeComparison
          :grades="comparisonData"
          :headers="{
            grade: t('qualityGuide.comparison.headers.grade'),
            quality: t('qualityGuide.comparison.headers.quality'),
            price: t('qualityGuide.comparison.headers.price'),
            warranty: t('qualityGuide.comparison.headers.warranty'),
            bestFor: t('qualityGuide.comparison.headers.bestFor')
          }"
        />
      </div>
    </section>

    <!-- Detailed Grade Cards -->
    <section class="py-16 bg-gray-50">
      <div class="container-custom">
        <h2 class="text-3xl font-bold text-gray-900 mb-12 text-center">
          {{ t('qualityGuide.gradesTitle') }}
        </h2>

        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <!-- OEM -->
          <QualityGradeCard
            :grade="grades.oem"
            :pros-label="t('qualityGuide.labels.pros')"
            :cons-label="t('qualityGuide.labels.cons')"
            :recommended-label="t('qualityGuide.labels.recommended')"
          />

          <!-- Original -->
          <QualityGradeCard
            :grade="grades.original"
            :pros-label="t('qualityGuide.labels.pros')"
            :cons-label="t('qualityGuide.labels.cons')"
            :recommended-label="t('qualityGuide.labels.recommended')"
          />

          <!-- Genuine -->
          <QualityGradeCard
            :grade="grades.genuine"
            :pros-label="t('qualityGuide.labels.pros')"
            :cons-label="t('qualityGuide.labels.cons')"
            :recommended-label="t('qualityGuide.labels.recommended')"
          />

          <!-- Grade A -->
          <QualityGradeCard
            :grade="grades.a"
            :pros-label="t('qualityGuide.labels.pros')"
            :cons-label="t('qualityGuide.labels.cons')"
            :recommended-label="t('qualityGuide.labels.recommended')"
          />

          <!-- Grade B -->
          <QualityGradeCard
            :grade="grades.b"
            :pros-label="t('qualityGuide.labels.pros')"
            :cons-label="t('qualityGuide.labels.cons')"
            :recommended-label="t('qualityGuide.labels.recommended')"
          />

          <!-- Grade C -->
          <QualityGradeCard
            :grade="grades.c"
            :pros-label="t('qualityGuide.labels.pros')"
            :cons-label="t('qualityGuide.labels.cons')"
            :recommended-label="t('qualityGuide.labels.recommended')"
          />

          <!-- Aftermarket -->
          <QualityGradeCard
            :grade="grades.aftermarket"
            :pros-label="t('qualityGuide.labels.pros')"
            :cons-label="t('qualityGuide.labels.cons')"
            :recommended-label="t('qualityGuide.labels.recommended')"
            class="lg:col-span-2"
          />
        </div>
      </div>
    </section>

    <!-- Selection Guide -->
    <section class="py-16 bg-white">
      <div class="container-custom max-w-4xl">
        <h2 class="text-3xl font-bold text-gray-900 mb-4 text-center">
          {{ t('qualityGuide.selectionGuide.title') }}
        </h2>
        <p class="text-lg text-gray-700 mb-8 text-center">
          {{ t('qualityGuide.selectionGuide.intro') }}
        </p>

        <div class="space-y-6">
          <div
            v-for="(factor, index) in selectionFactors"
            :key="index"
            class="bg-gray-50 rounded-lg p-6 border-l-4 border-accent-600"
          >
            <h3 class="text-xl font-semibold text-gray-900 mb-2">
              {{ factor.title }}
            </h3>
            <p class="text-gray-700">
              {{ factor.description }}
            </p>
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
          {{ t('qualityGuide.cta.title') }}
        </h2>
        <p class="text-xl text-gray-300 mb-8 max-w-2xl mx-auto">
          {{ t('qualityGuide.cta.description') }}
        </p>
        <NuxtLink :to="localePath('/contact')" class="btn-accent inline-block">
          {{ t('qualityGuide.cta.button') }}
        </NuxtLink>
      </div>
    </section>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'
import QualityGradeCard from '~/components/quality/QualityGradeCard.vue'
import GradeComparison from '~/components/quality/GradeComparison.vue'

const { t } = useI18n()
const localePath = useLocalePath()

// Define level values for each grade
const gradeLevels = {
  oem: 5,
  original: 5,
  genuine: 4,
  a: 4,
  b: 3,
  c: 2,
  aftermarket: 3
}

// Get all grades from translations
const getGrade = (gradeKey) => {
  // Get pros array
  const pros = []
  let prosIndex = 0
  while (true) {
    const prosKey = `qualityGuide.grades.${gradeKey}.pros.${prosIndex}`
    const prosValue = t(prosKey)
    if (prosValue === prosKey) break // Translation not found
    pros.push(prosValue)
    prosIndex++
  }

  // Get cons array
  const cons = []
  let consIndex = 0
  while (true) {
    const consKey = `qualityGuide.grades.${gradeKey}.cons.${consIndex}`
    const consValue = t(consKey)
    if (consValue === consKey) break // Translation not found
    cons.push(consValue)
    consIndex++
  }

  return {
    badge: t(`qualityGuide.grades.${gradeKey}.badge`),
    title: t(`qualityGuide.grades.${gradeKey}.title`),
    level: gradeLevels[gradeKey] || 3,
    description: t(`qualityGuide.grades.${gradeKey}.description`),
    price: t(`qualityGuide.grades.${gradeKey}.price`),
    warranty: t(`qualityGuide.grades.${gradeKey}.warranty`),
    bestFor: t(`qualityGuide.grades.${gradeKey}.bestFor`),
    pros,
    cons
  }
}

const grades = computed(() => ({
  oem: getGrade('oem'),
  original: getGrade('original'),
  genuine: getGrade('genuine'),
  a: getGrade('a'),
  b: getGrade('b'),
  c: getGrade('c'),
  aftermarket: getGrade('aftermarket')
}))

// Get selection factors
const selectionFactors = computed(() => {
  const factors = []
  for (let i = 0; i < 5; i++) {
    factors.push({
      title: t(`qualityGuide.selectionGuide.factors.${i}.title`),
      description: t(`qualityGuide.selectionGuide.factors.${i}.description`)
    })
  }
  return factors
})

// Prepare data for comparison table
const comparisonData = computed(() => {
  const gradesObj = grades.value
  return [
    {
      name: gradesObj.oem?.badge || 'OEM',
      level: gradesObj.oem?.level || 5,
      price: gradesObj.oem?.price || '$$$$$',
      warranty: gradesObj.oem?.warranty || '12-24 months',
      bestFor: gradesObj.oem?.bestFor || 'Critical components'
    },
    {
      name: gradesObj.original?.badge || 'Original',
      level: gradesObj.original?.level || 5,
      price: gradesObj.original?.price || '$$$$$',
      warranty: gradesObj.original?.warranty || '12-18 months',
      bestFor: gradesObj.original?.bestFor || 'New vehicles'
    },
    {
      name: gradesObj.genuine?.badge || 'Genuine',
      level: gradesObj.genuine?.level || 4,
      price: gradesObj.genuine?.price || '$$$$',
      warranty: gradesObj.genuine?.warranty || '6-12 months',
      bestFor: gradesObj.genuine?.bestFor || 'Regular maintenance'
    },
    {
      name: gradesObj.a?.badge || 'Grade A',
      level: gradesObj.a?.level || 4,
      price: gradesObj.a?.price || '$$$',
      warranty: gradesObj.a?.warranty || '6-12 months',
      bestFor: gradesObj.a?.bestFor || 'Quality-conscious buyers'
    },
    {
      name: gradesObj.b?.badge || 'Grade B',
      level: gradesObj.b?.level || 3,
      price: gradesObj.b?.price || '$$',
      warranty: gradesObj.b?.warranty || '3-6 months',
      bestFor: gradesObj.b?.bestFor || 'Standard repairs'
    },
    {
      name: gradesObj.c?.badge || 'Grade C',
      level: gradesObj.c?.level || 2,
      price: gradesObj.c?.price || '$',
      warranty: gradesObj.c?.warranty || '0-3 months',
      bestFor: gradesObj.c?.bestFor || 'Temporary fixes'
    },
    {
      name: gradesObj.aftermarket?.badge || 'Aftermarket',
      level: gradesObj.aftermarket?.level || 3,
      price: gradesObj.aftermarket?.price || '$$-$$$',
      warranty: gradesObj.aftermarket?.warranty || '3-6 months',
      bestFor: gradesObj.aftermarket?.bestFor || 'General replacements'
    }
  ]
})

// SEO
useSeoMeta({
  title: t('meta.qualityGuide.title'),
  description: t('meta.qualityGuide.description'),
  ogTitle: t('meta.qualityGuide.title'),
  ogDescription: t('meta.qualityGuide.description'),
  ogType: 'website'
})
</script>
