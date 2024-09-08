package com.src.service;

import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private String secretKey="243sdfjsdfnkjgiebgehfdbgjhfw9835h3iunrgveiwahl";

	public String generateToken(String username) {
		// TODO Auto-generated method stub
		
		Map<String, Object> claims=new HashMap<>();
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+600000))
				.and()
				.signWith(getKey())
				.compact();
	}

	private SecretKey getKey() {
		// TODO Auto-generated method stub
		byte[] keyByte=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyByte);
	}

	private <T> T extractClaims(String token,Function<Claims, T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token, Claims::getSubject);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		final String userName=extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token,Claims::getExpiration);
	}

}
