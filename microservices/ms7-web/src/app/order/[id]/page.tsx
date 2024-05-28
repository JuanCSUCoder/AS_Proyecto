"use client"

import { CartList } from "@/components/cart/CartList";
import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { Order } from "@/model/Order";
import { faTruck } from "@fortawesome/free-solid-svg-icons";
import { APIProvider, Map, Marker, useMap, useMarkerRef } from "@vis.gl/react-google-maps";
import { useEffect, useState } from "react";

export default function OrderDetailsPage({ params }: { params: { id: string } }) {
  const map = useMap();
  const [markerRef, marker] = useMarkerRef();

  const [order, setOrder] = useState<Order | undefined>(undefined);

  useEffect(() => {
    fetch("http://localhost:5010/gestionpedidos/api/orders/" + encodeURIComponent(params.id))
      .then(res => res.json())
      .then(res => setOrder(res));
  }, [params.id])

  if (!order) return (<p>Loading {params.id} ...</p>);

  return (
    <DefaultContainer>
      <h2>Seguimiento Pedido</h2>
      <p>
        <strong># de Orden:</strong> {order?.id}
      </p>
      <p>
        <strong>Estado:</strong> {order?.status}
      </p>
      <APIProvider apiKey={process.env.API_KEY as string}>
        <Map
          style={{ width: "100%", height: "40vh" }}
          defaultCenter={{ lat: 4.649189, lng: -74.103447 }}
          defaultZoom={15}
          gestureHandling={"greedy"}
          disableDefaultUI={true}
        >
          <Marker
            ref={markerRef}
            position={{ lat: 4.649189, lng: -74.103447 }}
            title={"Tu paquete"}
          />
        </Map>
      </APIProvider>
      <CartList order={order} readonly />
    </DefaultContainer>
  );
}