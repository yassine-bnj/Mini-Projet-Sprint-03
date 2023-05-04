package com.example.livres.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	
	
	@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager( ) {
		 PasswordEncoder passwordEncoder = passwordEncoder ();
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(passwordEncoder.encode("123")).roles("USER").build(),
                User.withUsername("yassine").password(passwordEncoder.encode("123")).roles("USER","AGENT").build(),
                User.withUsername("admin").password(passwordEncoder.encode("123")).roles("ADMIN").build()
        );
    }
	
	
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	       

	        
	        httpSecurity.csrf().disable();
	        httpSecurity.authorizeHttpRequests().requestMatchers("/login").permitAll();
	        httpSecurity.authorizeHttpRequests().requestMatchers("/showCreate").hasAnyRole("ADMIN","AGENT");
	        httpSecurity.authorizeHttpRequests().requestMatchers("/saveLivre").hasAnyRole("ADMIN","AGENT");
	        
	        httpSecurity.authorizeHttpRequests().requestMatchers("/ListeLivres").hasAnyRole("ADMIN","AGENT","USER");
	        httpSecurity.authorizeHttpRequests().
	        requestMatchers("/supprimerLivre","/updateLivre","/modifierLivre","/showCreateType"
	        		,"/saveType","/ListeTypes","/supprimerType","/modifierType","/updateType"
	        		).hasRole("ADMIN");
	       
	        
	        httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
	        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();

	        httpSecurity.formLogin();
	        httpSecurity.exceptionHandling().accessDeniedPage("/accessDenied");
	        
	        return httpSecurity.build();
	    }
	
	  @Bean
	  public PasswordEncoder passwordEncoder () {
	  return new BCryptPasswordEncoder();
	  }
	  
}
