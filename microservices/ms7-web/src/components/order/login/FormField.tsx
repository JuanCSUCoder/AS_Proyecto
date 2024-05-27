import { Input } from "@mui/joy";
import { ReactNode } from "react";

export function FormField({
  name,
  children,
  required,
  disabled
}: {
    name?: string,
    children?: ReactNode,
    required?: boolean,
    disabled?: boolean
}) {
  return (
    <div className="my-3 w-full">
      {children && (
        <label>{children}</label>
      )}
      <Input required={required} disabled={disabled} name={name} />
    </div>
  );
}