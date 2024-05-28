"use client"

import { OrdersList } from "@/components/orders/OrdersList";
import { Order } from "@/model/Order";
import { useEffect, useState } from "react";

export default function OrdersPage() {
  const [orders, setOrders] = useState<Order[]>([]);

  useEffect(() => {
    fetch("http://localhost:5010/gestionpedidos/api/orders/all").then(res => res.json()).then(res => setOrders(res)).catch();
  }, [])

  return (
    <main className="flex w-full flex-col items-center justify-start">
      <h2>Historial de Pedidos</h2>
      <OrdersList orders={orders} />
    </main>
  );
}