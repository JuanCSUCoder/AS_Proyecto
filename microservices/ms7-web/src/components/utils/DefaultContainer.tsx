import { ReactNode } from "react";

export function DefaultContainer({children}:{children: ReactNode}) {
  return (
    <main className="flex w-full flex-col items-center justify-start">
      {children}
    </main>
  )
}