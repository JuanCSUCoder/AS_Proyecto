import { Sheet } from "@mui/joy";
import Table from "@mui/joy/Table";
import { CartItem } from "./CartItem";
import { Order } from "@/model/Order";

export function CartList({order}: {order: Order}) {

  return (
    <div className="w-full md:w-2/3 md:mr-5">
      <Sheet
        sx={{
          width: "100%",
          height: "100%",
          overflow: "auto",
        }}
      >
        <Table
          size="lg"
          stickyFooter
          stickyHeader
          variant="plain"
          sx={{
            width: "100%",
          }}
        >
          <thead className="font-bold">
            <tr>
              <td>Nombre</td>
              <td>Cantidad</td>
              <td>Precio</td>
              <td></td>
            </tr>
          </thead>
          <tbody>
            {order.items?.map((item, idx) => (
              <CartItem key={`${idx}-cart-item53436`} orderItem={item} />
            ))}
          </tbody>
        </Table>
      </Sheet>
    </div>
  );
}