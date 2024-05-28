"use client"

import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { Sheet, Table } from "@mui/joy";
import { useEffect, useState } from "react";

interface Stats {
  averageProductsPerOrder: number;
  totalUsers: number;
  maxStockProductName: string;
  totalProducts: number;
  totalOrderValue: number;
  totalOrders: number;
  minStockProductName: string;
  mostExpensiveOrder: number;
  cheapestOrder: number;
}

interface Almacen {
  id: string;
  location: string;
  stock: number;
};

export default function DashboardPage() {
  const [stats, setStats] = useState<Stats | undefined>(undefined);

  useEffect(() => {
    fetch("http://localhost:5016/stats")
      .then(res => res.json())
      .then(res => setStats(res));
  }, []);

  const [almacenes, setAlma] = useState<Almacen[]>([]);

  useEffect(() => {
    fetch("http://localhost:5016/stats/inventories")
      .then(res => res.json())
      .then(res => setAlma(res))
  })

  return (
    <DefaultContainer>
      <h2>Dashboard</h2>
      <h3>Estadísticas Generales</h3>
      <div className="flex flex-col items-start justify-start">
        <p>
          <strong>Total de Usuarios:</strong>

          {stats?.totalUsers}
        </p>
        <p>
          <strong>Producto con Mayor Inventario:</strong>

          {stats?.maxStockProductName}
        </p>
        <p>
          <strong>Producto con Menor Inventario:</strong>

          {stats?.minStockProductName}
        </p>
        <p>
          <strong>Total de Productos:</strong>

          {stats?.totalProducts}
        </p>
        <p>
          <strong>Promedio de Productos por Pedido:</strong>

          {stats?.averageProductsPerOrder}
        </p>
        <p>
          <strong>Valor Total de las Pedidos:</strong>

          {stats?.totalOrderValue}
        </p>
        <p>
          <strong>Cantidad de Pedidos:</strong>

          {stats?.totalOrders}
        </p>
        <p>
          <strong>Pedido Más Caro:</strong>

          {stats?.mostExpensiveOrder}
        </p>
        <p>
          <strong>Pedido Más Barato:</strong>

          {stats?.cheapestOrder}
        </p>

        <h3>Almacenes</h3>
        <Sheet>
          <Table>
            <thead>
              <tr>
                <td>ID</td>
                <td>Almacen</td>
                <td>Inventario</td>
              </tr>
            </thead>
            <tbody>
              {
                almacenes.map((almacen, idx) => (
                  <tr key={`almacen-${idx}`}>
                    <td>{almacen.id}</td>
                    <td>{almacen.location}</td>
                    <td>{almacen.stock}</td>
                  </tr>
                ))
              }
            </tbody>
          </Table>
        </Sheet>
      </div>
    </DefaultContainer>
  );
}