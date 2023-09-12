package com.backend.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

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
  
   @Bean
   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	   /*http
	   		//http.securityMatcher("/**") 
   			.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())
   			.formLogin(withDefaults())
   			.httpBasic(withDefaults())
   		;*/
	   
	   // https://github.com/Ons-diweni/Spring-Security-6-JWT/blob/main/src/main/java/com/ons/securitylayerJwt/security/SpringSecurityConfig.java

	   http.csrf().disable()
	   		.cors(cors ->cors.disable())
	   		.authorizeHttpRequests(
	   				auth -> auth.requestMatchers("/**").permitAll()
	   					.requestMatchers("/**")
	   					.hasAnyRole("USER")
	   					.requestMatchers("/**")
	   					.access(new WebExpressionAuthorizationManager("hasRole('USER')"))
	   					.anyRequest().denyAll()
	        );
	   http.httpBasic(withDefaults());

	  return http.build();
   }
}