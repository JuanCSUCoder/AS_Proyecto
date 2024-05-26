import { CartList } from "@/components/cart/CartList";
import { Button } from "@mui/joy";

export default function CartPage() {
  return (
    <main className="flex w-full flex-col items-center justify-start">
      <h2>Lista</h2>
      <CartList />
      <div className="my-5">
        <Button size="lg" variant="soft" color="success">Realizar Pedido</Button>
      </div>
    </main>
  );
}