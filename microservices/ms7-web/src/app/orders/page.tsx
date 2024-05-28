"use client"

import { OrdersList } from "@/components/orders/OrdersList";
import { Order } from "@/model/Order";
import { useEffect, useState } from "react";
import { useDBUser } from "../hooks/useDBUser";
import { usePodInfo } from "../hooks/usePodInfo";
import { DefaultContainer } from "@/components/utils/DefaultContainer";

export default function OrdersPage() {
  const [orders, setOrders] = useState<Order[]>([]);
  const podInfo = usePodInfo();
  const dbUser = useDBUser(podInfo?.webId);

  useEffect(() => {
    if (dbUser?.id) {
      fetch(
        "http://localhost:5010/gestionpedidos/api/orders/productos/cliente/" +
          dbUser?.id
      )
        .then((res) => res.json())
        .then((res) => setOrders(res))
        .catch();
    }
  }, [dbUser])

  return (
    <DefaultContainer>
      <h2>Historial de Pedidos</h2>
      <OrdersList orders={orders} />
    </DefaultContainer>
  );
}