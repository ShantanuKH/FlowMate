package com.flowmate.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity

public class AppConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement(Management -> Management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//      Provide Enpoint Security that which end point should be accessible without login and without registration
//      Only the endpoints starting with /api/** should be authenticated for that we must use JWT token, Without JWT token it cannot be accessible,
//      and anyother request afterwords should be permitted, i.e signup, login etc
                .authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csfr-> csfr.disable())
                .cors(cors->cors.configurationSource(corsConfiguration()));
//      .addFilterBefore(new) will check JWT Tokens are validate or not


        return http.build();

    }

    private CorsConfigurationSource corsConfiguration() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg=new CorsConfiguration();
//                 Only the mentioned frontend URL are able to get access to it, Basically without this frontend cannot be attached to backend
                cfg.setAllowedOrigins(Arrays.asList(
//                        For localhost
                        "http://localhost:3000",
//                        For Vita App
                        "http://localhost:5173",
//                        For Angular
                        "http://localhost:4200/"
                ));


                cfg.setAllowedMethods(Collections.singletonList("*"));
                // What are the mehthods that we want to allow
                cfg.setAllowCredentials(true);
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setAllowedHeaders(Arrays.asList("Authorization"));
                cfg.setMaxAge(3600L);


                return cfg;
            }
        };

//         Before putting password in the database we need to BigCrypt the password and so we are doing so

        }

            @Bean
            PasswordEncoder passwordEncoder(){
                return new BCryptPasswordEncoder();

    }

}

