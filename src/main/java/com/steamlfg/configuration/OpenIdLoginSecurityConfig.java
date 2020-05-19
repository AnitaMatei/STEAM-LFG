package com.steamlfg.configuration;


import com.steamlfg.model.handler.CustomAuthenticationSuccessHandler;
import com.steamlfg.service.CustomAuthentificationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class OpenIdLoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    CustomAuthentificationUserDetailsService customAuthentificationUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/api/announcement/add").permitAll()
                .anyRequest().permitAll()
                .and()
                .openidLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/login?status=success", true)
                .successHandler(customAuthenticationSuccessHandler)
                .authenticationUserDetailsService(customAuthentificationUserDetailsService)
                .failureUrl("/login?status=failed")
                .and()
                .rememberMe().tokenValiditySeconds(2592000)
                .and()
                .csrf().disable();
    }


}
