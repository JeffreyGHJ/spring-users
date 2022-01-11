package com.jeffreyghj.springusers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jeffreyghj.springusers.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	/*
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return null;
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return null;
	}
	*/
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("pass")).roles("ADMIN")
			.and()
			.withUser("user1").password(passwordEncoder().encode("pass1")).roles("USER")
			.and()
			.withUser("premium").password(passwordEncoder().encode("paid")).roles("PREMIUM");
		
		auth.userDetailsService(userDetailsService);
		
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		
		//Authorize all requests for css files so that the login page can be styled before the user is authenticated
		/*
		http.authorizeRequests().antMatchers("/css/**").permitAll()
			.antMatchers("/users/showCreateUserForm").permitAll()
			//.antMatchers("/users/saveUser").permitAll()	  // Allow admin to easily spoof new users
			.antMatchers("/users/createNewUser").permitAll()  // Allow anyone to create a new account
			.antMatchers("/users/confirmCreateUser").permitAll();
			//.antMatchers("/users/list").permitAll();
		*/
		
		http.requiresChannel()
			.requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
			.requiresSecure();
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/users/showCreateUserForm").permitAll()
			//.antMatchers("/users/saveUser").permitAll()	  // Allow admin to easily spoof new users
			.antMatchers("/users/createNewUser").permitAll()  // Allow anyone to create a new account
			.antMatchers("/users/confirmCreateUser").permitAll()
			//.antMatchers("/users/list").permitAll();
			//.antMatchers("/admin/**").hasRole("ADMIN")
			//.antMatchers("/anonymous*").anonymous()
			//.antMatchers("/resources/**").permitAll()
			//.antMatchers("/login*").permitAll()
			.anyRequest().authenticated()
			
			// configure form login and logout
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage") // @GetMapping for this found in LoginController.java
				.loginProcessingUrl("/authenticateUser")
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
			
	}
}
