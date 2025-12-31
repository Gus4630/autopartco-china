<template>
  <div class="bg-gray-50 py-12">
    <div class="container-custom">
      <div class="max-w-4xl mx-auto">
        <h1 class="text-4xl font-bold text-gray-900 mb-4 text-center">
          {{ t('contact.title') }}
        </h1>
        <p class="text-lg text-gray-600 mb-12 text-center">
          {{ t('contact.subtitle') }}
        </p>

        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <!-- Contact Form -->
          <div class="bg-white rounded-lg shadow-md p-8">
            <form @submit.prevent="handleSubmit">
              <div class="mb-6">
                <label for="name" class="block text-sm font-medium text-gray-700 mb-2">
                  {{ t('contact.form.name') }}
                </label>
                <input
                  id="name"
                  v-model="form.name"
                  type="text"
                  required
                  class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-accent-500 focus:border-transparent"
                />
              </div>

              <div class="mb-6">
                <label for="email" class="block text-sm font-medium text-gray-700 mb-2">
                  {{ t('contact.form.email') }}
                </label>
                <input
                  id="email"
                  v-model="form.email"
                  type="email"
                  required
                  class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-accent-500 focus:border-transparent"
                />
              </div>

              <div class="mb-6">
                <label for="phone" class="block text-sm font-medium text-gray-700 mb-2">
                  {{ t('contact.form.phone') }}
                </label>
                <input
                  id="phone"
                  v-model="form.phone"
                  type="tel"
                  class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-accent-500 focus:border-transparent"
                />
              </div>

              <div class="mb-6">
                <label for="subject" class="block text-sm font-medium text-gray-700 mb-2">
                  {{ t('contact.form.subject') }}
                </label>
                <input
                  id="subject"
                  v-model="form.subject"
                  type="text"
                  required
                  class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-accent-500 focus:border-transparent"
                />
              </div>

              <div class="mb-6">
                <label for="message" class="block text-sm font-medium text-gray-700 mb-2">
                  {{ t('contact.form.message') }}
                </label>
                <textarea
                  id="message"
                  v-model="form.message"
                  rows="5"
                  required
                  class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-accent-500 focus:border-transparent"
                ></textarea>
              </div>

              <button
                type="submit"
                :disabled="submitting"
                class="btn-accent w-full"
              >
                {{ submitting ? t('contact.form.sending') : t('contact.form.send') }}
              </button>

              <!-- Success Message -->
              <div v-if="showSuccess" class="mt-4 p-4 bg-green-50 border border-green-200 rounded-lg">
                <p class="text-green-800">{{ t('contact.form.success') }}</p>
              </div>

              <!-- Error Message -->
              <div v-if="showError" class="mt-4 p-4 bg-red-50 border border-red-200 rounded-lg">
                <p class="text-red-800">{{ t('contact.form.error') }}</p>
              </div>
            </form>
          </div>

          <!-- Contact Information -->
          <div>
            <div class="bg-white rounded-lg shadow-md p-8 mb-6">
              <h2 class="text-2xl font-bold text-gray-900 mb-6">
                {{ t('contact.info.title') }}
              </h2>

              <div class="space-y-4">
                <div class="flex items-start">
                  <svg class="w-6 h-6 text-accent-600 mr-3 mt-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                  </svg>
                  <div>
                    <p class="font-medium text-gray-900">{{ t('footer.phone') }}</p>
                    <a :href="`tel:${config.public.companyPhone}`" class="text-accent-600 hover:underline">
                      {{ config.public.companyPhone }}
                    </a>
                  </div>
                </div>

                <div class="flex items-start">
                  <svg class="w-6 h-6 text-accent-600 mr-3 mt-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                  </svg>
                  <div>
                    <p class="font-medium text-gray-900">{{ t('footer.landline') }}</p>
                    <a :href="`tel:${config.public.companyLandline}`" class="text-accent-600 hover:underline">
                      {{ config.public.companyLandline }}
                    </a>
                  </div>
                </div>

                <div class="flex items-start">
                  <svg class="w-6 h-6 text-accent-600 mr-3 mt-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                  </svg>
                  <div>
                    <p class="font-medium text-gray-900">{{ t('footer.email') }}</p>
                    <a :href="`mailto:${config.public.companyEmail}`" class="text-accent-600 hover:underline">
                      {{ config.public.companyEmail }}
                    </a>
                  </div>
                </div>

                <div class="flex items-start">
                  <svg class="w-6 h-6 text-accent-600 mr-3 mt-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                  </svg>
                  <div>
                    <p class="font-medium text-gray-900">{{ t('contact.info.address') }}</p>
                    <p class="text-gray-600">{{ t('contact.info.addressValue') }}</p>
                  </div>
                </div>

                <div class="flex items-start">
                  <svg class="w-6 h-6 text-accent-600 mr-3 mt-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                  </svg>
                  <div>
                    <p class="font-medium text-gray-900">{{ t('contact.info.hours') }}</p>
                    <p class="text-gray-600">{{ t('contact.info.hoursValue') }}</p>
                  </div>
                </div>
              </div>
            </div>

            <!-- WhatsApp & WeChat -->
            <div class="bg-gray-50 rounded-lg p-6 border border-gray-200">
              <h3 class="font-semibold text-gray-900 mb-4">
                {{ locale === 'zh' ? '快速联系' : 'Quick Contact' }}
              </h3>
              <div class="space-y-3">
                <a
                  :href="`https://wa.me/${config.public.whatsappNumber}`"
                  target="_blank"
                  rel="noopener noreferrer"
                  class="flex items-center text-accent-500 hover:text-accent-600 transition-colors"
                >
                  <svg class="w-6 h-6 mr-2" fill="currentColor" viewBox="0 0 24 24">
                    <path d="M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347m-5.421 7.403h-.004a9.87 9.87 0 01-5.031-1.378l-.361-.214-3.741.982.998-3.648-.235-.374a9.86 9.86 0 01-1.51-5.26c.001-5.45 4.436-9.884 9.888-9.884 2.64 0 5.122 1.03 6.988 2.898a9.825 9.825 0 012.893 6.994c-.003 5.45-4.437 9.884-9.885 9.884m8.413-18.297A11.815 11.815 0 0012.05 0C5.495 0 .16 5.335.157 11.892c0 2.096.547 4.142 1.588 5.945L.057 24l6.305-1.654a11.882 11.882 0 005.683 1.448h.005c6.554 0 11.89-5.335 11.893-11.893a11.821 11.821 0 00-3.48-8.413z"/>
                  </svg>
                  WhatsApp: +{{ config.public.whatsappNumber }}
                </a>
                <div class="flex items-center text-gray-700">
                  <svg class="w-6 h-6 mr-2 text-accent-500" fill="currentColor" viewBox="0 0 24 24">
                    <path d="M8.691 2.188C7.907 2.188 7.06 2.526 6.5 3.12c-1.016 1.073-1.559 2.655-1.42 4.26.03.345.06.631.09.917.18 1.753.35 3.506.52 5.259.15 1.553.3 3.106.45 4.659.06.61.12 1.22.18 1.83.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81.03.27.06.54.09.81z"/>
                  </svg>
                  WeChat: {{ config.public.wechatId }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { t, locale } = useI18n()
const config = useRuntimeConfig()
const { submitContactForm } = useMockApi()

const form = ref({
  name: '',
  email: '',
  phone: '',
  subject: '',
  message: ''
})

const submitting = ref(false)
const showSuccess = ref(false)
const showError = ref(false)

const handleSubmit = async () => {
  submitting.value = true
  showSuccess.value = false
  showError.value = false

  try {
    // USING MOCK DATA
    await submitContactForm(form.value)

    // Real API (uncomment when backend is ready):
    // await $fetch(`${config.public.apiBase}/contact`, {
    //   method: 'POST',
    //   body: form.value
    // })

    showSuccess.value = true
    form.value = {
      name: '',
      email: '',
      phone: '',
      subject: '',
      message: ''
    }
  } catch (error) {
    showError.value = true
  } finally {
    submitting.value = false
  }
}

// SEO
useSeoMeta({
  title: t('meta.contact.title'),
  description: t('meta.contact.description')
})
</script>
