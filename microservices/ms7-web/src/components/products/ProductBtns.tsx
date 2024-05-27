"use client"

import { Button } from "@mui/joy";
import { BtnGroup } from "../utils/BtnGroup";
import { Add, Info, Remove } from "@mui/icons-material";
import { Product } from "@/model/Product";
import { useContext } from "react";
import { CartContext } from "@/providers/CartProvider";
import Link from "next/link";

export function ProductBtns({ product }: { product: Product }) {
  const ctx = useContext(CartContext);
  
  return (
    <BtnGroup>
      <Button startDecorator={<Add />} onClick={() => ctx?.addItem(product)}>
        1
      </Button>
      <Button
        startDecorator={<Remove />}
        color="danger"
        onClick={() => ctx?.removeItem(product)}
      >
        1
      </Button>
      <Link href={`/product/${product.id}`}>
        <Button startDecorator={<Info />} color="neutral">
          Detalles
        </Button>
      </Link>
    </BtnGroup>
  );
}