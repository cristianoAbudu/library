package com.backend.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ServerSecurityConfig  {

   private final UserDetailsService userDetailsService;

   public ServerSecurityConfig(
		   @Qualifier("userService")
           UserDetailsService userDetailsService) {
       this.userDetailsService = userDetailsService;
   }
   
   @Bean
   JwtAuthenticationConverter jwtAuthenticationConverter() {
       JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
       grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
       grantedAuthoritiesConverter.setAuthorityPrefix("");

       JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
       jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
       return jwtAuthenticationConverter;
   }
   /*
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
           .cors(AbstractHttpConfigurer::disable)
           .csrf(AbstractHttpConfigurer::disable)
           .authorizeHttpRequests(request -> request
                   .requestMatchers("*").permitAll()
                   .requestMatchers(HttpMethod.OPTIONS).permitAll()
                   //.requestMatchers("*").hasAuthority("SYSTEM_ADMIN")
                   .requestMatchers("*").authenticated()
           )
           //.sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
           //.authenticationProvider(authenticationProvider())
           //.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
           ;
       ;
       SecurityFilterChain chain = http.build();
       //log.info("Configured security filter chain: {}",chain);
       return chain;
   }
   */
   
   @Bean
   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
   	http.cors(cors -> cors.disable());
   	return http.build();
   }
}