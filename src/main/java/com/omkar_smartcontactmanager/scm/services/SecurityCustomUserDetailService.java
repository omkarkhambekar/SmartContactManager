package com.omkar_smartcontactmanager.scm.services;

import com.omkar_smartcontactmanager.scm.repositories.UserRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    private final UserRepositories userRepositories;

    public SecurityCustomUserDetailService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // apane user ko load karwana
        return userRepositories.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email " + username ));

    }
}
