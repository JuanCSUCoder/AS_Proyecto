import { Stack } from "@mui/joy";
import { ReactNode } from "react";

export function BtnGroup({children}:{children: ReactNode}) {
  return (
    <Stack
      direction="row"
      justifyContent="flex-start"
      alignItems="baseline"
      spacing={1}
    >
      {children}
    </Stack>
  )
}