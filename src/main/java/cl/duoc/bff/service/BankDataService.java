package cl.duoc.bff.service;

import cl.duoc.bff.model.Account;
import cl.duoc.bff.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankDataService {

    private final WebClient webClient;
    private final JwtAuthService jwtAuthService;
    private final RestTemplate restTemplate;

    @Value("${backend.service.url}")
    private String backendUrl;

    public BankDataService(WebClient.Builder webClientBuilder, JwtAuthService jwtAuthService, RestTemplate restTemplate) {
        this.webClient = webClientBuilder.build();
        this.jwtAuthService = jwtAuthService;
        this.restTemplate = restTemplate;
    }

    public List<Account> getAllAccounts() {
        return jwtAuthService.getJwtToken()
                .flatMap(token -> webClient.get()
                        .uri(backendUrl + "/api/accounts")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<Account>>() {}))
                .block();
    }

    public Account getAccountById(String accountNumber) {
        return jwtAuthService.getJwtToken()
                .flatMap(token -> webClient.get()
                        .uri(backendUrl + "/api/accounts/" + accountNumber)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .retrieve()
                        .bodyToMono(Account.class))
                .block();
    }

    public List<Transaction> getTransactionsForAccount(String accountNumber) {
        return jwtAuthService.getJwtToken()
                .flatMap(token -> webClient.get()
                        .uri(backendUrl + "/api/transactions/" + accountNumber)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<Transaction>>() {}))
                .block();
    }
}
