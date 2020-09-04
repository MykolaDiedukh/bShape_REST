package com.rest.bshape.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.util.stream.Collectors.joining;

// glasa do generowania tokenów po poprawnym zalogowanu
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setUsernameParameter("email"); // loguje sie po emailu , default username

    }

    // bedzie generowac token po poprawnym zalogowaniu
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        Claims claims = new DefaultClaims()
                .setSubject(((UserDetails) authResult.getPrincipal()).getUsername()) // castuje do interfejsu UserDetails aby pobrać nazwę uzytkownika ktory poprawnie sie zalogowal
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));  // jak długo token jest wazny - token jest ważny cały dzień


        claims.put("authorities", authResult.getAuthorities().stream()            /// pobrałem liste simpleAuhtority
                .map(GrantedAuthority::getAuthority) // metoda referencyjna
                .collect(joining(","))); // mapuje, pobieram auuthority i colectuje łącząc przy pomocy joning.


        String token = Jwts.builder()
                .setClaims(claims)  // setuje claimsy
                .signWith(SignatureAlgorithm.HS512, "mySecretKeyHehe")  // ustawiam sygnature haszowania + ustawiam klucz
                .compact(); // tworze token

    }
}
