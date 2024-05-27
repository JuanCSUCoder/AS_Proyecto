import { CartList } from "@/components/cart/CartList";
import { PriceTag } from "@/components/products/PriceTag";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { Button } from "@mui/joy";
import Link from "next/link";

export default function CartPage() {
  return (
    <DefaultContainer>
      <h2>Lista de Compras</h2>
      <div className="flex flex-col md:flex-row justify-between items-center">
        <CartList />
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