package com.backend.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthWebSecurityConfiguration {
	@Autowired
	private AppBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().antMatchers("/public").permitAll().anyRequest().authenticated().and()
				.httpBasic(basic -> basic.authenticationEntryPoint(authenticationEntryPoint));
		return http.build();

	}

    @Bean
    InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER_ROLE")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(8);
	}
}