package com.youcode.wdcmanager.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JWTFilter jwtFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .httpBasic().disable()
            .authorizeHttpRequests(r -> {
                r.antMatchers("/api/auth/**").permitAll();
                r.anyRequest().authenticated();
            })
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        hasAnyRole("ADMIN", "RESPONSIBLE", "PARTICIPANT")

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/api/auth/**");
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
