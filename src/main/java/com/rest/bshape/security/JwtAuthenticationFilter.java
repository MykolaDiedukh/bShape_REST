package com.rest.bshape.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// glasa do generowania tokenów po poprawnym zalogowanu
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setUsernameParameter("email"); // loguje sie po emailu , default username

    }

}
