package shop.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import shop.model.User;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // implementation follows https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/

    public static final String SECRET = "secret-key-to-generate-jwt";
    public static final long EXPIRATION_TIME = 315_360_000_000L; // 10 years in ms
    public static final String TOKEN_TYPE = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";

    private AuthenticationManager authManager;

    public JWTAuthenticationFilter(AuthenticationManager authManager) {
        this.authManager = authManager;
        setFilterProcessesUrl("/login"); // not required as the same path is registered by default
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            User credentials = new ObjectMapper().readValue(req.getInputStream(), User.class);
            Authentication token = new UsernamePasswordAuthenticationToken(credentials.getName(), credentials.getPasswordHash());
            return authManager.authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
        String token = JWT.create()
                .withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(AUTH_HEADER, TOKEN_TYPE + token);
    }
}