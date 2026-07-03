package cl.SalmonesAustral.Alimentacion.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Component
public class JaulaClient {

    private static final Logger logger = LoggerFactory.getLogger(JaulaClient.class);
    private final WebClient webClient;

    public JaulaClient(@Qualifier("jaulasWebClient") WebClient webClient) {
        this.webClient = webClient; 
    }
    public List<Object> obtenerJaulasPorCodigo(String codigo) {
        logger.info("Llamando a microservicio jaula para obtener los codigos: {}", codigo);
        return webClient.get()
                .uri("/api/v1/jaulas/codigo/{codigo}", codigo)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }
    public List<String> obtenerTodosLosCodigos() {
    return webClient.get()
            .uri("/api/v1/jaulas/codigos")
            .retrieve()
            .bodyToFlux(String.class) // Esperamos texto (los códigos)
            .collectList()
            .block();
}
}
