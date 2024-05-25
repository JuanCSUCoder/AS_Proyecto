import psycopg2

# Información de conexión
conn = psycopg2.connect(
    dbname='defaultdb',
    user='root',
    password='',
    host='cockroachdb',
    port='26257'
)

import time
# Esperar 5 segundos
time.sleep(5)

# Crear las tablas
def create_tables():
    with conn.cursor() as cur:
        cur.execute("""
            CREATE TABLE IF NOT EXISTS Product (
                id SERIAL PRIMARY KEY,
                name VARCHAR NOT NULL,
                descr VARCHAR,
                imageURL VARCHAR,
                price FLOAT,
                stock INT
            );
        """)
        
        cur.execute("""
            CREATE TABLE IF NOT EXISTS Order (
                id SERIAL PRIMARY KEY,
                userId SERIAL,
                status VARCHAR,
                total FLOAT
            );
        """)
        
        cur.execute("""
            CREATE TABLE IF NOT EXISTS OrderItem (
                id SERIAL PRIMARY KEY,
                orderId INT,
                productId SERIAL,
                quantity INT,
                FOREIGN KEY (orderId) REFERENCES Order (id),
                FOREIGN KEY (productId) REFERENCES Product (id)
            );
        """)
        
        cur.execute("""
            CREATE TABLE IF NOT EXISTS Inventory (
                id SERIAL PRIMARY KEY,
                productId SERIAL UNIQUE,
                location VARCHAR,
                quantity INT,
                FOREIGN KEY (productId) REFERENCES Product (id)
            );
        """)
        
        cur.execute("""
            CREATE TABLE IF NOT EXISTS User (
                id SERIAL PRIMARY KEY,
                userPod VARCHAR UNIQUE,
                providerUrl VARCHAR
            );
        """)
        
        cur.execute("""
            CREATE TABLE IF NOT EXISTS Address (
                id SERIAL PRIMARY KEY,
                userId INT UNIQUE,
                street VARCHAR,
                city VARCHAR,
                state VARCHAR,
                country VARCHAR,
                zip VARCHAR,
                FOREIGN KEY (userId) REFERENCES User (id)
            );
        """)
        
        cur.execute("""
            CREATE TABLE IF NOT EXISTS Review (
                id SERIAL PRIMARY KEY,
                score INT,
                productId SERIAL,
                FOREIGN KEY (productId) REFERENCES Product (id)
            );
        """)
        
        conn.commit()

# Crear las tablas
create_tables()
print("Tables created")

# Cerrar la conexión
conn.close()
