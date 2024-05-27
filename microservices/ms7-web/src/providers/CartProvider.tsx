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

function removeItemOnce(arr: any[], value: any) {
  var index = arr.indexOf(value);
  if (index > -1) {
    arr.splice(index, 1);
  }
  return arr;
}

export function CartProvider({ children }: { children: ReactNode }) {
  const [cartState, setCart] = useState<CartState>({
    cart: {
      total: 0.0,
      status: "CART",
      items: []
    }
  });

  const addItem: ItemHandler = (item) => {
    setCart((prevCart) => {
      const existingItem = prevCart.cart.items?.findLast((i) => i.product?.id == item.id);

      if (existingItem && existingItem.quantity) {
        existingItem.quantity += 1;
      } else {
        prevCart.cart.items?.push({
          order: prevCart.cart,
          product: item,
          quantity: 1,
        });
      }
      return prevCart;
    })
    return true;
  };
  
  const removeItem: ItemHandler = (item) => {
    return false;
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