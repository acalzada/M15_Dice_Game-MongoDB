package com.M15_DiceGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.M15_DiceGame.Security.JWTAuthorizationFilter;

@SpringBootApplication
public class M15DiceGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(M15DiceGameApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
							.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
							.authorizeRequests()
							.antMatchers(HttpMethod.POST, "/user").permitAll()
							.anyRequest().authenticated();
		}
	}

}
