package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Person;
import com.youcode.wdcmanager.entity.VerificationToken;
import com.youcode.wdcmanager.repository.PersonRepository;
import com.youcode.wdcmanager.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final PersonRepository personRepository;

    private final PersonService personService;
    private final VerificationTokenService verificationTokenService;

    public String login(Person person){
        String usernameOrEmail = person.getUsername() == null ? person.getEmail() : person.getUsername();
        Authentication authentification =
                new UsernamePasswordAuthenticationToken(usernameOrEmail, person.getPassword());
        Authentication authenticated = authenticationManager.authenticate(authentification);
        return jwtUtil.createToken(Long.valueOf(authenticated.getName()));
    }

    public boolean logout(Long userId){
        personRepository.findById(userId)
                .ifPresent(p -> {
                    p.setLoginExpireAt(Instant.now());
                    personRepository.save(p);
            });
        return true;
    }

    public String register(Person person){
        Person registredPerson = personService.create(person);
        sendRegisterConfirm(person);
        return jwtUtil.createToken(person.getId());
    }

    public void sendRegisterConfirm(Person person){
        VerificationToken verificationToken = verificationTokenService.create(person);
        String token = verificationToken.getToken().toString();

        String emailTemplate = "Hello %s %s,\n\nThank you for registering to our services..\n\nTo complete your registration, please confirm your account by clicking the link below:\n\nhttps://localhost:8081/api/auth/confirm/%s\n\nNotice: link expire after 24 hours from the time this email was sent.\n\nBest regards,\nWDCManager";
        String formattedText = String.format(emailTemplate, person.getFirstName(), person.getLastName(), token);

    }
}
