package com.demo.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;


    /*
    Jdbc Authentication
     */
@Bean
    JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.createUser(getAdminUser());
        return manager;
    }



/*
InMemory Authentication
uncomment @Bean to enable InMemory authenication
 */
//    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder encoder) {
        UserDetails user1 = getAdminUser();

        UserDetails user2 = getUser();

        return new InMemoryUserDetailsManager(user1,user2);
    }

    private static UserDetails getUser() {
        UserDetails user2 = User.builder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();
        return user2;
    }

    private static UserDetails getAdminUser() {
        UserDetails user = User.builder()
                .username("admin")
                .password("456")
                .roles("ADMIN")
                .build();
        return user;
    }



    /*
    Authorization of users

    disabled default security to access H2 console
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/admin-login").hasRole("ADMIN")
                                .requestMatchers("/user-login").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("login/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/console/**").permitAll()
                                .anyRequest().authenticated()

                )
                .csrf(AbstractHttpConfigurer::disable)
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .formLogin(Customizer.withDefaults());
        return http.build();

    }

    @Bean
  public  PasswordEncoder encoder() {
        return   NoOpPasswordEncoder.getInstance();
    }


}
