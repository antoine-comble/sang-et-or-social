package com.zenika.rclens.social.tech;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/joueur/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/match/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/match/**").hasAnyRole("ADMIN", "MODERATEUR")
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/user/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .httpBasic(withDefaults()).build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        final UserDetails user = User.builder()
                .username("user")
                .password("{noop}password")
                .roles("USER")
                .build();

        final UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}password")
                .roles("ADMIN")
                .build();

        final UserDetails moderateur = User.builder()
                .username("moderateur")
                .password("{noop}password")
                .roles("MODERATEUR")
                .build();

        return new InMemoryUserDetailsManager(user, admin, moderateur);
    }

}
