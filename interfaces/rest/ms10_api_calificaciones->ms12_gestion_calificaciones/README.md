# Endpoints

## Obtener Calificaciones

**Endpoint:** GET /scores

### Cuerpo de la Respuesta

```json
[
  {
    "productId": "UUID",
    "userId": "UUID",
    "score": 4, // Numero de estrellas de 1-5
    "comment": "Comentario"
  },
  {
    "productId": "UUID",
    "userId": "UUID",
    "score": 4, // Numero de estrellas de 1-5
    "comment": "Comentario"
  },
  {
    "productId": "UUID",
    "userId": "UUID",
    "score": 4, // Numero de estrellas de 1-5
    "comment": "Comentario"
  },
  {
    "productId": "UUID",
    "userId": "UUID",
    "score": 4, // Numero de estrellas de 1-5
    "comment": "Comentario"
  }
]
```

## Registrar Calificación de un producto

**Endpoint:** POST /score

### Cuerpo de la Petición

```json
{
  "productId": "UUID",
  "userId": "UUID",
  "score": 4, // Numero de estrellas de 1-5
  "comment": "Comentario"
}
```
