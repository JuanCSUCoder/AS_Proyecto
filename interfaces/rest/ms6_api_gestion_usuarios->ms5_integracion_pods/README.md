# Endpoints

- Obtener datos de un usuario
  - **Endpoint:** GET /user?url=https%3A%2F%2Fjuancsucoder.solidcommunity.net%2F
  - **Cuerpo de la Respuesta:** respuesta_obtener.json
- Actualizar/Agregar datos a un usuario
  - **Endpoint:** PUT /user?url=https%3A%2F%2Fjuancsucoder.solidcommunity.net%2F
  - **Cuerpo de la Petición:** peticion_actualizar.json
- Verficar "Token" (esto aun no lo puedo definir en firme porque aun no se cual es ese token)
  - **Endpoint:** GET /verify?token=dbnfswkdnvfjnb&url=https%3A%2F%2Fjuancsucoder.solidcommunity.net%2F
  - Si la respuesta tiene estatus 200 el token es valido, si es estatus 400 es invalido
