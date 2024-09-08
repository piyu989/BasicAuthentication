package com.src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.src.entity.User;
import com.src.entity.UserPrincipal;

@Configuration
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=service.getUser(username);
		
		if(user==null) {
			throw new RuntimeException("user not found");
		}
		
		return new UserPrincipal(user);
	}

}
