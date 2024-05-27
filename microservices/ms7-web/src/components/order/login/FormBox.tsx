import { Stack } from "@mui/joy";
import { FormEventHandler, ReactNode } from "react";

export function FormBox({ children, onSubmit }: {
  children: ReactNode,
  onSubmit: FormEventHandler<HTMLFormElement>
}) {
  return (
    <form className="w-full" onSubmit={onSubmit}>
      <Stack
        direction="column"
        justifyContent="flex-start"
        alignItems="center"
        spacing={3}
      >
        {children}
        </Stack>
    </form>
  )
}