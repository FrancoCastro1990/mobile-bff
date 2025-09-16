package cl.duoc.bff.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JwtAuthService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Value("${backend.service.url}")
    private String backendUrl;

    @Value("${backend.service.auth.username}")
    private String username;

    @Value("${backend.service.auth.password}")
    private String password;

    public JwtAuthService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = new ObjectMapper();
    }

    public Mono<String> getJwtToken() {
        return webClient.post()
                .uri(backendUrl + "/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password))
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    try {
                        JsonNode jsonNode = objectMapper.readTree(response);
                        return jsonNode.get("token").asText();
                    } catch (Exception e) {
                        throw new RuntimeException("Error parsing JWT token from backend response", e);
                    }
                });
    }
}