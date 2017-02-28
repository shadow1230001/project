package com.intransition.labs.service.impl;

import java.util.HashSet;
import java.util.Set;

import com.intransition.labs.domain.user.Role;
import com.intransition.labs.domain.user.User;
import com.intransition.labs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user;

		if( !isEmail( username ) ) {
			user = userRepository.findByNickname(username);
		}else{
			user = userRepository.findByEmail( username );
		}
		
        if( user == null ) throw new UsernameNotFoundException("No user in repository");
         
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for ( Role role : user.getRoles() ){
            grantedAuthorities.add(new SimpleGrantedAuthority( role.getName() ) );
        }
                
        com.intransition.labs.service.impl.UserDetails details = new com.intransition.labs.service.impl.UserDetails( user.getNickname(), user.getPassword(), true, true, true, !user.isBanned(), grantedAuthorities);//new org.Lyubin.service.impl.UserDetails(user.getNickname(), user.getPassword(), enabled, accountNonExpired, credential , grantedAuthorities);
        details.setUser(user);
        
        return details;
   }
	
	boolean isEmail( String email ) {
		if( email.split("@").length == 1 )  return false;
		return true;
	}
	
}
