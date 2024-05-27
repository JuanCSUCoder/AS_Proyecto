"use client"

import { FormBox } from "@/components/order/login/FormBox";
import { FormField } from "@/components/order/login/FormField";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";

export default function VerifyDataPage() {
  const onSubmit = () => {

  }

  return (
    <DefaultContainer>
      <h2>Verifica tu información</h2>
      <FormBox onSubmit={onSubmit}>
        <div className="flex flex-col md:flex-row gap-9 w-full">
          <div className="flex flex-col w-full md:w-1/2">
            <h3>Información Personal</h3>
            <FormField required name="name">
              Nombre:
            </FormField>
            <FormField type="email" required name="email">
              Email:
            </FormField>
          </div>

          <div className="flex flex-col w-full md:w-1/2">
            <h3>Información de Entrega</h3>
            <FormField required name="address">
              Dirección:
            </FormField>
            <FormField required name="city">
              Ciudad:
            </FormField>
            <FormField required name="lat">
              Latitud:
            </FormField>
            <FormField required name="lng">
              Longitud:
            </FormField>
          </div>
        </div>

        <h3>Información de Pago</h3>
        <FormField required type="number" name="cardNumber">
          Número de la Tarjeta:
        </FormField>
        <FormField required type="number" name="cvv">
          CVV:
        </FormField>
        <BtnGroup>
          <MainButton href="/order/shipment">Confirmar Datos</MainButton>
        </BtnGroup>
      </FormBox>
    </DefaultContainer>
  );
}