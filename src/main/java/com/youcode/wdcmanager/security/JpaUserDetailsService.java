package com.youcode.wdcmanager.security;

import com.youcode.wdcmanager.entity.Person;
import com.youcode.wdcmanager.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Person loadedUser = personRepository.findById(Long.parseLong(id)).orElse(null);
        return new MyUserDetailsImpl(loadedUser);
    }
}
