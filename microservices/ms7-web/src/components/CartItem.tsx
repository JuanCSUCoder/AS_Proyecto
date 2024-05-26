import { OrderItem } from "../model/OrderItem";

export function CartItem({ orderItem }: {
  orderItem: OrderItem
}) {
  return (
    <tr>
      <td>Avena</td>
      <td>1x</td>
      <td>$ 1.000</td>
    </tr>
  )
}