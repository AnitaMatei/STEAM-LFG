package com.steamlfg.configuration;


import com.steamlfg.model.handler.CustomAuthenticationSuccessHandler;
import com.steamlfg.service.CustomAuthenticationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class OpenIdLoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    CustomAuthenticationUserDetailsService customAuthenticationUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .openidLogin()
                .loginPage("/login").permitAll()
                .successHandler(customAuthenticationSuccessHandler)
                .authenticationUserDetailsService(customAuthenticationUserDetailsService)
                .and()
                .formLogin().disable();
    }


}
