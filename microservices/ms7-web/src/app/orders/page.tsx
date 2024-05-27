import { OrdersList } from "@/components/orders/OrdersList";

export default function OrdersPage() {
  const orders = [
    {
      id: "dsvd",
    },
    {
      id: "dsvd",
    },
    {
      id: "dsvd",
    },
  ];

  return (
    <main className="flex w-full flex-col items-center justify-start">
      <h2>Historial de Pedidos</h2>
      <OrdersList orders={orders} />
    </main>
  );
}