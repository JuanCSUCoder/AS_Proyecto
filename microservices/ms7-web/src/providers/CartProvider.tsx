"use client"

import { Order } from "@/model/Order";
import { Product } from "@/model/Product";
import React, { ReactNode, useState } from "react";

export interface CartState {
  cart: Order
}

type ItemHandler = (item: Product) => boolean;

export interface CartHandlers {
  cartState: CartState;
  addItem: ItemHandler;
  removeItem: ItemHandler;
}

export const CartContext = React.createContext<CartHandlers | undefined>(undefined);

export function CartProvider({ children }: { children: ReactNode }) {
  const [cartState, setCart] = useState<CartState>({
    cart: {
      total: 0.0,
      status: "CART",
      items: []
    }
  });

  const addItem: ItemHandler = (item) => {
    console.log("Adding item")
    setCart((prevCart) => {
      let newCart: CartState = JSON.parse(JSON.stringify(prevCart));
      const existingItem = newCart.cart.items?.findLast((i) => i.product?.id == item.id);

      if (existingItem && existingItem.quantity) {
        console.log("FOund")
        existingItem.quantity += 1;
      } else {
        console.log("Not found creating...");
        newCart.cart.items?.push({
          // order: newCart.cart, DONT ADD ORDER
          product: item,
          quantity: 1,
        });
      }
      return newCart;
    })
    return true;
  };
  
  const removeItem: ItemHandler = (item) => {
    setCart((prevCart) => {
      let newCart: CartState = JSON.parse(JSON.stringify(prevCart));
      const existingItem = newCart.cart.items?.findLast(
        (i) => i.product?.id == item.id
      );

      console.log("Trying to remove 1 of " + existingItem);

      if (existingItem && existingItem.quantity) {
        if (existingItem.quantity <= 1) {
          const index = newCart.cart.items?.indexOf(existingItem);
          console.log("Found for deleition at " + index);
          if (index != undefined && index > -1) {
            newCart.cart.items?.splice(index, 1);
          }
        } else {
          existingItem.quantity -= 1;
        }
      }

      return newCart;
    });
    return true;
  }

  return (
    <CartContext.Provider value={{
      cartState,
      addItem,
      removeItem
    }}>
      {children}
    </CartContext.Provider>
  )
}