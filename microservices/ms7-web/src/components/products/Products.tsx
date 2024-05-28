import { Product } from "@/model/Product"
import { ProductCard } from "./ProductCard";

export function Products({products}: {products: Product[]}) {
  // const products: Product[] = [
  //   {
  //     name: "Producto 1",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //     imageURL:
  //       "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Arduino_ftdi_chip-1.jpg/1200px-Arduino_ftdi_chip-1.jpg",
  //   },
  //   {
  //     name: "Producto 2",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 3",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 3",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 1",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 2",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 3",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 3",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 1",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 2",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 3",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 3",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 1",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 2",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 3",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  //   {
  //     name: "Producto 3",
  //     descr: "Descrripcion del producto 1",
  //     price: 1120.0,
  //   },
  // ];

  let idcount = 0;
  products.forEach(product => {
    product.id = idcount.toString();
    idcount++;
  });

  return (
    <div className="flex flex-row flex-wrap gap-3 items-center justify-center">
      {products.map((p, idx) => (
        <ProductCard key={idx} product={p} />
      ))}
    </div>
  )
}