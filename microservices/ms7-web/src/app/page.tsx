import { Products } from "@/components/Products";
import Image from "next/image";

export default function Home() {
  return (
    <main className="flex min-h-screen w-full flex-col items-center justify-start p-24">
      <h1>SuperStore - Tu tienda descentralizada</h1>
      <Products />
    </main>
  );
}
