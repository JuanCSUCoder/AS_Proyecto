"use client"

import { CartList } from "@/components/cart/CartList";
import { PriceTag } from "@/components/products/PriceTag";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { Order } from "@/model/Order";
import { CartContext } from "@/providers/CartProvider";
import { Button } from "@mui/joy";
import Link from "next/link";
import { useContext } from "react";

export default function CartPage() {
  const context = useContext(CartContext);
  const order = context ? context.cartState.cart : {
    total: 0.0,
    items: []
  };

  return (
    <DefaultContainer>
      <h2>Lista de Compras</h2>
      <div className="flex flex-col md:flex-row justify-between items-center">
        <CartList order={order} />
        <div className="flex flex-col">
          <PriceTag>{order.total} TOTAL</PriceTag>
          <BtnGroup>
            <MainButton href="/order/user/login" disabled={order.items?.length == 0}>Realizar Pedido</MainButton>
          </BtnGroup>
        </div>
      </div>
    </DefaultContainer>
  );
}