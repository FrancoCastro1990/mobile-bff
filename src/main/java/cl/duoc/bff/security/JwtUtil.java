package cl.duoc.bff.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    // WARNING: In production do NOT hardcode keys. This is for demo only.
    private static final Key KEY = Keys.hmacShaKeyFor("VerySecretKeyForDemoPurposesDontUseInProd!".repeat(2).getBytes());
    public static String generateToken(String username, String role){
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000L*60*60*6)) // 6h
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
    public static io.jsonwebtoken.Claims parseToken(String token){
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
    }
}
