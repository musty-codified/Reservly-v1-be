package com.mustycodified.Reservlyv1be.config;
import com.mustycodified.Reservlyv1be.security.AuthenticationFilter;
import com.mustycodified.Reservlyv1be.security.JwtAuthorizationFilter;
import com.mustycodified.Reservlyv1be.security.SecurityConstants;
import com.mustycodified.Reservlyv1be.services.UserService;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserService userDetailsService;

  private String path = "/api/v1/";
    private final String[] WHITE_LISTED_URLS = { SecurityConstants.EMAIL_VERIFICATION_URL, SecurityConstants.SIGN_UP_URL,
            "/swagger*/**","/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        http.cors().and().csrf()
                .disable().authorizeRequests().
                antMatchers(WHITE_LISTED_URLS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilterAfter(new JwtAuthorizationFilter(), AuthenticationFilter.class)
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods(CorsConfiguration.ALL)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedOriginPatterns(CorsConfiguration.ALL);
            }
        };
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    public AuthenticationFilter getAuthenticationFilter(){
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authentication -> {
            return authentication;
        });
        authenticationFilter.setFilterProcessesUrl("/users/authenticate");
        return authenticationFilter;
    }

}
