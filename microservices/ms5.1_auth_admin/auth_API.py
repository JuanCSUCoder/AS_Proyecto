import psycopg2
import jwt
from flask import Flask, request, jsonify
import time

# Clave secreta para firmar el token JWT
SECRET_KEY = "claveeeee_secretaaaa"

app = Flask(__name__)

# Función para establecer la conexión a la base de datos
def get_db_connection():
    return psycopg2.connect(
        dbname='defaultdb',
        user='root',
        password='',
        host='cockroachdb',
        port='26257'
    )


# Endpoint para la autenticación y la generación del token JWT
@app.route('/loginAdmin', methods=['POST'])
def loginAdmin():
    data = request.get_json()
    username = data.get('username')
    password = data.get('password')

    if not username or not password:
        return jsonify({'error': 'Nombre de usuario y contraseña requeridos'}), 400

    conn = get_db_connection()
    with conn.cursor() as cur:
        cur.execute("SELECT password FROM administrators WHERE username = %s", (username,))
        stored_password = cur.fetchone()

    if stored_password and password == stored_password[0]:
        token = jwt.encode({'username': username}, SECRET_KEY, algorithm='HS256')
        return jsonify({'token': token}), 200
    else:
        return jsonify({'error': 'Credenciales inválidas'}), 401

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5005)
