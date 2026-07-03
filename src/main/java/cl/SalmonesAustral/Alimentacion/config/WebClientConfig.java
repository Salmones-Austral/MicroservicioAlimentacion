package cl.SalmonesAustral.Alimentacion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${url.microservicio.criaderos}")
    private String criaderosUrl;

    @Value("${url.microservicio.jaula}")
    private String jaulaUrl;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
    @Bean
    public WebClient criaderosWebClient(WebClient.Builder builder) {
        return builder.baseUrl(criaderosUrl).build();
    }
    @Bean
    public WebClient jaulasWebClient(WebClient.Builder builder) {
        return builder.baseUrl(jaulaUrl).build();
    }

}
