import { Button, Input } from "@mui/joy";
import { FormField } from "./FormField";
import Link from "next/link";

export function LoginForm() {
  return (
    <form className="w-full flex flex-col items-center justify-center">
      <FormField name="Proveedor de Identidad SOLID" />
    </form>
  );
}