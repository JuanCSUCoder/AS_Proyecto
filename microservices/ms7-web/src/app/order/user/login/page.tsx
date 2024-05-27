"use client"

import { FormBox } from "@/components/order/login/FormBox";
import { FormField } from "@/components/order/login/FormField";
import { LoginForm } from "@/components/order/login/LoginForm";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { Input } from "@mui/joy";
import { redirect } from "next/navigation";
import { FormEventHandler } from "react";

export default function LoginPage() {
  const test = {
    api: "http://localhost:5006",
    callback: "http://localhost:3000/order/user/verify",
  };

  const onSubmit: FormEventHandler<HTMLFormElement> = (event) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const formJson = Object.fromEntries((formData as any).entries());
    // alert(JSON.stringify(formJson));
    window.location.href = `${test.api}/login/?idp=${encodeURIComponent(formJson.idp)}&&callback=${encodeURIComponent(test.callback)}`;
  }

  return (
    <DefaultContainer>
      <h2>Inicio de Sesión</h2>
      <FormBox onSubmit={onSubmit}>
        <FormField name="idp" required>
          Proveedor de Identidad SOLID:
        </FormField>
        <BtnGroup>
          <MainButton type="submit">
            Ir al Proveedor de Identidad
          </MainButton>
        </BtnGroup>
      </FormBox>
    </DefaultContainer>
  );
}