"use client"

import { FormField } from "@/components/order/login/FormField";
import { LoginForm } from "@/components/order/login/LoginForm";
import { MainButton } from "@/components/utils/MainButton";
import { Input } from "@mui/joy";
import { FormEventHandler } from "react";

export default function LoginPage() {
  const onSubmit: FormEventHandler<HTMLFormElement> = (event) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const formJson = Object.fromEntries((formData as any).entries());
    alert(JSON.stringify(formJson));
  }

  return (
    <main className="flex w-full flex-col items-center justify-start">
      <h2>Inicio de Sesión</h2>
      <form
        className="w-full flex flex-col items-center justify-center"
        onSubmit={onSubmit}
      >
        <FormField name="idp" required>
          Proveedor de Identidad SOLID:
        </FormField>
        <MainButton type="submit">Ir al Proveedor de Identidad</MainButton>
      </form>
    </main>
  );
}