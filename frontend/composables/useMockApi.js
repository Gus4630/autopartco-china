// Composable for mock API data
// Switch USE_MOCK_DATA to false when real API is ready

import {
  mockProducts,
  mockCategories,
  mockBrands,
  getProductBySlug,
  getRelatedProducts,
  getFeaturedProducts,
  getProductsByCategory,
  getProductsByBrand
} from '~/utils/mockData'

const USE_MOCK_DATA = true // Set to false to use real API

export const useMockApi = () => {
  const config = useRuntimeConfig()

  // Simulate API delay
  const delay = (ms = 300) => new Promise(resolve => setTimeout(resolve, ms))

  // Get all products
  const fetchProducts = async () => {
    if (USE_MOCK_DATA) {
      await delay()
      return mockProducts
    }
    // Real API call
    return await $fetch(`${config.public.apiBase}/products`)
  }

  // Get product by slug
  const fetchProductBySlug = async (slug) => {
    if (USE_MOCK_DATA) {
      await delay()
      const product = getProductBySlug(slug)
      if (!product) {
        throw new Error('Product not found')
      }
      return product
    }
    // Real API call
    return await $fetch(`${config.public.apiBase}/products/${slug}`)
  }

  // Get related products
  const fetchRelatedProducts = async (productId) => {
    if (USE_MOCK_DATA) {
      await delay()
      return getRelatedProducts(productId)
    }
    // Real API call
    return await $fetch(`${config.public.apiBase}/products/${productId}/related`)
  }

  // Get featured products
  const fetchFeaturedProducts = async () => {
    if (USE_MOCK_DATA) {
      await delay()
      return getFeaturedProducts()
    }
    // Real API call
    return await $fetch(`${config.public.apiBase}/products/featured`)
  }

  // Get all categories
  const fetchCategories = async () => {
    if (USE_MOCK_DATA) {
      await delay()
      return mockCategories
    }
    // Real API call
    return await $fetch(`${config.public.apiBase}/categories`)
  }

  // Get all brands
  const fetchBrands = async () => {
    if (USE_MOCK_DATA) {
      await delay()
      return mockBrands
    }
    // Real API call
    return await $fetch(`${config.public.apiBase}/brands`)
  }

  // Submit contact form
  const submitContactForm = async (formData) => {
    if (USE_MOCK_DATA) {
      await delay(1000)
      // Simulate successful submission
      console.log('Mock contact form submission:', formData)
      return { success: true, message: 'Form submitted successfully' }
    }
    // Real API call
    return await $fetch(`${config.public.apiBase}/contact`, {
      method: 'POST',
      body: formData
    })
  }

  return {
    fetchProducts,
    fetchProductBySlug,
    fetchRelatedProducts,
    fetchFeaturedProducts,
    fetchCategories,
    fetchBrands,
    submitContactForm
  }
}
