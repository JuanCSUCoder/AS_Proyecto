import { Product } from "@/model/Product";
import { AspectRatio, Button, Card, CardOverflow } from "@mui/joy";
import { PriceTag } from "./PriceTag";
import { Add } from "@mui/icons-material";
import Image from "next/image";

export function ProductCard({ product }: {
  product: Product
}) {
  return (
    <Card orientation="vertical" size="lg" variant="soft" sx={{ width: 320 }}>
      <h3 className="my-1">{product.name}</h3>
      {product.imageURL ? (
        <CardOverflow>
          <AspectRatio ratio={2}>
            <Image
              src={product.imageURL}
              alt="Product image"
              sizes="100%"
              fill
              style={{
                objectFit: "fill",
              }}
            />
          </AspectRatio>
        </CardOverflow>
      ) : null}
      <PriceTag>{product.price}</PriceTag>
      <p className="my-1">{product.descr}</p>

      <Button startDecorator={<Add />}>Agregar</Button>
    </Card>
  );
}