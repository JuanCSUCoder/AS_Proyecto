import { Order } from "@/model/Order";
import { Button, Sheet, Table } from "@mui/joy";
import Link from "next/link";

export function OrdersList({ orders }: {
  orders: Order[]
}) {
  return (
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
            <td>ID</td>
            <td>Estado</td>
            <td>Detalles</td>
          </tr>
        </thead>
        <tbody>
          {orders.map((order, idx) => (
            <tr key={`order-${idx}`}>
              <td>{order.id}</td>
              <td>{order.status}</td>
              <td>
                <Link href={`/order/${order.id}`}>
                  <Button color="neutral" size="sm" sx={{
                    fontSize: 12,
                  }}>Ver Detalles</Button>
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Sheet>
  );
}