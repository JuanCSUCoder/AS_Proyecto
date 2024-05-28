import { Product } from "@/model/Product"
import { ProductCard } from "./ProductCard";

export function Products({products}: {products: Product[]}) {
  return (
    <div className="flex flex-row flex-wrap gap-3 items-center justify-center">
      {products.map((p, idx) => (
        <ProductCard key={idx} product={p} />
      ))}
    </div>
  )
}