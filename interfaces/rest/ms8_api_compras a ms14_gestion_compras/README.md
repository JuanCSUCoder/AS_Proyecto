# Interfaces de Gestión de Compras

## Registrar Orden

**Endpoint:** POST /order

### Cuerpo de la Petición

```json
{
  "userId": "UUID",
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

**Endpoint:** POST /payorder/{uuid}

### Cuerpo de Solicitud

```json
{
  "paid": true
}
```

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
