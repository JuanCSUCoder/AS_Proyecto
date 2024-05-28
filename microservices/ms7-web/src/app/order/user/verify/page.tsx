"use client"

import { FormBox } from "@/components/order/login/FormBox";
import { FormField } from "@/components/order/login/FormField";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { Pod } from "@/model/Pod";
import { extractFormObject } from "@/utils/form";
import { FormEventHandler, useEffect, useState } from "react";

interface FormStructure {

}

export default function VerifyDataPage() {
  const [pod, setPod] = useState<Pod>({});
  const [btnDisabled, setBtnDisabled] = useState(true);

  useEffect(() => {
    setBtnDisabled(false);

    fetch(`${process.env.MS5_URL}/user`, {
      credentials: "include"
    })
      .then((res) => res.json())
      .then((data) => setPod(data));
  }, []);

  const mapToPod: (formv: FormStructure) => Pod = (formv) => {
    let podv: Pod = {};

    return podv;
  }

  const onSubmit: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();

    const formv = extractFormObject(e);
    console.log(formv);

    // href="/order/shipment"
  }

  return (
    <DefaultContainer>
      <h2>Verifica tu información</h2>
      <FormBox onSubmit={onSubmit}>
        <div className="flex flex-col md:flex-row gap-9 w-full">
          <div className="flex flex-col w-full md:w-1/2">
            <h3>Información Personal</h3>
            <FormField required name="name" defaultValue={pod.name}>
              Nombre:
            </FormField>
            <FormField type="email" required name="email" defaultValue={pod.email}>
              Email:
            </FormField>
          </div>

          <div className="flex flex-col w-full md:w-1/2">
            <h3>Información de Entrega</h3>
            <FormField required name="address" defaultValue={pod.location?.description?.address}>
              Dirección:
            </FormField>
            <FormField required name="city" defaultValue={pod.location?.description?.city}>
              Ciudad:
            </FormField>
            <FormField required name="lat" defaultValue={pod.location?.coordinates?.lat}>
              Latitud:
            </FormField>
            <FormField required name="lng" defaultValue={pod.location?.coordinates?.lng}>
              Longitud:
            </FormField>
          </div>
        </div>

        <h3>Información de Pago</h3>
        <FormField required type="number" name="cardNumber" defaultValue={pod.private?.cardNumber}>
          Número de la Tarjeta:
        </FormField>
        <FormField required type="password" name="cvv" defaultValue={pod.private?.cvv}>
          CVV:
        </FormField>
        <BtnGroup>
          <MainButton disabled={btnDisabled}>Confirmar Datos</MainButton>
        </BtnGroup>
      </FormBox>
    </DefaultContainer>
  );
}