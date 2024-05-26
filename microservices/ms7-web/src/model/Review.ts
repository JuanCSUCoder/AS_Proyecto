import { Product } from "./Product";
import { User } from "./User";

export interface Review {
  id: string,
  score: number,
  product: Product,
  user: User
}