package com.src.service;

import java.util.Map;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

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

	private Key getKey() {
		// TODO Auto-generated method stub
		byte[] keyByte=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyByte);
	}

}
