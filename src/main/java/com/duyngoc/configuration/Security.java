/*package com.duyngoc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import service.UserDetailsServices;



@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {


	@Autowired
	private UserDetailsServices userDetailsService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		 * http.httpBasic().and().authorizeRequests()
		 * .antMatchers("/**").permitAll()
		 * .antMatchers("/manager/").access("hasRole('USER')").anyRequest().
		 * authenticated();
		 

		
		 * http .httpBasic().and() .authorizeRequests()
		 * .antMatchers("/index.html", "/home.html", "/login.html",
		 * "/").permitAll() .anyRequest().authenticated() .and() .csrf()
		 * .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		 

		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		//.antMatchers("/api.employee/**").access("hasRole('USER')")
		.antMatchers("/search/**").permitAll()
		.antMatchers("/api.manager/**").access("hasRole('ADMIN')")
		.antMatchers("/login","/api.employee/**").authenticated()
	
		.and().httpBasic()  
				antMatchers("/manager/**").access("hasRole('USER')")
				.antMatchers("/api/login").access("hasRole('USER')")
				.antMatchers("/**").permitAll();

		// .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
		// .and().logout().logoutRequestMatcher(new
		// AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");;

		.and().csrf().disable();
	}

}
*/