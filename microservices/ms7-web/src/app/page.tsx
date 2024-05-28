import { NavBar } from "@/components/NavBar";
import { Products } from "@/components/products/Products";
import Image from "next/image";
import { useEffect } from "react";

export default function Home() {
  useEffect(() => {
    try {
      fetch("http://localhost:5004/recomendaciones_productos", {
        method: "POST",
        body: JSON.stringify({
          user_id: ""
        })
      });
    } catch (error) {
      
    }
  }, []);

  return (
    <main className="flex w-full flex-col items-center justify-start">
      <Products />
    </main>
  );
}
