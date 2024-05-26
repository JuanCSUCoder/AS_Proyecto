import { Order } from "./Order";
import { Review } from "./Review";

export interface User {
  id: string,
  userPod: string,
  providerUrl: string,
  orders: Order[],
  reviews: Review[]
}