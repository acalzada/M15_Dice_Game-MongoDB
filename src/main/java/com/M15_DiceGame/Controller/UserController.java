package com.M15_DiceGame.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.M15_DiceGame.Domain.UserAuth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {
	
	@PostMapping({"/user","/user/"})
	public UserAuth login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		String token = getJWTToken(username);
		UserAuth userAuth = new UserAuth();
		userAuth.setUser(username);
		userAuth.setToken(token);
		return userAuth;
	}
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
													.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
						.builder()
						.setId("softtekJWT")
						.setSubject(username)
						.claim("authorities", grantedAuthorities.stream()
												.map(GrantedAuthority::getAuthority)
												.collect(Collectors.toList()))
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + 600000))
						.signWith(SignatureAlgorithm.HS512,
								secretKey.getBytes()).compact();
		
		return "Bearer " + token;
	}
}
