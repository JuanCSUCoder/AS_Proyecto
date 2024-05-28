import java.nio.charset.StandardCharsets;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RabbitMQconsumer {

    @Incoming("cola-correo")
    public void consume(byte[] messageBytes) {
        String messageStr = new String(messageBytes, StandardCharsets.UTF_8);
        JsonObject message = new JsonObject(messageStr);
        System.out.println("Mensaje recibido: " + message);
        Mail.send_mail(message);

    }
}
