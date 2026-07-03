package cl.SalmonesAustral.Alimentacion.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Component
public class CriaderoClient {

    private static final Logger logger = LoggerFactory.getLogger(CriaderoClient.class);
    private final WebClient webClient;

    public CriaderoClient(@Qualifier("criaderoWebClient") WebClient webClient) {
        this.webClient = webClient; 
    }
    public List<Object> obtenerCriaderosPorNombre(String nombre) {
        logger.info("Llamando a microservicio criadero para obtener datos por nombre: {}", nombre);
        return webClient.get()
                .uri("/api/v1/criaderos/{nombre}", nombre) 
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }
}
