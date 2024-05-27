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
    setCart((prevCart) => {
      prevCart.cart.items?.push({
        order: prevCart.cart,
        product: item,
        quantity: 1,
      });
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