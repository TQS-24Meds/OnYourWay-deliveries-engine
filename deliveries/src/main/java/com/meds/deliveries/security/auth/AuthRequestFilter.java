package com.meds.deliveries.security.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.meds.deliveries.service.SpringUserDetailsService;

@Component
public class AuthRequestFilter extends OncePerRequestFilter {

    private final SpringUserDetailsService springUserDetailsService;
    private final JWTTokenUtils jwtTokenUtils;

    @Autowired
    public AuthRequestFilter(SpringUserDetailsService springUserDetailsService, JWTTokenUtils jwtTokenUtils) {
        this.springUserDetailsService = springUserDetailsService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            jwtToken = requestTokenHeader.substring(7);

            try {
                username = jwtTokenUtils.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                throw new InvalidBearerTokenException("Unable to get JWT Token.");
            } catch (ExpiredJwtException e) {
                throw new InvalidBearerTokenException("JWT Token has expired.");
            } catch (SignatureException e) {
                throw new InvalidBearerTokenException("JWT validity cannot be asserted.");
            }

        }

        // Once we get the token validate it
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.springUserDetailsService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtils.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        chain.doFilter(request, response);

    }

}
