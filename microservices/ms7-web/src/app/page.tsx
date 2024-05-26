import { NavBar } from "@/components/NavBar";
import { Products } from "@/components/Products";
import Image from "next/image";

export default function Home() {
  return (
    <main className="flex min-h-screen w-full flex-col items-center justify-start p-10">
      <Products />
    </main>
  );
}
