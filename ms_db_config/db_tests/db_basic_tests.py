import psycopg2

# Información de conexión
conn = psycopg2.connect(
    dbname='defaultdb',
    user='root',
    password='',
    host='localhost',
    port='26257'
)

# Función para eliminar la tabla TestTable si existe
def drop_table():
    with conn.cursor() as cur:
        cur.execute("""
            DROP TABLE IF EXISTS TestTable;
        """)
        conn.commit()

# Función para crear la tabla TestTable
def create_table():
    with conn.cursor() as cur:
        cur.execute("""
            CREATE TABLE IF NOT EXISTS TestTable (
                id SERIAL PRIMARY KEY,
                name STRING NOT NULL
            );
        """)
        conn.commit()

# Función para insertar un nuevo registro
def insert_record(name):
    with conn.cursor() as cur:
        cur.execute("""
            INSERT INTO TestTable (name) VALUES (%s)
            RETURNING id;
        """, (name,))
        record_id = cur.fetchone()[0]
        conn.commit()
        return record_id

# Función para obtener todos los registros
def get_records():
    with conn.cursor() as cur:
        cur.execute("SELECT id, name FROM TestTable;")
        records = cur.fetchall()
        return records

# Eliminar la tabla TestTable si existe
drop_table()

# Crear la tabla TestTable
create_table()

# Insertar un nuevo registro
record_id = insert_record('Test Name')
print(f'Inserted record with ID: {record_id}')

# Obtener todos los registros
records = get_records()
for record in records:
    print("Fetched Record =", record)

# Cerrar la conexión
conn.close()
