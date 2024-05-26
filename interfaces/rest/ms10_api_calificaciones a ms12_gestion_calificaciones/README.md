# Endpoints

## Obtener Calificaciones

**Endpoint:** GET /scores?prodId=12

### Cuerpo de la Respuesta

```json
[
  {
    "id": "1",
    "score": 3,
    "product": {
      "id": "1",
      "name": "Producto",
      "descr": "Descripcion",
      "imageURL": "https://image.png",
      "price": 12.34
    },
    "user": {
      "id": "1",
      "userPod": "pod",
      "providerUrl": "prov"
    }
  },
  {
    "id": "d80c2c74-8806-46b4-9189-612b02f90613",
    "score": 4,
    "product": {
      "id": "1",
      "name": "Producto",
      "descr": "Descripcion",
      "imageURL": "https://image.png",
      "price": 12.34
    },
    "user": {
      "id": "1",
      "userPod": "pod",
      "providerUrl": "prov"
    }
  }
]
```

## Registrar Calificación de un producto

**Endpoint:** POST /score

### Cuerpo de la Petición

```json
{
  "score": 4,
  "product": {
    "id": "1"
  },
  "user": {
    "id": "1"
  }
}
```

### Ejemplo Respuesta

```json
{
  "id": "d80c2c74-8806-46b4-9189-612b02f90613",
  "score": 4,
  "product": {
    "id": "1",
    "name": "Producto",
    "descr": "Descripcion",
    "imageURL": "https://image.png",
    "price": 12.34
  },
  "user": {
    "id": "1",
    "userPod": "pod",
    "providerUrl": "prov"
  }
}
```