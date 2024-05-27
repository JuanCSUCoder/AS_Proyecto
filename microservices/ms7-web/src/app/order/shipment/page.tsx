"use client"

import { FormBox } from "@/components/order/login/FormBox";
import { BtnGroup } from "@/components/utils/BtnGroup";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { MainButton } from "@/components/utils/MainButton";
import { APIProvider, Map, Marker, useMap, useMapsLibrary, useMarkerRef } from "@vis.gl/react-google-maps";
import { FormEventHandler, useEffect, useState } from "react";

export default function ShipmentPage() {
  const map = useMap();
  const [markerRef, marker] = useMarkerRef();
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (!map) return;

    

    // here you can interact with the imperative maps API
  }, [map]);


  const precioTransporte = 10000;
  const distancia = 2.3;

  const onSubmit: FormEventHandler<HTMLFormElement> = (_e) => {
    setLoading(true);
  }

  return (
    <DefaultContainer>
      <APIProvider apiKey={process.env.API_KEY as string}>
        <Map
          style={{ width: "80vw", height: "50vh" }}
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
          <MainButton type="submit" loading={loading}>Realizar el Pago</MainButton>
        </BtnGroup>
      </FormBox>
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
