package com.example.ebeautycentar.config;


//import com.ey.springboot3security.service.UserInfoDetails;
import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.dto.UserInfoDetails;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.service.JwtService;

import com.example.ebeautycentar.service.KorisnikService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final KorisnikService userDetailsService;
    private final JwtService jwtService;
    private final KorisnikService korisnikService;

    @Autowired
    public JwtAuthFilter(KorisnikService userDetailsService, JwtService jwtService, KorisnikService korisnikService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.korisnikService = korisnikService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = korisnikService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
