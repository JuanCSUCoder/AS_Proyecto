import psycopg2

# Información de conexión
conn = psycopg2.connect(
    dbname='defaultdb',
    user='root',
    password='',
    host='localhost',
    port='26257'
)

# Función para insertar datos de ejemplo en las tablas
def insert_data():
    with conn.cursor() as cur:
        # Insertar datos en la tabla User y capturar el ID generado
        cur.execute("""
            INSERT INTO "User" (userPod, providerUrl)
            VALUES ('user@example.com', 'http://example.com')
            RETURNING id;
        """)
        user_id = cur.fetchone()[0]  # Capturar el ID generado

        # Insertar datos en la tabla Product y capturar el ID generado
        cur.execute("""
            INSERT INTO Product (name, descr, imageURL, price, stock)
            VALUES ('Producto 1', 'Descripción del producto 1', 'imagen1.jpg', 10.99, 100)
            RETURNING id;
        """)
        product_id = cur.fetchone()[0]  # Capturar el ID generado
        
        # Insertar datos en la tabla Order y capturar el ID generado
        cur.execute("""
            INSERT INTO "Order" (userId, status, total)
            VALUES (%s, 'Pending', 10.00)
            RETURNING id;
        """, (user_id,))
        order_id = cur.fetchone()[0]  # Capturar el ID generado
        
        # Insertar datos en la tabla OrderItem utilizando los IDs capturados
        cur.execute("""
            INSERT INTO OrderItem (orderId, productId, quantity)
            VALUES (%s, %s, %s);
        """, (order_id, product_id, 2))
        
        # Insertar datos en la tabla Inventory utilizando el ID capturado
        cur.execute("""
            INSERT INTO Inventory (productId, location, quantity)
            VALUES (%s, 'Warehouse A', 50);
        """, (product_id,))
        
        # Insertar datos en la tabla Address utilizando el ID capturado
        cur.execute("""
            INSERT INTO Address (userId, street, city, state, country, zip)
            VALUES (%s, '123 Main St', 'City', 'State', 'Country', '12345');
        """, (user_id,))
        
        # Insertar datos en la tabla Review utilizando el ID capturado
        cur.execute("""
            INSERT INTO Review (score, productId)
            VALUES (5, %s);
        """, (product_id,))
        
        conn.commit()
        print("Data inserted successfully")

# Función para traer datos de ejemplo de las tablas
def fetch_data():
    with conn.cursor() as cur:
        # Obtener datos de la tabla User ordenados por ID
        cur.execute("SELECT * FROM \"User\" ORDER BY id;")
        users = cur.fetchall()
        print("Users:")
        for user in users:
            print(user)
        
        # Obtener datos de la tabla Product ordenados por ID
        cur.execute("SELECT * FROM Product ORDER BY id;")
        products = cur.fetchall()
        print("\nProducts:")
        for product in products:
            print(product)
        
        # Obtener datos de la tabla Order ordenados por ID
        cur.execute("SELECT * FROM \"Order\" ORDER BY id;")
        orders = cur.fetchall()
        print("\nOrders:")
        for order in orders:
            print(order)
        
        # Obtener datos de la tabla Inventory ordenados por ID
        cur.execute("SELECT * FROM Inventory ORDER BY id;")
        inventory = cur.fetchall()
        print("\nInventory:")
        for item in inventory:
            print(item)
        
        # Obtener datos de la tabla Address ordenados por ID
        cur.execute("SELECT * FROM Address ORDER BY id;")
        addresses = cur.fetchall()
        print("\nAddresses:")
        for address in addresses:
            print(address)
        
        # Obtener datos de la tabla Review ordenados por ID
        cur.execute("SELECT * FROM Review ORDER BY id;")
        reviews = cur.fetchall()
        print("\nReviews:")
        for review in reviews:
            print(review)

# Insertar datos de ejemplo
insert_data()

# Traer y mostrar datos
fetch_data()

# Cerrar la conexión
conn.close()
