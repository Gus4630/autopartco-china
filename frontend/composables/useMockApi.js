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

const USE_MOCK_DATA = false // Set to false to use real API

export const useMockApi = () => {
  const config = useRuntimeConfig()

  // Simulate API delay
  const delay = (ms = 300) => new Promise(resolve => setTimeout(resolve, ms))

  // Get all products
  const fetchProducts = async (params = {}) => {
    if (USE_MOCK_DATA) {
      await delay()
      return { content: mockProducts, totalElements: mockProducts.length, totalPages: 1 }
    }
    // Real API call - returns Spring Page object
    const queryParams = new URLSearchParams({
      page: params.page || 0,
      size: params.size || 20,
      ...(params.category && { category: params.category }),
      ...(params.search && { search: params.search })
    }).toString()
    return await $fetch(`${config.public.apiBase}/auto-parts?${queryParams}`)
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
    return await $fetch(`${config.public.apiBase}/auto-parts/${slug}`)
  }

  // Get related products
  const fetchRelatedProducts = async (productId) => {
    if (USE_MOCK_DATA) {
      await delay()
      return getRelatedProducts(productId)
    }
    // Real API call
    return await $fetch(`${config.public.apiBase}/auto-parts/${productId}/related`)
  }

  // Get featured products (first 8 products)
  const fetchFeaturedProducts = async () => {
    if (USE_MOCK_DATA) {
      await delay()
      return getFeaturedProducts()
    }
    // Real API call - fetch first 8 products
    const response = await $fetch(`${config.public.apiBase}/auto-parts?page=0&size=8`)
    return response.content || []
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

  // Get all brands (disabled - hidden from UI)
  const fetchBrands = async () => {
    if (USE_MOCK_DATA) {
      await delay()
      return mockBrands
    }
    // Brands functionality is disabled
    return []
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
