import { CartList } from "@/components/cart/CartList";
import { Button } from "@mui/joy";
import Link from "next/link";

export default function CartPage() {
  return (
    <main className="flex w-full flex-col items-center justify-start">
      <h2>Lista de Compras</h2>
      <CartList />
      <div className="my-5">
        <Link href="/order/login">
          <Button size="lg" variant="soft" color="success">
            Realizar Pedido
          </Button>
        </Link>
      </div>
    </main>
  );
}