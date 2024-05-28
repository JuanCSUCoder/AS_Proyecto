import { ReactNode, Suspense } from "react";

export default function LayoutConfirm({children}: {children: ReactNode}) {
  return (
    <Suspense>
      {children}
    </Suspense>
  )
}