package com.rest.bshape.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;

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
                .setSubject(((UserDetails)authResult.getPrincipal()).getUsername()) // castuje do interfejsu UserDetails aby pobrać nazwę uzytkownika ktory poprawnie sie zalogowal
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24 ));  // jak długo token jest wazny - token jest ważny cały dzień




    }
}
