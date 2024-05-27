import { ReactNode } from "react";

export function BtnGroup({children}:{children: ReactNode}) {
  return (
    <div className="my-5 flex flex-row gap-3">
      {children}
    </div>
  )
}