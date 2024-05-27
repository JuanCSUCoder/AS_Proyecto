# Ejemplo de uso
productos_comprados = '"Camiseta de algodón", "Pantalones vaqueros", "Zapatillas deportivas", "Gafas de sol", "Sombrero de paja", "Chaqueta de cuero", "Bolso de mano"'
productos_disponibles = '"Camiseta de algodón", "Pantalones vaqueros", "Zapatillas deportivas", "Jersey de lana", "Bufanda de lana", "Gorra de invierno", "Smartphone", "Portátil", "Tableta gráfica", "Impresora multifunción", "Disco duro externo", "Balón de fútbol", "Balón de baloncesto", "Balón de voleibol", "Raqueta de tenis", "Raqueta de bádminton", "Raqueta de squash", "Tabla de surf", "Tabla de snowboard", "Tabla de skate", "Bicicleta de montaña", "Bicicleta de carretera", "Bicicleta plegable", "Gimnasio en casa", "Cinta de correr", "Bicicleta estática", "Cámara digital", "Cámara réflex", "Cámara instantánea", "Consola de videojuegos", "Videojuego de acción", "Videojuego de aventura", "Auriculares inalámbricos", "Auriculares con cancelación de ruido", "Auriculares deportivos", "Smartwatch", "Reloj de pulsera", "Reloj de buceo", "Chocolate negro", "Chocolate con leche", "Chocolate blanco", "Café en grano", "Café molido", "Café instantáneo", "Aceite de oliva virgen extra", "Aceite de coco", "Aceite de aguacate", "Arroz integral", "Arroz basmati", "Arroz salvaje", "Quinoa", "Cuscús", "Trigo sarraceno", "Atún enlatado", "Sardinas en conserva", "Salmon enlatado", "Pasta de trigo integral", "Pasta de espelta", "Pasta de quinoa", "Frutos secos surtidos", "Almendras", "Nueces", "Anacardos", "Leche de almendras", "Leche de coco", "Leche de avena", "Yogur griego", "Yogur de soja", "Yogur de coco", "Huevos orgánicos", "Huevos camperos", "Huevos de codorniz", "Batido de proteínas", "Suplemento de proteínas", "Barrita energética", "Barrita de cereales", "Barrita de frutas", "Espinacas frescas", "Lechuga iceberg", "Rúcula", "Manzanas", "Peras", "Uvas", "Plátanos", "Kiwi", "Fresas", "Frambuesas", "Brócoli", "Coliflor", "Calabacín", "Pan integral", "Pan de centeno", "Pan sin gluten", "Miel cruda", "Miel de flores", "Miel de abeja", "Salmón fresco", "Trucha ahumada", "Bacalao salado", "Aceitunas", "Aceitunas rellenas", "Aceitunas aliñadas", "Salsa de tomate", "Salsa de pesto", "Salsa de curry", "Patatas", "Patatas dulces", "Boniatos", "Queso cheddar", "Queso mozzarella", "Queso parmesano", "Cerveza artesanal", "Cerveza de trigo", "Cerveza IPA", "Vino tinto reserva", "Vino blanco seco", "Vino rosado", "Galletas de avena", "Galletas de chocolate", "Galletas de mantequilla", "Barritas energéticas", "Barritas de chocolate", "Barritas de frutas", "Agua mineral", "Agua con gas", "Agua saborizada", "Jugo de naranja natural", "Jugo de manzana", "Jugo de piña", "Helado de vainilla", "Helado de chocolate", "Helado de fresa", "Pizzas congeladas", "Pizza margarita", "Pizza cuatro quesos", "Pizza pepperoni", "Sopa instantánea", "Sopa de pollo", "Sopa de tomate", "Sopa de verduras"'



# Google AI Config y otros
import os
import google.generativeai as genai
import requests
import json
import re
import time
from flask import Flask, request, jsonify
from flask_cors import CORS


# Traer la info del usuario y productos desde el MS de datos.
def fetch_and_save_json(user_id):
    # Define the URLs
    url_user_products = f'http://inventarios:8080/products/{user_id}/products'
    url_products = 'http://inventarios:8080/products'
    
    try:
        # Make the GET requests
        response_user_products = requests.get(url_user_products)
        response_products = requests.get(url_products)
        
        # Check if the requests were successful
        user_products_names = []
        products_names = []
        
        if response_user_products.status_code == 200:
            user_products_json = response_user_products.json()
            print(user_products_json)
            if user_products_json:
                user_products_names = [product['name'] for product in user_products_json]
            else:
                print(f"No products found for user {user_id}.")
        else:
            print(f"Failed to fetch data for user {user_id}. Status code: {response_user_products.status_code}")
        
        if response_products.status_code == 200:
            products_json = response_products.json()
            print(products_json)
            if products_json:
                products_names = [product['name'] for product in products_json]
            else:
                print("No products found.")
        else:
            print(f"Failed to fetch products data. Status code: {response_products.status_code}")
                
        return {
            "user_products_names": user_products_names,
            "products_names": products_names
        }
    
    except requests.RequestException as e:
        print(f"An error occurred: {e}")
        return None


# Configurar la API
docker_api_key = os.getenv("GOOGLE_API_KEY") # API KEY Como variable de entorno del dockerfile
if docker_api_key == None: docker_api_key = "AIzaSyC3mQeAQTPA6ZUeYbevCSx5_a5mG8rdup0" # Si no esta dockerizada la API
genai.configure(api_key=docker_api_key)

# Configurar el modelo
generation_config = {
    "temperature": 1,
    "top_p": 0.95,
    "top_k": 64,
    "max_output_tokens": 8192
}

safety_settings = [
    {
        "category": "HARM_CATEGORY_HARASSMENT",
        "threshold": "BLOCK_MEDIUM_AND_ABOVE"
    },
    {
        "category": "HARM_CATEGORY_HATE_SPEECH",
        "threshold": "BLOCK_MEDIUM_AND_ABOVE"
    },
    {
        "category": "HARM_CATEGORY_SEXUALLY_EXPLICIT",
        "threshold": "BLOCK_MEDIUM_AND_ABOVE"
    },
    {
        "category": "HARM_CATEGORY_DANGEROUS_CONTENT",
        "threshold": "BLOCK_MEDIUM_AND_ABOVE"
    },
]

model = genai.GenerativeModel(model_name="gemini-1.5-flash",
                              generation_config=generation_config,
                              safety_settings=safety_settings)


# Funciones para el manejo de respuestas

def obtener_recomendaciones(productos_comprados, productos_disponibles):
    FORMATO = '{"productos_recomendados": ["Producto1","Producto2","Producto3","Producto4","Producto5"]}'
    prompt = [
        f"Eres un asistente de recomendaciones para un sitio web de venta de productos. Deberás otorgar recomendaciones, basado en el historial de compras del usuario, y el inventario de productos disponibles. Brinda 5 recomendaciones acorde al historial de ordenes del usuario. Debes devolver un JSON. El formato debe ser: {FORMATO}",
        f"Productos ordenados previamente por el usuario: {productos_comprados}",
        f"Productos disponibles: {productos_disponibles}"
    ]
    
    # Generar el contenido
    response = model.generate_content(prompt)
    return response

def realizarPeticion_manejarRespuesta(productos_comprados, productos_disponibles):
    # Si la respuesta refleja algun tipo de alucinacion, realizar nuevamente la peticion
    while True:
        recomendacionesResponse = obtener_recomendaciones(productos_comprados, productos_disponibles)
        
        if recomendacionesResponse is None or not hasattr(recomendacionesResponse, 'text'):
            print("Error: la respuesta de la API fue None o no contiene texto. Reintentando...")
            time.sleep(1)  # Esperar un segundo antes de reintentar
            continue
        
        try:
            # Usar una expresión regular para extraer el contenido entre { y }
            match = re.search(r"\{.*\}", recomendacionesResponse.text, re.DOTALL)
            if match:
                json_response = match.group(0)
                response_json = json.loads(json_response)
                print("Recomendaciones almacenadas exitosamente: ", response_json)
                return response_json
                break
            else:
                print("Error: la respuesta no contiene un JSON válido. Reintentando...")
        except json.JSONDecodeError as e:
            print(f"Error al decodificar la respuesta JSON: {e}. Reintentando...")


# Realizar la peticion y el manejo de la respuesta
# Pruebas
# realizarPeticion_manejarRespuesta(productos_comprados, productos_disponibles)


app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": ["*"]}}) # Habilita CORS para la APP Flask. Necesario para que funcione adecuadamente con Angular/React

@app.route('/recomendaciones_productos', methods=['POST'])
def realizar_peticion():
        
    datos = request.json
    user_id = datos.get('user_id')
    
    # Fetch products based on user_id
    productos_data = fetch_and_save_json(user_id)
    
    if not productos_data:
        return jsonify({'error': 'Failed to fetch product data for the given user ID'}), 500
    
    productos_comprados = productos_data.get('user_products_names', [])
    productos_disponibles = productos_data.get('products_names', [])
    
    if productos_comprados and productos_disponibles:
        recomendaciones = realizarPeticion_manejarRespuesta(productos_comprados, productos_disponibles)
        return jsonify(recomendaciones)
    else:
        return jsonify({'error': 'No products found for the given user ID'}), 404

if __name__ == '__main__':
    # host='0.0.0.0' permite la conexion desde fuera del contenedor
    app.run(host='0.0.0.0', port=5004)
