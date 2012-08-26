package com.leonty.etmweb.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class ServiceAuthManager implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication token)
			throws AuthenticationException {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_SERVICE"));		
		
		return new UsernamePasswordAuthenticationToken(
			     token.getPrincipal(),
			     token.getCredentials(),
			     authorities);
	}

}
