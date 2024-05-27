"use client"

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

  }

  return (
    <DefaultContainer>
      <div className="flex w-full flex-col md:flex-row">
        <div className="flex w-full md:w-2/3 flex-col">
          <APIProvider apiKey={process.env.API_KEY as string}>
            <Map
              style={{ width: "100%", height: "50vh" }}
              defaultCenter={{ lat: 4.649189, lng: -74.103447 }}
              defaultZoom={15}
              gestureHandling={"greedy"}
              disableDefaultUI={true}
            >
              <Marker
                ref={markerRef}
                position={{ lat: 4.649189, lng: -74.103447 }}
              />
              <Directions />
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
              <MainButton type="submit" loading={loading}>
                Realizar el Pago
              </MainButton>
            </BtnGroup>
          </FormBox>
        </div>
      </div>
    </DefaultContainer>
  );
}

function Directions() {
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
      origin: "Cr 58C #147-81, Bogotá, Colombia",
      destination: "Pontificia Universidad Javeriana, Bogota Colombia",
      travelMode: google.maps.TravelMode.DRIVING,
      provideRouteAlternatives: true,
    }).then((res) => {
      dirRender.setDirections(res);
      setRoutes(res.routes);
    })
  }, [dirServ, dirRender]);

  return null;
}
