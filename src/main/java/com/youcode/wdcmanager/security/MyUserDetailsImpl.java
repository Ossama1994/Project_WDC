package com.youcode.wdcmanager.security;

import com.youcode.wdcmanager.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class MyUserDetailsImpl implements MyUserDetails {
    private final Person person;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority role =
                new SimpleGrantedAuthority("ROLE_".concat(person.getRole().getName()));
        return Collections.singleton(role);
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return person.getStatus();
    }

    @Override
    public Instant getTokenExpireAt(){
        return person.getLoginExpireAt();
    }

    @Override
    public Long getId() {
        return person.getId();
    }


}
