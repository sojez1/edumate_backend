package com.jpstechno.edumate_backend.securityconfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final List<String> listeUrlAutorise = List.of("http://localhost:5173",
            "https://edumate-frontend.onrender.com");
    private final List<String> listeHttpMethodeAccepte = List.of("GET", "DELETE", "PUT", "POST", "OPTIONS");
    private final List<String> listeEnteteAutorisee = List.of("Authorization", "Content-Type");

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public CorsConfigurationSource corsConfig() {

        CorsConfiguration corsconf = new CorsConfiguration();
        corsconf.setAllowedOrigins(List.of("*"));
        corsconf.setAllowedMethods(List.of("*"));
        corsconf.setAllowedHeaders(List.of("*"));
        corsconf.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlCorsSource = new UrlBasedCorsConfigurationSource();
        urlCorsSource.registerCorsConfiguration("/**", corsconf);

        return urlCorsSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(14);
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider(userDetailsService);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return daoProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) {
        return authConfig.getAuthenticationManager();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http
                .csrf((customCsrf) -> customCsrf.disable())
                .cors(Customizer.withDefaults())
                // .addFilterBefore(null, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/", "/auth/login",
                                "/error",
                                "/auth/verifier-otp",
                                "/auth/refreshToken",
                                "/demande-admission/soumettre",
                                "/enumerations/**",
                                "/utilisateurs/new_user/**",
                                "/classes/listerClasses/**",
                                "/anneeScolaires/lister/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }

}