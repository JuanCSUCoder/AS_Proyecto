"use client"

import { CartList } from "@/components/cart/CartList";
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
    total: 148.08,
    items: [
      {
        id: "1",
        quantity: 3,
        product: {
          id: "1",
          descr: "Descripcion producto",
          name: "Cocacola",
          price: 12.34,
        },
      },
      {
        id: "2",
        quantity: 1,
        product: {
          id: "2",
          descr: "Descripcion producto 2",
          name: "Arduino Uno",
          price: 12.34,
        },
      },
      {
        id: "3",
        quantity: 8,
        product: {
          id: "3",
          descr: "Descripcion producto 3",
          name: "Avena",
          price: 12.34,
        },
      },
    ],
  };

  return (
    <DefaultContainer>
      <h2>Seguimiento Pedido</h2>
      <p>
        <strong># de Orden:</strong> {order.id}
      </p>
      <p>
        <strong>Estado:</strong> {order.status}
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