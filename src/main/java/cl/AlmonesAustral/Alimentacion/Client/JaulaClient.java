package cl.AlmonesAustral.Alimentacion.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class JaulaClient {

    private static final Logger logger = LoggerFactory.getLogger(JaulaClient.class);
        private final WebClient webClient;

        public JaulaClient(WebClient.Builder webClientBuilder) {
            this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build(); 
        }
        
        public int obtenerDatosJaula(int id) {
        logger.info("Llamando a microservicio jaula para obtener datos de la jaula: {}", id);
        
        return webClient.get()
                .uri("/api/v1/jaula/{id}", id)
                .retrieve()
                .bodyToMono(int.class)
                .onErrorResume(e -> {
                    logger.error("Error al comunicarse con el microservicio de Jaula: {}", e.getMessage());
                    return Mono.just(-1); 
                })
                .block();
            }

}
