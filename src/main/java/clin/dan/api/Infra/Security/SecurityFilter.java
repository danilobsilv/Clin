package clin.dan.api.Infra.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var jwtToken = retrieveToken(request);

        var subject = tokenService.getSubject(jwtToken);



        filterChain.doFilter(request, response);

    }

    private String retrieveToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null){
            throw new RuntimeException("JWT Token not found in Auth Header.");
        }

        return authHeader.replace("Bearer", "").trim();
    }
}
