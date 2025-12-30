export const useImageUrl = () => {
  const config = useRuntimeConfig()

  const getImageUrl = (path) => {
    if (!path) return ''
    // If path already starts with http/https, return as is
    if (path.startsWith('http://') || path.startsWith('https://')) {
      return path
    }
    // Otherwise prepend the API base URL
    // Remove /api from apiBase since path already includes it
    const baseUrl = config.public.apiBase.replace('/api', '')
    return `${baseUrl}${path}`
  }

  return {
    getImageUrl
  }
}
