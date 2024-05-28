"use client"

import { Products } from "@/components/products/Products";
import { useEffect, useState } from "react";
import { useDBUser } from "./hooks/useDBUser";
import { usePodInfo } from "./hooks/usePodInfo";

export default function Home() {
  const podInfo = usePodInfo();
  const dbUser = useDBUser(podInfo?.webId);
  const [suggestion, setSuggestion] = useState("");

  useEffect(() => {
    fetch("http://localhost:5004/recomendaciones_productos", {
      method: "POST",
      body: JSON.stringify({
        user_id: dbUser?.id
      }),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((res) => res.text())
      .then(res => setSuggestion(res))
      .catch(err => console.error(err));
  }, [dbUser?.id]);

  return (
    <main className="flex w-full flex-col items-center justify-start">
      <p>{ suggestion }</p>
      <Products />
    </main>
  );
}
