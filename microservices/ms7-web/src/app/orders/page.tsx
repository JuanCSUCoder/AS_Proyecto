import { OrdersList } from "@/components/orders/OrdersList";
import { Order } from "@/model/Order";
import { useState } from "react";

export default function OrdersPage() {
  const [orders, setOrders] = useState<Order[]>([]);

  return (
    <main className="flex w-full flex-col items-center justify-start">
      <h2>Historial de Pedidos</h2>
      <OrdersList orders={orders} />
    </main>
  );
}