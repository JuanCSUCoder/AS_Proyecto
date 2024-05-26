import { Order } from "./Order";
import { Product } from "./Product";

export interface OrderItem {
  id: string,
  quantity: number,
  order: Order,
  product: Product
}
