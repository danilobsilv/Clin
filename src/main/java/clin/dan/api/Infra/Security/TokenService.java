package clin.dan.api.Infra.Security;

import clin.dan.api.Features.User.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserModel userModel){
        System.out.println(secret);
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("auth0")
                .withSubject(userModel.getLogin())
                .withClaim("id", userModel.getId())
                .withExpiresAt(dataExpiracaoToken())
                .sign(algorithm);
            }

        catch (JWTCreationException exception){
            throw new RuntimeException("Error generating JWT Token", exception);
        }
    }

    public String getSubject(String tokenJwt){
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        }
        catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid or Expired JWT Token");
        }
    }

    private Instant dataExpiracaoToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }
}
