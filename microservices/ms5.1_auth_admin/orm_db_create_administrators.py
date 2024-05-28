import psycopg2
import time

# Esperar 5 segundos
time.sleep(5)

# Información de conexión
conn = psycopg2.connect(
    dbname='defaultdb',
    user='root',
    password='',
    host='cockroachdb',
    port='26257'
)

# Crear la tabla administrators
def create_tables():
    with conn.cursor() as cur:
        cur.execute("""
            CREATE TABLE IF NOT EXISTS administrators (
                id SERIAL PRIMARY KEY,
                username VARCHAR UNIQUE NOT NULL,
                password VARCHAR NOT NULL
            );
        """)
        conn.commit()

# Insertar administrador
def insert_admin():
    with conn.cursor() as cur:
        cur.execute("""
            INSERT INTO administrators (id, username, password)
            VALUES (1, 'root', '1234')
            ON CONFLICT DO NOTHING;
        """)
        conn.commit()

# Crear la tabla
create_tables()
print("Table 'administrators' created")

# Insertar administrador
insert_admin()
print("Admin 'root' inserted")

# Cerrar la conexión
conn.close()
