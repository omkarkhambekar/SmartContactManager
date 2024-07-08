package com.omkar_smartcontactmanager.scm.config;

import com.omkar_smartcontactmanager.scm.services.SecurityCustomUserDetailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
//
//    //user create and login using java with in memory service
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User.withUsername("admin123").password("admin123").roles("ADMIN","USER").build();
//        UserDetails user2 = User.withUsername("user123").password("user123").build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
//



    private final SecurityCustomUserDetailService UserDetailService;

    public SecurityConfig(SecurityCustomUserDetailService userDetailService) {
        UserDetailService = userDetailService;
    }

    //configuration of authenticaton provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //user details ka object
        daoAuthenticationProvider.setUserDetailsService(UserDetailService);
        //use password object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //configuration

        //urls configure kiya hai konse public rahenge aur private rahange
        httpSecurity.authorizeHttpRequests(authorize -> {
//            authorize.requestMatchers("/home","/register","/service").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

