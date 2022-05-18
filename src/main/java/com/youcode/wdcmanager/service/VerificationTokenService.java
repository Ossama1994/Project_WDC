package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Person;
import com.youcode.wdcmanager.entity.VerificationToken;
import com.youcode.wdcmanager.repository.PersonRepository;
import com.youcode.wdcmanager.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;
    private final PersonRepository personRepository;

    public VerificationToken create(Person person){
        VerificationToken verificationToken = VerificationToken.builder()
                .token(UUID.randomUUID())
                .person(person)
                .expireAt(LocalDateTime.now().plusDays(1))
                .build();
        return verificationTokenRepository.save(verificationToken);
    }

    public void deleteById(Long id){
        verificationTokenRepository.deleteById(id);
    }

    public boolean isTokenValid(UUID token){
        Optional<VerificationToken> tokens = verificationTokenRepository
                .findByTokenAndExpireAtGreaterThan(token, LocalDateTime.now())
                .stream().findAny();
        tokens.ifPresent(vt -> {
            Person person = vt.getPerson();
            person.setStatus(true);
            personRepository.save(person);
        });
        return tokens.isPresent();
    }
}
