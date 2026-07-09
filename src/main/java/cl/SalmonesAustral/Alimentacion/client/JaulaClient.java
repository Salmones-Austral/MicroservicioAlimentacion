package cl.SalmonesAustral.Alimentacion.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;

@Component
public class JaulaClient {

    private static final Logger logger = LoggerFactory.getLogger(JaulaClient.class);
    private final WebClient webClient;

    public JaulaClient(@Qualifier("jaulasWebClient") WebClient webClient) {
        this.webClient = webClient; 
    }
    //para pedir datos a MS jaula
    public List<Object> listarJaulas() {
        logger.info("Llamando a microservicio jaula para obtener todas las jaulas registradas");
        return webClient.get()
                .uri("/api/v1/jaulas")
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
            }

    //para listar Codigos y estado de las jaulas
    public List<Map<String, Object>> listarEstadosHabilitacion() {
        logger.info("Llamando a microservicio Jaulas para obtener los estados de habilitacion");
        
        return webClient.get()
                //se usa exactamente el endpoint que se definio en MS jaula
                .uri("/api/v1/jaulas/listar-estados-habilitacion") 
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .block();
    }

    //Para actualizar el estado por medio del codigo de la jaula
    public Map<String, Object> cambiarEstadoHabilitacion(String codigo, Boolean habilitarAlimentacion) {
        logger.info("Llamando a microservicio Jaulas para cambiar habilitacion de: " + codigo);
        
        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                //se usa exactamente el endpoint que se definio en MS jaula
                .path("/api/v1/jaulas/codigo/{codigo}/habilitacion")
                .queryParam("habilitarAlimentacion", habilitarAlimentacion)
                .build(codigo))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();
    }
        
}
