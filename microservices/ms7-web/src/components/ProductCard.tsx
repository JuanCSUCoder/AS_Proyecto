import { Product } from "@/model/Product";
import { Button, Card } from "@mui/joy";
import { PriceTag } from "./PriceTag";

export function ProductCard({ product }: {
  product: Product
}) {
  return (
    <Card
      orientation="vertical"
      size="lg"
      variant="soft"
    >
      <h3 className="my-1">{product.name}</h3>
      <PriceTag>{ product.price }</PriceTag>
      <p className="my-1">
        {product.desc}
      </p>

      <Button>Agregar</Button>
    </Card>
  )
}