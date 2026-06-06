package cl.AlmonesAustral.Alimentacion.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CriaderoClient {

    private static final Logger logger = LoggerFactory.getLogger(CriaderoClient.class);
        private final WebClient webClient;

        public CriaderoClient(WebClient.Builder webClientBuilder) {
            this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build(); 
        }

        public int obtenerDatosCriadero(int id) {
        logger.info("Llamando a microservicio criadero para obtener datos del criadero: {}", id);
        
        return webClient.get()
                .uri("/api/v1/criadero/{id}", id)
                .retrieve()
                .bodyToMono(int.class)
                .onErrorResume(e -> {
                    logger.error("Error al comunicarse con el microservicio de Criadero: {}", e.getMessage());
                    return Mono.just(-1); 
                })
                .block();
    }
}