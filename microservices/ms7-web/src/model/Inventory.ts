import { Product } from "./Product";

export interface Inventory {
  id?: string,
  location?: string,
  stock?: number,
  product?: Product
}
