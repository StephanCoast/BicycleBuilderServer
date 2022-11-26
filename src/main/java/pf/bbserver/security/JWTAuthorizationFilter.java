package pf.bbserver.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        // validate HTTP authorization header
        String authHeader = req.getHeader(JWTAuthenticationFilter.AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith(JWTAuthenticationFilter.TOKEN_TYPE)) {
            chain.doFilter(req, res);
            return;
        }

        // extract the token from the HTTP authorization header
        String jwt = authHeader.substring(JWTAuthenticationFilter.TOKEN_TYPE.length()).trim();

        // validate JWT signature
        String claim = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.SECRET.getBytes())).build().verify(jwt).getSubject();
        if (claim != null) {
            Authentication auth = new UsernamePasswordAuthenticationToken(claim, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(req, res);
    }
}