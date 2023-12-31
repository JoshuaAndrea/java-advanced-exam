package nl.inholland.exam.joshuaandrea.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {

    @Value("${api.secret.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{
            //Get the provided auth token
            String token = getBearerToken(request);

            if (token.equals(apiKey)) {
                filterChain.doFilter(request, response);
            }
            else {
                throw new AuthenticationException("Invalid authorisation token.");
            }
        }
        catch(AuthenticationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getMessage());
            response.getWriter().flush();
        }
    }

    private String getBearerToken(HttpServletRequest request) throws AuthenticationException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            //Returns the string that comes after "Bearer ": which is the token
            return authHeader.substring(7);
        }
        else{
            throw new AuthenticationException("No authorisation token provided.");
        }
    }
}
