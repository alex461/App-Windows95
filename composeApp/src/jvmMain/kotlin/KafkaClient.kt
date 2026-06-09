import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

class KafkaClient {

    // 1. Inicializamos el cliente de Ktor activando el soporte de WebSockets
    private val client = HttpClient {
        install(WebSockets)
    }

    /**
     * 2. Esta función abre la conexión y nos devuelve un 'Flow' de Kotlin.
     * Un Flow es como un río de datos: cada vez que llegue un mensaje de Kafka,
     * este Flow va a emitir el texto automáticamente a tu UI.
     */
    fun escucharMensajesDeKafka(): Flow<String> = flow {
        try {
            // Reemplaza '10.0.2.2' si pruebas en emulador de Android, o 'localhost' si es PC/Desktop
            val hostIp = "localhost"
            val puerto = 8080

            println("🔄 Intentando conectar al WebSocket de Ktor...")

            client.webSocket(host = hostIp, port = puerto, path = "/kafka-stream") {
                println("✅ ¡Conectados con éxito al flujo de Kafka!")

                // Mientras la conexión esté activa, nos quedamos esperando paquetes (Frames)
                while (isActive) {
                    val frame = incoming.receive()

                    // Si el paquete es de tipo Texto, lo extraemos y lo mandamos al Flow
                    if (frame is Frame.Text) {
                        val textoRecibido = frame.readText()
                        emit(textoRecibido) // 👈 Esto envía el mensaje a la UI
                    }
                }
            }
        } catch (e: Exception) {
            println("❌ Error en la conexión del WebSocket: ${e.message}")
            emit("Error de conexión: ${e.message}")
        }
    }

    // Recuerda cerrar el cliente cuando ya no lo uses para no dejar conexiones huérfanas
    fun cerrar() {
        client.close()
    }
}