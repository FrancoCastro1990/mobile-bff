package cl.duoc.bff.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

public class JwtIssuer {
    private static final String SECRET = "my-secret-key-123456789012345678901234567890";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    public static String generateToken(String username, String role){
        return JWT.create()
                .withSubject(username)
                .withClaim("scope", role) // Spring Security mapea 'scope' a autoridad
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L*60*60))
                .sign(algorithm);
    }
}
