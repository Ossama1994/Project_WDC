package com.youcode.wdcmanager.security;

import com.youcode.wdcmanager.entity.Person;
import com.youcode.wdcmanager.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usernameOrEmail = authentication.getName();
        String password = authentication.getCredentials().toString();

        Person foundUser = personRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .filter(p -> passwordEncoder.matches(password, p.getPassword()))
                .orElse(null);

        if(foundUser != null)
        return new UsernamePasswordAuthenticationToken(foundUser.getId(), password);

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
