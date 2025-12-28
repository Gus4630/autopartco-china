// Mock data for development before API is ready

export const mockCategories = [
  {
    id: 'cat-1',
    name: { en: 'Engine Parts', zh: '发动机配件' },
    description: { en: 'Complete range of engine components and parts', zh: '全系列发动机部件和配件' }
  },
  {
    id: 'cat-2',
    name: { en: 'Transmission Parts', zh: '变速箱配件' },
    description: { en: 'Transmission systems and components', zh: '变速系统和部件' }
  },
  {
    id: 'cat-3',
    name: { en: 'Brake System', zh: '制动系统' },
    description: { en: 'Brake pads, discs, and hydraulic components', zh: '刹车片、刹车盘和液压部件' }
  },
  {
    id: 'cat-4',
    name: { en: 'Suspension Parts', zh: '悬挂系统' },
    description: { en: 'Shock absorbers, springs, and chassis components', zh: '减震器、弹簧和底盘部件' }
  },
  {
    id: 'cat-5',
    name: { en: 'Electrical System', zh: '电气系统' },
    description: { en: 'Sensors, switches, and electrical components', zh: '传感器、开关和电气部件' }
  },
  {
    id: 'cat-6',
    name: { en: 'Cooling System', zh: '冷却系统' },
    description: { en: 'Radiators, water pumps, and cooling components', zh: '散热器、水泵和冷却部件' }
  }
]

export const mockBrands = [
  {
    id: 'brand-1',
    name: { en: 'Sinotruk (CNHTC)', zh: '中国重汽' },
    description: { en: 'China National Heavy Duty Truck Group', zh: '中国重型汽车集团' },
    logo: null
  },
  {
    id: 'brand-2',
    name: { en: 'Weichai Power', zh: '潍柴动力' },
    description: { en: 'Leading engine manufacturer', zh: '领先的发动机制造商' },
    logo: null
  },
  {
    id: 'brand-3',
    name: { en: 'HOWO', zh: 'HOWO' },
    description: { en: 'Sinotruk truck brand', zh: '中国重汽卡车品牌' },
    logo: null
  }
]

export const mockProducts = [
  {
    id: 'prod-1',
    slug: 'weichai-wd12-engine-assembly',
    name: { en: 'Weichai WD12 Engine Assembly', zh: '潍柴WD12发动机总成' },
    description: {
      en: 'Complete Weichai WD12 diesel engine assembly with 220HP, Euro 3 emission standard. Suitable for heavy-duty trucks and commercial vehicles.',
      zh: '潍柴WD12柴油发动机总成，220马力，欧三排放标准。适用于重型卡车和商用车。'
    },
    partNumber: 'WD12-G220E301',
    carBrand: { en: 'Sinotruk', zh: '中国重汽' },
    carModel: { en: 'HOWO A7, HOWO T7H', zh: 'HOWO A7, HOWO T7H' },
    manufacturingCountry: { en: 'China', zh: '中国' },
    category: { id: 'cat-1', name: { en: 'Engine Parts', zh: '发动机配件' } },
    factoryAddress: {
      en: 'Weichai Power Co., Ltd, Weifang City, Shandong Province, China',
      zh: '潍柴动力股份有限公司，中国山东省潍坊市'
    },
    condition: 'NEW',
    quality: 'GENUINE',
    partYear: '2023',
    images: [
      'https://images.unsplash.com/photo-1486262715619-67b85e0b08d3?w=800',
      'https://images.unsplash.com/photo-1619642751034-765dfdf7c58e?w=800'
    ]
  },
  {
    id: 'prod-2',
    slug: 'sinotruk-howo-brake-disc',
    name: { en: 'Sinotruk HOWO Brake Disc', zh: '中国重汽HOWO刹车盘' },
    description: {
      en: 'Front brake disc for Sinotruk HOWO trucks. Made from high-quality cast iron with excellent heat dissipation properties.',
      zh: '中国重汽HOWO卡车前刹车盘。采用优质铸铁制造，具有出色的散热性能。'
    },
    partNumber: 'AZ9231340006',
    carBrand: { en: 'Sinotruk', zh: '中国重汽' },
    carModel: { en: 'HOWO A7, T7H, T5G', zh: 'HOWO A7, T7H, T5G' },
    manufacturingCountry: { en: 'China', zh: '中国' },
    category: { id: 'cat-3', name: { en: 'Brake System', zh: '制动系统' } },
    factoryAddress: {
      en: 'Sinotruk Jinan Truck Co., Ltd, Jinan City, Shandong Province, China',
      zh: '中国重型汽车集团济南卡车有限公司，中国山东省济南市'
    },
    condition: 'NEW',
    quality: 'OEM',
    partYear: '2022',
    images: ['https://images.unsplash.com/photo-1625047509168-a7026f36de04?w=800']
  },
  {
    id: 'prod-3',
    slug: 'weichai-fuel-injection-pump',
    name: { en: 'Weichai Fuel Injection Pump', zh: '潍柴燃油喷射泵' },
    description: {
      en: 'High-pressure fuel injection pump for Weichai WD615 engine series. Ensures optimal fuel delivery and engine performance.',
      zh: '潍柴WD615系列发动机高压燃油喷射泵。确保最佳燃油输送和发动机性能。'
    },
    partNumber: '612600080674',
    carBrand: { en: 'Sinotruk', zh: '中国重汽' },
    carModel: { en: 'HOWO, Steyr King', zh: 'HOWO, 斯太尔王' },
    manufacturingCountry: { en: 'China', zh: '中国' },
    category: { id: 'cat-1', name: { en: 'Engine Parts', zh: '发动机配件' } },
    factoryAddress: {
      en: 'Weichai Power Co., Ltd, Weifang City, Shandong Province, China',
      zh: '潍柴动力股份有限公司，中国山东省潍坊市'
    },
    condition: 'NEW',
    quality: 'GENUINE',
    partYear: '2023',
    images: ['https://images.unsplash.com/photo-1581092160562-40aa08e78837?w=800']
  },
  {
    id: 'prod-4',
    slug: 'howo-air-filter',
    name: { en: 'HOWO Air Filter Element', zh: 'HOWO空气滤芯' },
    description: {
      en: 'High-efficiency air filter element for HOWO trucks. Protects engine from dust and contaminants.',
      zh: 'HOWO卡车高效空气滤芯。保护发动机免受灰尘和污染物侵害。'
    },
    partNumber: 'WG9719190001',
    carBrand: { en: 'Sinotruk', zh: '中国重汽' },
    carModel: { en: 'HOWO A7, T7H, Dump Trucks', zh: 'HOWO A7, T7H, 自卸车' },
    manufacturingCountry: { en: 'China', zh: '中国' },
    category: { id: 'cat-1', name: { en: 'Engine Parts', zh: '发动机配件' } },
    factoryAddress: {
      en: 'Sinotruk Parts Manufacturing, Jinan City, Shandong Province, China',
      zh: '中国重汽配件制造厂，中国山东省济南市'
    },
    condition: 'NEW',
    quality: 'OEM',
    partYear: '2023',
    images: ['https://images.unsplash.com/photo-1621939514649-280e2ee25f60?w=800']
  },
  {
    id: 'prod-5',
    slug: 'sinotruk-clutch-pressure-plate',
    name: { en: 'Sinotruk Clutch Pressure Plate', zh: '中国重汽离合器压盘' },
    description: {
      en: 'Heavy-duty clutch pressure plate assembly for Sinotruk vehicles. Designed for reliable power transmission.',
      zh: '中国重汽车辆重型离合器压盘总成。专为可靠的动力传输而设计。'
    },
    partNumber: 'AZ9725160100',
    carBrand: { en: 'Sinotruk', zh: '中国重汽' },
    carModel: { en: 'HOWO, Steyr King', zh: 'HOWO, 斯太尔王' },
    manufacturingCountry: { en: 'China', zh: '中国' },
    category: { id: 'cat-2', name: { en: 'Transmission Parts', zh: '变速箱配件' } },
    factoryAddress: {
      en: 'Sinotruk Transmission Factory, Jinan City, Shandong Province, China',
      zh: '中国重汽变速箱厂，中国山东省济南市'
    },
    condition: 'NEW',
    quality: 'GENUINE',
    partYear: '2022',
    images: ['https://images.unsplash.com/photo-1580273916550-e323be2ae537?w=800']
  },
  {
    id: 'prod-6',
    slug: 'weichai-oil-filter',
    name: { en: 'Weichai Engine Oil Filter', zh: '潍柴发动机机油滤清器' },
    description: {
      en: 'Premium quality oil filter for Weichai engines. Ensures clean oil circulation and extends engine life.',
      zh: '潍柴发动机优质机油滤清器。确保机油清洁循环，延长发动机寿命。'
    },
    partNumber: '61000070005',
    carBrand: { en: 'Sinotruk', zh: '中国重汽' },
    carModel: { en: 'HOWO (WD615, WD12, WP10 engines)', zh: 'HOWO (WD615, WD12, WP10发动机)' },
    manufacturingCountry: { en: 'China', zh: '中国' },
    category: { id: 'cat-1', name: { en: 'Engine Parts', zh: '发动机配件' } },
    factoryAddress: {
      en: 'Weichai Filter Manufacturing, Weifang City, Shandong Province, China',
      zh: '潍柴滤清器制造厂，中国山东省潍坊市'
    },
    condition: 'NEW',
    quality: 'GENUINE',
    partYear: '2023',
    images: ['https://images.unsplash.com/photo-1609193423407-0abbb323ddc7?w=800']
  },
  {
    id: 'prod-7',
    slug: 'howo-shock-absorber',
    name: { en: 'HOWO Front Shock Absorber', zh: 'HOWO前减震器' },
    description: {
      en: 'Heavy-duty front shock absorber for HOWO trucks. Provides superior ride comfort and handling.',
      zh: 'HOWO卡车重型前减震器。提供卓越的驾乘舒适性和操控性。'
    },
    partNumber: 'WG9719680004',
    carBrand: { en: 'Sinotruk', zh: '中国重汽' },
    carModel: { en: 'HOWO A7, T7H, T5G', zh: 'HOWO A7, T7H, T5G' },
    manufacturingCountry: { en: 'China', zh: '中国' },
    category: { id: 'cat-4', name: { en: 'Suspension Parts', zh: '悬挂系统' } },
    factoryAddress: {
      en: 'Sinotruk Chassis Parts, Jinan City, Shandong Province, China',
      zh: '中国重汽底盘配件厂，中国山东省济南市'
    },
    condition: 'NEW',
    quality: 'OEM',
    partYear: '2022',
    images: ['https://images.unsplash.com/photo-1563453392212-326f5e854473?w=800']
  },
  {
    id: 'prod-8',
    slug: 'sinotruk-radiator',
    name: { en: 'Sinotruk Heavy Truck Radiator', zh: '中国重汽散热器' },
    description: {
      en: 'High-performance radiator for Sinotruk heavy trucks. Efficient cooling for demanding applications.',
      zh: '中国重汽重型卡车高性能散热器。为高要求应用提供高效冷却。'
    },
    partNumber: 'WG9725530001',
    carBrand: { en: 'Sinotruk', zh: '中国重汽' },
    carModel: { en: 'HOWO series, Dump trucks, Tractor units', zh: 'HOWO系列, 自卸车, 牵引车' },
    manufacturingCountry: { en: 'China', zh: '中国' },
    category: { id: 'cat-6', name: { en: 'Cooling System', zh: '冷却系统' } },
    factoryAddress: {
      en: 'Sinotruk Cooling System Factory, Jinan City, Shandong Province, China',
      zh: '中国重汽冷却系统厂，中国山东省济南市'
    },
    condition: 'NEW',
    quality: 'GENUINE',
    partYear: '2023',
    images: ['https://images.unsplash.com/photo-1621939514649-280e2ee25f60?w=800']
  }
]

// Helper function to get products by category
export const getProductsByCategory = (categoryId) => {
  return mockProducts.filter(product => product.category.id === categoryId)
}

// Helper function to get products by brand (car brand)
export const getProductsByBrand = (brandName) => {
  return mockProducts.filter(product =>
    product.carBrand.en.toLowerCase().includes(brandName.toLowerCase())
  )
}

// Helper function to get product by slug
export const getProductBySlug = (slug) => {
  return mockProducts.find(product => product.slug === slug)
}

// Helper function to get related products (same category)
export const getRelatedProducts = (productId, limit = 4) => {
  const product = mockProducts.find(p => p.id === productId)
  if (!product) return []

  return mockProducts
    .filter(p => p.id !== productId && p.category.id === product.category.id)
    .slice(0, limit)
}

// Helper function to get featured products
export const getFeaturedProducts = (limit = 8) => {
  return mockProducts.slice(0, limit)
}
