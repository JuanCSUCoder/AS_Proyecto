"use client"

import { useUser } from "@/app/hooks/useUser";
import { FormBox } from "@/components/order/login/FormBox";
import { FormField } from "@/components/order/login/FormField";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { Pod } from "@/model/Pod";
import { extractFormObject } from "@/utils/form";
import { useRouter } from "next/navigation";
import { FormEventHandler, useEffect, useState } from "react";

interface FormStructure {
  address: string;
  cardNumber: string;
  city: string;
  cvv: string;
  email: string;
  lat: string;
  lng: string;
  name: string;
}

export default function VerifyDataPage() {
  const [btnDisabled, setBtnDisabled] = useState(true);
  const [loading, setLoading] = useState(false);
  const router = useRouter();
  const pod = useUser(() => {
    setBtnDisabled(false);
  }) || {};

  

  const mapToPod: (formv: FormStructure) => Pod = (formv) => {
    let podv: Pod = {
      name: formv.name,
      email: formv.email,
      location: {
        description: {
          address: formv.address,
          city: formv.city,
          countryCode: "COL",
        },
        coordinates: {
          lat: +formv.lat,
          lng: +formv.lng
        }
      },
      private: {
        cardNumber: formv.cardNumber,
        cvv: +formv.cvv,
      }
    };

    return podv;
  }

  const onSubmit: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();
    setLoading(true);

    const formv = extractFormObject(e) as FormStructure;
    console.log(formv);
    const podv = mapToPod(formv);
    console.log(podv);

    fetch(`${process.env.MS5_URL}/user`, {
      credentials: "include",
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(podv),
    }).then(() => {
      router.push("/order/shipment");
    });

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
          <MainButton disabled={btnDisabled} loading={loading}>Confirmar Datos</MainButton>
        </BtnGroup>
      </FormBox>
    </DefaultContainer>
  );
}