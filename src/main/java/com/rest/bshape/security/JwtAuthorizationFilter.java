package com.rest.bshape.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    // sprawdzamy jakie user ma role i czy jest zalogowany
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        // rozparosywuje token
        Claims claims = Jwts.parser()
                .setSigningKey("mySecretKeyHehe") // ma być ten sam co w poprzednim filtrze!
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();

        //pobieram role po kluczu authorities ktory zadeklarowalem w poprzednim filtrze
        String authorities = claims.get("authorities", String.class);

        //tworze pusta liste
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        //sprawdzam czy jest rozne od nula,jestli jest to ma role ktora wrzucam do listy
        if (authorities != null) {
            // splituje strina po przecinku, wrzucam tablice do strumienia, mapuje i wrzucam do listy
            grantedAuthorityList = Arrays.stream(authorities.split(","))
                    .map(SimpleGrantedAuthority::new) // metoda referencyna
                    .collect(Collectors.toList());
        }
        String eMail = claims.getSubject();

        // napisanie ifa czy email istnieje jestli tak to wrzycenie do kontekstu security jestli nie to wrzucennie bledu
        if (eMail != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(eMail, null, grantedAuthorityList);
            SecurityContextHolder.getContext()
                    .setAuthentication(usernamePasswordAuthenticationToken);
        } else {
            response.setStatus(401);
        }

    }


}
