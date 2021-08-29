package com.example.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

	@Autowired
	public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth.inMemoryAuthentication()
		  .withUser("john").password("123").roles("USER").and()
		  .withUser("tom").password("111").roles("ADMIN").and();
//		  .withUser("john").password(passwordEncoder.encode("123")).roles("USER").and()
//		  .withUser("tom").password(passwordEncoder.encode("111")).roles("ADMIN").and()
//		  .withUser("user1").password(passwordEncoder.encode("pass")).roles("USER").and()
//		  .withUser("admin").password(passwordEncoder.encode("nimda")).roles("ADMIN");
	    }// @formatter:on

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.sessionManagement().
//		http.authorizeRequests().antMatchers("/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		// @formatter:off
//		http.authorizeRequests()
//		.antMatchers("/login").permitAll()
//		.antMatchers("/oauth/token/revokeById/**").permitAll()
//		.antMatchers("/tokens/**").permitAll()
//		//.antMatchers("/h2-console/**").permitAll()
//		.anyRequest().authenticated()
//		.and().formLogin().permitAll()
//		.and().csrf().disable();
		// @formatter:on
	}

    @Override
    public void configure(WebSecurity web) throws Exception {
    	 web.ignoring().antMatchers(
                 "/h2-console/**");
    }
}
