import { OrderItem } from "./OrderItem";
import { User } from "./User";

export interface Order {
  id?: string,
  status?: string,
  total?: number,
  user?: User,
  items?: OrderItem[]
}