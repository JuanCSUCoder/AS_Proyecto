import javax.mail.*;
import javax.mail.internet.*;
import io.vertx.core.json.JsonObject;
import java.util.Properties;

public class Mail {

    static void send_mail(JsonObject contenido) {
        final String username = "malbororitual@gmail.com";
        final String password = "faiy wzau slgq wedn";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Extracción de la información del JSON
            JsonObject order = contenido.getJsonObject("order");
            String orderId = order.getString("id");
            String status = order.getString("status");
            double total = order.getDouble("total");
            JsonObject user = order.getJsonObject("user");
            String userId = user.getString("id");
            String userPod = user.getString("userPod");
            String providerUrl = user.getString("providerUrl");
            JsonObject item = order.getJsonArray("items").getJsonObject(0);
            String itemId = item.getString("id");
            int quantity = item.getInteger("quantity");
            JsonObject product = item.getJsonObject("product");
            String productId = product.getString("id");
            String productName = product.getString("name");
            String productDescr = product.getString("descr");
            String productImageURL = product.getString("imageURL");
            double productPrice = product.getDouble("price");
            String email = contenido.getString("email");

            // Crear el contenido del email en HTML
            String emailContent = "<h1>Detalles de la Orden</h1>" +
                    "<p><strong>Orden ID:</strong> " + orderId + "</p>" +
                    "<p><strong>Estado:</strong> " + status + "</p>" +
                    "<p><strong>Total:</strong> $" + total + "</p>" +
                    "<h2>Detalles del Usuario</h2>" +
                    "<p><strong>Usuario ID:</strong> " + userId + "</p>" +
                    "<p><strong>User Pod:</strong> " + userPod + "</p>" +
                    "<p><strong>Provider URL:</strong> " + providerUrl + "</p>" +
                    "<h2>Detalles del Producto</h2>" +
                    "<p><strong>Item ID:</strong> " + itemId + "</p>" +
                    "<p><strong>Cantidad:</strong> " + quantity + "</p>" +
                    "<p><strong>Producto ID:</strong> " + productId + "</p>" +
                    "<p><strong>Nombre del Producto:</strong> " + productName + "</p>" +
                    "<p><strong>Descripción del Producto:</strong> " + productDescr + "</p>" +
                    "<p><strong>Precio del Producto:</strong> $" + productPrice + "</p>" +
                    "<img src=\"" + productImageURL + "\" alt=\"" + productName + "\" />";

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("malbororitual@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)); // Usar el email del JSON
            message.setSubject("Envio Realizado Orden: " + orderId); // Usar el ID de la orden en el asunto

            // Configurar el contenido del mensaje como HTML
            message.setContent(emailContent, "text/html; charset=utf-8");

            Transport.send(message);

            // System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}