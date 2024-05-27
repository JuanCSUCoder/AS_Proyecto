import { CartList } from "@/components/cart/CartList";
import { PriceTag } from "@/components/products/PriceTag";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { Order } from "@/model/Order";
import { Button } from "@mui/joy";
import Link from "next/link";

export default function CartPage() {
  const order: Order = {
    id: "485629-458720-246524-246246",
    status: "PENDING",
    total: 148.08,
    items: [
      {
        id: "1",
        quantity: 3,
        product: {
          id: "1",
          descr: "Descripcion producto",
          name: "Cocacola",
          price: 12.34,
        },
      },
      {
        id: "2",
        quantity: 1,
        product: {
          id: "2",
          descr: "Descripcion producto 2",
          name: "Arduino Uno",
          price: 12.34,
        },
      },
      {
        id: "3",
        quantity: 8,
        product: {
          id: "3",
          descr: "Descripcion producto 3",
          name: "Avena",
          price: 12.34,
        },
      },
    ],
  };

  return (
    <DefaultContainer>
      <h2>Lista de Compras</h2>
      <div className="flex flex-col md:flex-row justify-between items-center">
        <CartList order={order} />
        <div className="flex flex-col">
          <PriceTag>1.000 TOTAL</PriceTag>
          <BtnGroup>
            <MainButton href="/order/user/login">Realizar Pedido</MainButton>
          </BtnGroup>
        </div>
      </div>
    </DefaultContainer>
  );
}