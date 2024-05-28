# Google AI Config y otros
import os
import requests
import json
import re
import time
from flask import Flask, request, jsonify
from flask_cors import CORS


app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": ["*"]}}) # Habilita CORS para la APP Flask. Necesario para que funcione adecuadamente con Angular/React


if __name__ == '__main__':
    # host='0.0.0.0' permite la conexion desde fuera del contenedor
    app.run(host='0.0.0.0', port=5004)
