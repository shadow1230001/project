package com.intransition.labs.service.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetails extends User {

	private com.intransition.labs.domain.user.User user;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5914552916661645537L;

	public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public com.intransition.labs.domain.user.User getUser() {
		return user;
	}

	public void setUser(com.intransition.labs.domain.user.User user) {
		this.user = user;
	}

}