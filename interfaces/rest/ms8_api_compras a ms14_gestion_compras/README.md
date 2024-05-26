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
  "id": "UUID Generado para la Orden",
  "userId": "UUID",
  "status": "PENDING",
  "products": [
    {
      "productId": "UUID",
      "quantity": 3
    },
    {
      "productId": "UUID",
      "quantity": 3
    },
    {
      "productId": "UUID",
      "quantity": 3
    },
    {
      "productId": "UUID",
      "quantity": 3
    },
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
  "id": "UUID Generado para la Orden",
  "userId": "UUID",
  "status": "PAID", // Se actualiza este campo
  "products": [
    {
      "productId": "UUID",
      "quantity": 3
    },
    {
      "productId": "UUID",
      "quantity": 3
    },
    {
      "productId": "UUID",
      "quantity": 3
    },
    {
      "productId": "UUID",
      "quantity": 3
    },
  ]
}
```
