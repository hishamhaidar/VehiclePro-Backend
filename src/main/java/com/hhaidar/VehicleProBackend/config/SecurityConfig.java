package com.hhaidar.VehicleProBackend.config;

import com.hhaidar.VehicleProBackend.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final   AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.
                csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        auth ->
                        {
                            auth.antMatchers("/user/register",
                                            "/user/authenticate","/booking/book/**")
                                    .permitAll()
                                    .antMatchers("/booking/confirm/**",
                                            "/booking/deny/**",
                                            "/slots/create/**",
                                            "/slots/edit/**",
                                            "/slots/delete/**")
                                    .hasAnyAuthority(Role.SERVICE_MANAGER.name(),Role.GARAGE_OWNER.name())
                                    .antMatchers("/user/role/**")
                                    .hasAuthority(Role.GARAGE_OWNER.name())
                                    .anyRequest()
                                    .authenticated();
                        }

                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
}

}
