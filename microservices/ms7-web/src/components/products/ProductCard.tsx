"use client"

import { Product } from "@/model/Product";
import { AspectRatio, Button, Card, CardOverflow } from "@mui/joy";
import { PriceTag } from "./PriceTag";
import { Add, Delete, Info } from "@mui/icons-material";
import Image from "next/image";
import { BtnGroup } from "../utils/BtnGroup";
import Link from "next/link";
import { useContext } from "react";
import { CartContext } from "@/providers/CartProvider";

export function ProductCard({ product }: {
  product: Product
}) {
  const ctx = useContext(CartContext);

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

      <BtnGroup>
        <Button startDecorator={<Add />} onClick={() => ctx?.addItem(product)}>Agregar</Button>
        <Button startDecorator={<Delete />} color="danger" onClick={() => ctx?.removeItem(product)}>
          Eliminar
        </Button>
        <Link href={`/product/${product.id}`}>
          <Button startDecorator={<Info />} color="neutral">
            Detalles
          </Button>
        </Link>
      </BtnGroup>
    </Card>
  );
}