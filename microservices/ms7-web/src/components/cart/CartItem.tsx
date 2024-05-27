import { OrderItem } from "@/model/OrderItem";
import { Delete } from "@mui/icons-material";
import { Button } from "@mui/joy";

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
        <Button color="danger">
          <Delete />
          <span className="hidden lg:inline-block">Eliminar</span>
        </Button>
      </td>
    </tr>
  );
}