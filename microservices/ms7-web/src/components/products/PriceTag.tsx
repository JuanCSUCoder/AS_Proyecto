import { ReactNode } from "react";

export function PriceTag({ children }: {
  children: ReactNode
}) {
  return (
    <span className="text-2xl font-bold  text-gray-600">$ {children}</span>
  );
}