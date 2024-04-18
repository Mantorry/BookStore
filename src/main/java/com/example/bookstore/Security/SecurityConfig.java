package com.example.bookstore.Security;

import com.example.bookstore.Data.Users;
import com.example.bookstore.Repo.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRep) {
        return username -> {
            Users users = userRep.findByUsername(username);
            if (users != null) return users;
            throw new UsernameNotFoundException("Пользователь: ‘" + username + "’ не найден");
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        return http.authorizeHttpRequests(auth->auth
                         .requestMatchers("/Book/**","/Customer/**","/Sale/**","/Delivery/**").authenticated()
                        .requestMatchers("/Login", "/Registration").anonymous()
                         .requestMatchers("/","/css/**","/img/**").permitAll())
                        .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/Login"))
                .build();
    }
}