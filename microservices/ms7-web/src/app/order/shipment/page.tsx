"use client"

import { useUser } from "@/app/hooks/useUser";
import { FormBox } from "@/components/order/login/FormBox";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { APIProvider, Map, Marker, useMap, useMapsLibrary, useMarkerRef } from "@vis.gl/react-google-maps";
import { redirect, useRouter } from "next/navigation";
import { FormEventHandler, useEffect, useState } from "react";

export default function ShipmentPage() {
  const map = useMap();
  const [markerRef, marker] = useMarkerRef();
  const [loading, setLoading] = useState(false);

  const router = useRouter();

  useEffect(() => {
    if (!map) return;

    // here you can interact with the imperative maps API
  }, [map]);

  const precioTransporte = 10000;
  const distancia = 2.3;

  const onSubmit: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();
    setLoading(true);
    router.replace("/order/confirm");
  };

  const [disabled, setDisabled] = useState(true);
  const pod = useUser(() => {
    setDisabled(false);
  });

  const center = { lat: 4.6284928, lng: -74.0672394 };
  const destinationCenter = [4.6465453, -74.1272909];

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
              <Directions origin="Pontificia Universidad Javeriana, Bogotá, Colombia" destination={pod?.location?.description?.address + ", " + pod?.location?.description?.city + ", Colombia"} />
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
