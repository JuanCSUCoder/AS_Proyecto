/* eslint-disable @next/next/no-sync-scripts */
"use client"

import { useDBUser } from "@/app/hooks/useDBUser";
import { usePodInfo } from "@/app/hooks/usePodInfo";
import { useUser } from "@/app/hooks/useUser";
import { FormBox } from "@/components/order/login/FormBox";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { Order } from "@/model/Order";
import { CartContext } from "@/providers/CartProvider";
import { APIProvider, Map, Marker, useMap, useMapsLibrary, useMarkerRef } from "@vis.gl/react-google-maps";
import { redirect, useRouter } from "next/navigation";
import { FormEventHandler, useContext, useEffect, useState } from "react";

export default function ShipmentPage() {
  const map = useMap();
  const [markerRef, marker] = useMarkerRef();
  const [loading, setLoading] = useState(false);
  const ctx = useContext(CartContext);
  const podUser = usePodInfo();
  const dbUser = useDBUser(podUser?.webId);

  const router = useRouter();

  useEffect(() => {
    if (!map) return;

    // here you can interact with the imperative maps API
  }, [map]);

  const [precioTransporte, setPrecio] = useState(0);
  const distancia = 2.3;

  const onSubmit: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();
    setLoading(true);

    let cart: Order = JSON.parse(JSON.stringify(ctx?.cartState.cart));
    
    cart.user = {
      id: dbUser?.id,
    };

    fetch("http://localhost:5010/gestionpedidos/api/orders/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(cart)
    }).then(res => res.json())
      .then((res: Order) => {
        router.replace("/order/confirm?id=" + res.id);
      }).catch((e) => {
        // router.replace("/order/confirm");
      });
  };

  const [disabled, setDisabled] = useState(true);
  const pod = useUser(() => {
    setDisabled(false);
  });

  const center = { lat: 4.6284928, lng: -74.0672394 };
  const destinationCenter = [4.6465453, -74.1272909];

  useEffect(() => {
    const datosPeticion = {
      latitudSalida: center.lat,
      longitudSalida: center.lng,
      latitudLlegada: pod?.location?.coordinates?.lat as number,
      longitudLlegada: pod?.location?.coordinates?.lat as number,
      cantidadPaquetes: ctx?.cartState.cart.items?.length as number,
    };

    try {
      fetch("http://localhost:5003/api/costo", {
        method: "POST",
        body: JSON.stringify(datosPeticion),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((res) => res.text())
        .then((val) => {
          setPrecio(+val);
          ctx?.setShipPrice(+val);
        });
    } catch (_) {
    }
  }, [ctx, pod])

  // defaultCenter={{ lat: 4.649189, lng: -74.103447 }}
  return (
    <DefaultContainer>
      <div className="flex w-full flex-col md:flex-row">
        <div className="flex w-full md:w-2/3 flex-col">
          <APIProvider apiKey={process.env.API_KEY as string}>
            <Map
              style={{ width: "100%", height: "50vh" }}
              defaultCenter={center}
              defaultZoom={15}
              gestureHandling={"greedy"}
              disableDefaultUI={true}
            >
              <Directions
                origin="Pontificia Universidad Javeriana, Bogotá, Colombia"
                destination={
                  pod?.location?.description?.address +
                  ", " +
                  pod?.location?.description?.city +
                  ", Colombia"
                }
              />
            </Map>
          </APIProvider>
        </div>
        <div className="flex w-full md:w-1/3 flex-col">
          <FormBox onSubmit={onSubmit}>
            <p>
              <strong>Precio Transporte: </strong>
              {precioTransporte}
            </p>
            <p>
              <strong>Distancia: </strong>
              {distancia}
            </p>
            <BtnGroup>
              <MainButton type="submit" loading={loading} disabled={disabled}>
                Realizar el Pago
              </MainButton>
            </BtnGroup>
          </FormBox>
        </div>
      </div>
    </DefaultContainer>
  );
}

function Directions({ origin, destination }: {
  origin: string,
  destination: string
}) {
  const map = useMap();
  const routesLibrary = useMapsLibrary("routes");
  const [dirServ, setDirServ] = useState<google.maps.DirectionsService>();
  const [dirRender, setDirRender] = useState<google.maps.DirectionsRenderer>();
  const [routes, setRoutes] = useState<google.maps.DirectionsRoute[]>([]);

  useEffect(() => {
    if (!routesLibrary || !map) return;
    setDirServ(new routesLibrary.DirectionsService());
    setDirRender(new routesLibrary.DirectionsRenderer({ map }));
  }, [routesLibrary, map]);

  useEffect(() => {
    if (!dirServ || !dirRender) return;

    dirServ.route({
      origin: origin,
      destination: destination,
      travelMode: google.maps.TravelMode.DRIVING,
      provideRouteAlternatives: true,
    }).then((res) => {
      dirRender.setDirections(res);
      setRoutes(res.routes);
    })
  }, [dirServ, dirRender, origin, destination]);

  return null;
}
