import { Button, Input } from "@mui/joy";
import { FormField } from "./FormField";
import Link from "next/link";

export function LoginForm() {
  return (
    <form className="w-full flex flex-col items-center justify-center">
      <FormField name="Proveedor de Identidad SOLID" />
      <Link href="/order/login">
        <Button size="lg" variant="soft" color="success">
          Ir al IdP
        </Button>
      </Link>
    </form>
  );
}