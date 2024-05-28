export interface Pod {
  name?: string,
  email?: string,
  location?: {
    description?: {
      address?: string,
      city?: string,
      countryCode?: string
    },
    coordinates?: {
      lat?: number,
      lng?: number
    }
  },
  private?: {
    cardNumber?: string,
    cvv?: number
  } 
}

/*
{
  "name": "Juan Camilo Sánchez Urrego",
  "email": "juancsucoder@gmail.com",
  "location": {
    "description": {
      "address": "Cll 123 #12-12",
      "city": "Bogotá D.C.", // Nombre de la ciudad
      "countryCode": "COL" // ISO 3 digit code
    },
    "coordinates": {
      "lat": 42.251643,
      "lng": 52.024531
    }
  }
}
*/