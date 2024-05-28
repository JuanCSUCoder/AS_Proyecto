"use client"

import { FormBox } from "@/components/order/login/FormBox";
import { FormField } from "@/components/order/login/FormField";
import { PriceTag } from "@/components/products/PriceTag";
import { ProductBtns } from "@/components/products/ProductBtns";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { Product } from "@/model/Product";
import { Review } from "@/model/Review";
import { Add, Delete } from "@mui/icons-material";
import { Button } from "@mui/joy";
import Image from "next/image";
import { useEffect, useState } from "react";

export default function ProductDetailsPage({ params }: {
  params: {id: string}
}) {
  // const product: Product = {
  //   id: params.id,
  //   name: "Arduino Uno",
  //   descr:
  //     "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nihil unde cupiditate accusamus amet corrupti vel delectus nesciunt dolores a quaerat eligendi esse, aspernatur laudantium error. Porro nam dignissimos in fugit esse earum libero labore aliquid sapiente! Fugiat, consectetur aperiam assumenda aspernatur aliquid laudantium ipsa cum, officiis eveniet eum explicabo maiores obcaecati nisi voluptas tempora eius ullam qui non culpa atque ex rem doloremque accusantium hic! Inventore vel officia odit quam animi deleniti obcaecati, dolorem aperiam voluptates dolores repellat non blanditiis repudiandae. Maiores labore voluptatum optio nostrum corrupti magni hic reprehenderit deleniti nam provident est, vero officia, ipsam veritatis delectus numquam.",
  //   price: 12.34,
  //   imageURL:
  //     "https://electronilab.co/wp-content/uploads/2013/04/Arduino-Uno-R3-Compatible-1.jpg",
  // };

  const [product, setProd] = useState<Product | undefined>(undefined);
  const [scores, setScores] = useState<Review[]>([]);

  useEffect(() => {
    fetch("http://localhost:5014/products/" + params.id)
      .then(res => res.json())
      .then(res => setProd(res));
    
    fetch("http://localhost:5013/scores?prodId=" + params.id)
      .then(res => res.json())
      .then(res => setScores(res));
  }, [params.id])

  if (!product) return (<p>Cargando ...</p>);

  return (
    <DefaultContainer>
      <h2>{product.name}</h2>
      <div className="flex w-full flex-col md:flex-row">
        <div className="flex w-full flex-col">
          {product.imageURL && (
            <div className="w-full h-64 relative">
              <Image
                src={product.imageURL}
                sizes="100vw"
                fill
                objectFit="contain"
                alt="Imagen del producto"
              />
            </div>
          )}
          <p>{product.descr}</p>
        </div>
        <div className="flex w-full flex-col items-end md:mt-20">
          <PriceTag>{product.price}</PriceTag>
          <ProductBtns product={product} />
          <FormBox onSubmit={(e) => {
            e.preventDefault();
          }}>
            <FormField name="comment">Comentario</FormField>
            <FormField name="score" type="number">Puntuación 0 a 5:</FormField>
            <MainButton type="submit">Enviar Comentario</MainButton>
          </FormBox>
          <div>

          </div>
        </div>
      </div>
    </DefaultContainer>
  );
}