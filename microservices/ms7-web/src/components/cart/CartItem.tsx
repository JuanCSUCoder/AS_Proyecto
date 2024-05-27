import { OrderItem } from "@/model/OrderItem";
import { Delete } from "@mui/icons-material";
import { Button } from "@mui/joy";

export function CartItem({ orderItem }: {
  orderItem: OrderItem
}) {
  return (
    <tr>
      <td>Avena</td>
      <td>1x</td>
      <td>$ 1.000</td>
      <td>
        <Button color="danger">
          <Delete />
          <span className="hidden lg:inline-block">Eliminar</span>
        </Button>
      </td>
    </tr>
  );
}