package cl.duoc.bff.controller;

import cl.duoc.bff.security.JwtIssuer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody Map<String,String> body){
        String user = body.getOrDefault("user","demo");
        String channel = body.getOrDefault("channel","web").toLowerCase();
        String role = switch (channel) {
            case "mobile" -> "ROLE_MOBILE";
            case "atm" -> "ROLE_ATM";
            default -> "ROLE_WEB";
        };
        String token = JwtIssuer.generateToken(user, role);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
