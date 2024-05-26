# Interfaces de Gestión de Compras

## Registrar Orden

**Endpoint:** POST /order

### Cuerpo de la Petición

```json
{
  "user": {
    "id": "1"
  },
  "items": [
    {
      "quantity": 0,
      "product": {
        "id": "string"
      }
    }
  ]
}
```

### Cuerpo de la Respuesta

```json
{
  "id": "62fc4fe8-4a23-4793-bbf8-b24ba1b95496",
  "status": "PENDING",
  "total": 37.019999999999996,
  "user": {
    "id": "1",
    "userPod": "pod",
    "providerUrl": "prov"
  },
  "items": [
    {
      "id": "6ccd1674-eced-47ac-8ce2-adf38eaa13e3",
      "quantity": 3,
      "product": {
        "id": "1",
        "name": "Producto",
        "descr": "Descripcion",
        "imageURL": "https://image.png",
        "price": 12.34
      }
    }
  ]
}
```

## Registrar Pago de la Orden

**Endpoint:** POST /payorder?orderid={uuid}

### Cuerpo de Solicitud

No requiere cuerpo

### Cuerpo de Respuesta

```json
{
  "id": "62fc4fe8-4a23-4793-bbf8-b24ba1b95496",
  "status": "PAID",
  "total": 37.019999999999996,
  "user": {
    "id": "1",
    "userPod": "pod",
    "providerUrl": "prov"
  },
  "items": [
    {
      "id": "6ccd1674-eced-47ac-8ce2-adf38eaa13e3",
      "quantity": 3,
      "product": {
        "id": "1",
        "name": "Producto",
        "descr": "Descripcion",
        "imageURL": "https://image.png",
        "price": 12.34
      }
    }
  ]
}
```
