import { OrderItem } from "@/model/OrderItem";
import { ProductBtns } from "../products/ProductBtns";

export function CartItem({ orderItem, readonly }: {
  orderItem: OrderItem,
  readonly?: boolean
}) {
  const { product, quantity } = orderItem;

  return (
    <tr>
      <td>{product?.name}</td>
      <td>{quantity}x</td>
      <td>$ {product?.price}</td>
      {!readonly && (
        <td>
          <ProductBtns product={product ? product : {}} />
        </td>
      )}
    </tr>
  );
}