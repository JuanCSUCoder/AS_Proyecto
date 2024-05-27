"use client"

import { DefaultContainer } from "@/components/utils/DefaultContainer";
import { Order } from "@/model/Order";
import { faTruck } from "@fortawesome/free-solid-svg-icons";
import { APIProvider, Map, Marker, useMap, useMarkerRef } from "@vis.gl/react-google-maps";

export default function OrderDetailsPage({ params }: { params: { id: string } }) {
  const map = useMap();
  const [markerRef, marker] = useMarkerRef();

  const order: Order = {
    id: "485629-458720-246524-246246",
    status: "ONDELIVERY",
    total: 37.02,
    items: [
      {
        id: "1",
        quantity: 3,
        product: {
          id: "1",
          descr: "Descripcion producto",
          name: "Cocacola",
          price: 12.34
        }
      }
    ]
  }

  return (
    <DefaultContainer>
      <h2>Seguimiento Pedido</h2>
      <p># de Orden: {order.id}</p>
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
    </DefaultContainer>
  );
}