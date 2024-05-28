"use client"

import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { useSearchParams } from "next/navigation";

export default function ConfirmPage() {
  const searchParams = useSearchParams();

  const id = searchParams.get("id");

  return (
    <DefaultContainer>
      <p>
        Su pedido a quedado confirmado con el código de orden
        {' ' + id}
      </p>
    </DefaultContainer>
  );
}
