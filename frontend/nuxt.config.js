// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2025-12-28',

  devtools: { enabled: true },

  // SSR Configuration
  ssr: true,

  // Modules
  modules: [
    '@nuxtjs/i18n',
    '@nuxtjs/seo',
    '@nuxtjs/tailwindcss',
    '@nuxt/image',
    '@pinia/nuxt',
    '@vueuse/nuxt'
  ],

  // i18n Configuration - Bilingual support (English and Chinese)
  i18n: {
    locales: [
      {
        code: 'en',
        iso: 'en-US',
        name: 'English',
        file: 'en.json'
      },
      {
        code: 'zh',
        iso: 'zh-CN',
        name: '中文',
        file: 'zh.json'
      }
    ],
    defaultLocale: 'en',
    strategy: 'prefix_except_default',
    langDir: 'locales',
    lazy: true,
    detectBrowserLanguage: {
      useCookie: true,
      cookieKey: 'i18n_redirected',
      redirectOn: 'root',
      alwaysRedirect: false,
      fallbackLocale: 'en'
    }
  },

  // SEO Configuration
  site: {
    url: 'https://autopartco-china.com',
    name: 'AutoPartCo China - Sinotruk & Weichai Parts Supplier',
    description: 'Professional supplier of genuine Sinotruk and Weichai auto parts from China. Quality parts, competitive prices, global shipping.',
    defaultLocale: 'en'
  },

  // SEO Module Options
  seo: {
    fallbackTitle: false,
    redirectToCanonicalSiteUrl: true
  },

  // Image Optimization
  image: {
    quality: 80,
    format: ['webp', 'jpg'],
    screens: {
      xs: 320,
      sm: 640,
      md: 768,
      lg: 1024,
      xl: 1280,
      xxl: 1536
    }
  },

  // Tailwind CSS Configuration
  tailwindcss: {
    cssPath: '~/assets/css/tailwind.css',
    configPath: 'tailwind.config.js',
    exposeConfig: false,
    viewer: true
  },

  // App Configuration
  app: {
    head: {
      charset: 'utf-8',
      viewport: 'width=device-width, initial-scale=1',
      htmlAttrs: {
        lang: 'en'
      },
      meta: [
        { name: 'format-detection', content: 'telephone=no' }
      ],
      link: [
        { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
      ]
    }
  },

  // Runtime Config - API Configuration
  runtimeConfig: {
    public: {
      apiBase: process.env.NUXT_PUBLIC_API_BASE || 'http://localhost:8080/api',
      siteUrl: process.env.NUXT_PUBLIC_SITE_URL || 'https://autopartco-china.com',
      companyPhone: '18660429678',
      companyLandline: '0531-85702200',
      companyEmail: '1745668156@qq.com',
      whatsappNumber: '8618660429678',
      wechatId: 'autopartco_china'
    }
  },

  // Vite Configuration
  vite: {
    css: {
      preprocessorOptions: {
        scss: {
          additionalData: ''
        }
      }
    }
  }
})
