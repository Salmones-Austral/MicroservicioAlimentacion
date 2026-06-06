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
    public List<Object> obtenerJaulasPorNombre(String nombre) {
        logger.info("Llamando a microservicio jaula para obtener datos por nombre: {}", nombre);
        return webClient.get()
                .uri("/{nombre}", nombre)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }
}
