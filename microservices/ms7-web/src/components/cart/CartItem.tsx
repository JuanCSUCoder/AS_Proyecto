import { OrderItem } from "@/model/OrderItem";
import { ProductBtns } from "../products/ProductBtns";

export function CartItem({ orderItem }: {
  orderItem: OrderItem
}) {
  const { product, quantity } = orderItem;

  return (
    <tr>
      <td>{product?.name}</td>
      <td>{quantity}x</td>
      <td>$ {product?.price}</td>
      <td>
        <ProductBtns product={product ? product : {}} />
      </td>
    </tr>
  );
}