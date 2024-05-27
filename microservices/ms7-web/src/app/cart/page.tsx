import { CartList } from "@/components/cart/CartList";
import { MainButton } from "@/components/utils/MainButton";
import { Button } from "@mui/joy";
import Link from "next/link";

export default function CartPage() {
  return (
    <main className="flex w-full flex-col items-center justify-start">
      <h2>Lista de Compras</h2>
      <CartList />
      <MainButton href="/order/user/login">Realizar Pedido</MainButton>
    </main>
  );
}