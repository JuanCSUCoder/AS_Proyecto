"use client"

import { FormBox } from "@/components/order/login/FormBox";
import { FormField } from "@/components/order/login/FormField";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { extractFormObject } from "@/utils/form";
import { useRouter } from "next/navigation";
import { FormEventHandler } from "react";

export default function AdminLoginPage() {
  const router = useRouter();

  const onSubmit: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();

    const formv = extractFormObject(e);

    fetch("http://localhost:5005/loginAdmin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(formv),
      credentials: "include"
    }).then(res => res.json())
      .then(res => {
        if (res.token) {
          localStorage.setItem("app_token", res.token);
          router.replace("/admin/dashboard");
        } else {
          alert("Credenciales Inválidas")
        }
      }).catch(() => {
        alert("Credenciales Inválidas")
      })
  }

  return (
    <DefaultContainer>
      <h2>Inicio de Sesión de Administrador</h2>
      <FormBox onSubmit={onSubmit}>
        <FormField name="username" required>Usuario:</FormField>
        <FormField name="password" required type="password">Contraseña:</FormField>
        <BtnGroup>
          <MainButton type="submit" href="/admin/dashboard">Iniciar Sesión</MainButton>
        </BtnGroup>
      </FormBox>
    </DefaultContainer>
  );
}