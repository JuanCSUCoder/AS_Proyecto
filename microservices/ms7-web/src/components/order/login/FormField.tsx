import { Input } from "@mui/joy";
import { HTMLInputTypeAttribute, ReactNode } from "react";

export function FormField({
  name,
  children,
  required,
  type,
  disabled
}: {
    name?: string,
    children?: ReactNode,
    required?: boolean,
    type?: HTMLInputTypeAttribute,
    disabled?: boolean
}) {
  return (
    <div className="my-3 w-full">
      {children && (
        <label>{children}</label>
      )}
      <Input required={required} disabled={disabled} name={name} type={type} />
    </div>
  );
}