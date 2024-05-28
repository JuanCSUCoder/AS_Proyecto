import { Input } from "@mui/joy";
import { HTMLInputTypeAttribute, ReactNode } from "react";

export function FormField({
  name,
  children,
  required,
  type,
  defaultValue,
  disabled
}: {
    name?: string,
    children?: ReactNode,
    required?: boolean,
    type?: HTMLInputTypeAttribute,
    defaultValue?: string | number,
    disabled?: boolean
}) {
  return (
    <div className="my-3 w-full">
      {children && (
        <label>{children}</label>
      )}
      <Input required={required} disabled={disabled} name={name} type={type} defaultValue={defaultValue} />
    </div>
  );
}