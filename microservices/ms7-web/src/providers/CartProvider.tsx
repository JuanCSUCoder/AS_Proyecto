"use client"

import { Order } from "@/model/Order";
import React, { ReactNode, useState } from "react";

export interface CartState {
  cart: Order
}

export interface CartHandlers {
  cartState: CartState;
  setCart?: React.Dispatch<React.SetStateAction<CartState>>;
}

export const CartContext = React.createContext<CartHandlers | undefined>(undefined);

export function CartProvider({ children }: { children: ReactNode }) {
  const [cartState, setCart] = useState<CartState>({
    cart: {

    }
  })

  return (
    <CartContext.Provider value={{
      cartState: cartState,
      setCart: setCart
    }}>
      {children}
    </CartContext.Provider>
  )
}