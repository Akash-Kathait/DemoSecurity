package com.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    /*
    InMemoryAuthentication manager Example
     */
    @Bean
    public InMemoryUserDetailsManager authenticationManager(PasswordEncoder encoder) {
        UserDetails user1 = User.builder()
                .username("admin")
                .password("456")
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize ->
                                authorize
                                        .requestMatchers("/admin-login").hasRole("ADMIN")
                                .requestMatchers("/user-login").hasAnyRole("USER","ADMIN")
                                .requestMatchers("login/**").permitAll()
                                .anyRequest().authenticated()   )
                .formLogin(Customizer.withDefaults()) ;
                return http.build();

    }



    @Bean
  public  PasswordEncoder encoder() {
        return   NoOpPasswordEncoder.getInstance();
    }


}
