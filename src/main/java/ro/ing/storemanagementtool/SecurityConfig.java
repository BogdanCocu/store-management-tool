package ro.ing.storemanagementtool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails userAdmin = User.builder()
                .username("admin")
                .password(encoder().encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails userBasic = User.builder()
                .username("user")
                .password(encoder().encode("user"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userAdmin, userBasic);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/product/changePrice/**").hasRole("ADMIN")
                        .requestMatchers("/product/addProduct").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
