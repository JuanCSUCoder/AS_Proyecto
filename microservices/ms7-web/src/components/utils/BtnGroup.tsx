import { Stack } from "@mui/joy";
import { ReactNode } from "react";

export function BtnGroup({children}:{children: ReactNode}) {
  return (
    <div className="my-5">
      <div className="w-full flex flex-row justify-start items-baseline gap-3 flex-wrap">
        {children}
      </div>
    </div>
  );
}