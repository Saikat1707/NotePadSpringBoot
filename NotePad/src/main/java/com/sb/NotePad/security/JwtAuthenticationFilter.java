package com.sb.NotePad.security;

import com.sb.NotePad.entities.User;
import com.sb.NotePad.repository.UserRepo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }


        String token = header.substring(7);

        try{

            if(!jwtService.isAccessToken(token)){
                filterChain.doFilter(request,response);
                return;
            }

            if(jwtService.isTokenExpired(token)){
                filterChain.doFilter(request,response);
                return;
            }

            String email = jwtService.extractEmail(token);
            if(email == null){
                filterChain.doFilter(request,response);
                return;
            }

            User user = userRepo.findByEmail(email)
                    .orElse(null);

            if(user == null){
                filterChain.doFilter(request,response);
                return;
            }

            if(SecurityContextHolder
                    .getContext()
                    .getAuthentication() == null){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);
            }
        }catch(ExpiredJwtException e){
            System.out.println("JWT expired");
        }catch(MalformedJwtException e){
            System.out.println("Invalid JWT");
        }catch(JwtException e){
            System.out.println("JWT error");
        }catch(Exception e){
            System.out.println("Authentication error");
        }

    }
}
