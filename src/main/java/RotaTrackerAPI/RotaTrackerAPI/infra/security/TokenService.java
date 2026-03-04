package RotaTrackerAPI.RotaTrackerAPI.infra.security;
import RotaTrackerAPI.RotaTrackerAPI.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")

    private String secret;

    public String gerarToken(User user){
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API RotaTracker") // emissor
                    .withSubject(user.getLogin()) // Sujeito
                    .withExpiresAt(dataExpiracao()) // Expiracao do token
                    .sign(algoritimo); // Assinatura
        }catch (JWTCreationException exception){
            throw new RuntimeException("ERRO ao gerar token JWT", exception);
        }
    }
    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));

    }
    public String getSubject(String tokenJWT){
        try{
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("API RotaTracker")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (Exception exception){
            throw new RuntimeException("Token JWT invalido ou expirado", exception);
        }
    }
}
