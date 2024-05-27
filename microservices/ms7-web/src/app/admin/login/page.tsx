"use client"

import { FormBox } from "@/components/order/login/FormBox";
import { FormField } from "@/components/order/login/FormField";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { FormEventHandler } from "react";

export default function AdminLoginPage() {
  const onSubmit: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();
  }

  return (
    <DefaultContainer>
      <h2>Inicio de Sesión de Administrador</h2>
      <FormBox onSubmit={onSubmit}>
        <FormField name="user" required>Usuario:</FormField>
        <FormField name="password" required type="password">Contraseña:</FormField>
        <BtnGroup>
          <MainButton type="submit" href="/admin/dashboard">Iniciar Sesión</MainButton>
        </BtnGroup>
      </FormBox>
    </DefaultContainer>
  );
}