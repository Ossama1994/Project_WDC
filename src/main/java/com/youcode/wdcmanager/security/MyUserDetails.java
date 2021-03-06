package com.youcode.wdcmanager.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

public interface MyUserDetails extends UserDetails {
    Instant getTokenExpireAt();
    Long getId();
}
