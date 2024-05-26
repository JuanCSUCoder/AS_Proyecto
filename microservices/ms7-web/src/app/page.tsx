import { NavBar } from "@/components/NavBar";
import { Products } from "@/components/Products";
import Image from "next/image";

export default function Home() {
  return (
    <main className="flex w-full flex-col items-center justify-start">
      <Products />
    </main>
  );
}
