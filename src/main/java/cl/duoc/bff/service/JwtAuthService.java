package cl.duoc.bff.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JwtAuthService {

    private final WebClient webClient;

    @Value("${backend.service.url}")
    private String backendUrl;

    @Value("${backend.service.auth.username}")
    private String username;

    @Value("${backend.service.auth.password}")
    private String password;

    public JwtAuthService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<String> getJwtToken() {
        return webClient.post()
                .uri(backendUrl + "/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password))
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    // Assuming the response is a JSON with token field
                    // In a real implementation, you'd parse the JSON properly
                    if (response.contains("token")) {
                        return response.split("\"token\":\"")[1].split("\"")[0];
                    }
                    return response;
                });
    }
}