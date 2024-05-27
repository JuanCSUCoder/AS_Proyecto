import { OrderItem } from "@/model/OrderItem";
import { CartContext, CartProvider } from "@/providers/CartProvider";
import { Delete } from "@mui/icons-material";
import { Button } from "@mui/joy";
import { useContext } from "react";

export function CartItem({ orderItem }: {
  orderItem: OrderItem
}) {
  const ctx = useContext(CartContext);
  const { product, quantity } = orderItem;

  return (
    <tr>
      <td>{product?.name}</td>
      <td>{quantity}x</td>
      <td>$ {product?.price}</td>
      <td>
        <Button color="danger" onClick={() => product && ctx?.removeItem(product)}>
          <Delete />
          <span className="hidden lg:inline-block">Eliminar</span>
        </Button>
      </td>
    </tr>
  );
}