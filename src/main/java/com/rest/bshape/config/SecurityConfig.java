package com.rest.bshape.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // włącza działanie adnotacji preautorajz
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Wykorzystuje wzorzec Builder, pierwszą funckję wywołuje w tej samej
        // linijce co zmienna lub typ, a każda kolejna funkcja w nowej.
        http.csrf()
                .ignoringAntMatchers("/**") // wyłącza nam secuirty na wszystkich endpointach restowych. Zeby cokolwiek dzialalo.
                .and()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // cały rest poiwnien byc bezstanowy czyli nie przechowywac info o poprzednich requestach

    }


}
