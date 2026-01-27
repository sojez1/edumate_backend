package com.jpstechno.edumate_backend.securityconfig;
/**
 * package com.jpstechno.simplespringauthentication.securityconfig;
 * 
 * import org.springframework.context.annotation.Bean;
 * import org.springframework.context.annotation.Configuration;
 * import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import
 * org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.security.crypto.password.PasswordEncoder;
 * import org.springframework.security.web.SecurityFilterChain;
 * 
 * @Configuration
 * @EnableWebSecurity
 *                    public class SecuriyConfiguration {
 * 
 * @Bean
 *       public PasswordEncoder passwordEncoder() {
 *       return new BCryptPasswordEncoder();
 *       }
 * 
 *       public SecurityFilterChain securityFilterChain(HttpSecurity http) {
 *       http
 *       .csrf(myCsrf -> myCsrf.disable())
 *       .authorizeHttpRequests(myReq -> myReq
 *       .requestMatchers("/utilisateurs/new_user").permitAll()
 *       .requestMatchers("utilisateurs/verifier_email").permitAll()
 *       .anyRequest().permitAll());
 *       return http.build();
 *       }
 * 
 *       }
 */
