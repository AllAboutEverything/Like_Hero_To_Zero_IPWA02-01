package com.example.likeherotozero.security;

import com.example.likeherotozero.repository.ScientistRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ScientistRepository scientistRepository;

    public CustomUserDetailsService(ScientistRepository scientistRepository) {
        this.scientistRepository = scientistRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var scientist = scientistRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(
                scientist.getUsername(),
                scientist.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(scientist.getRole()))
        );
    }
}
