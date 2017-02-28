package com.intransition.labs.service.impl;

import java.util.Collection;

import com.intransition.labs.domain.user.User;
import com.intransition.labs.service.SecurityService;
import com.intransition.labs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;
    
    public String findLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
        /* 
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getName();
        
        if( userDetails instanceof User ) {
            return ( (UserDetails) userDetails ).getUsername();
        }

        return null;
        */
    }
    
	public User getLoggedInUser() {
		return userRepository.findByNickname( findLoggedInUsername() );
	}


    public void authorize( String username, String password ) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
       
        if( password == null ) {
        	Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities() );
        	SecurityContextHolder.getContext().setAuthentication(authentication);
        	return;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken( userDetails, password, userDetails.getAuthorities() );

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
    

	  public boolean hasRole(String role) {
		    boolean hasRole = false;
		    UserDetails userDetails = getUserDetails();
		    if (userDetails != null) {
		      Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		      if (isRolePresent(authorities, role)) {
		        hasRole = true;
		      }
		    } 
		    return hasRole;
		  }
		  /**
		   * Get info about currently logged in user
		   * @return UserDetails if found in the context, null otherwise
		   */
		  protected UserDetails getUserDetails() {
		    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    UserDetails userDetails = null;
		    if (principal instanceof UserDetails) {
		      userDetails = (UserDetails) principal;
		    }
		    return userDetails;
		  }
		  /**
		   * Check if a role is present in the authorities of current user
		   * @param authorities all authorities assigned to current user
		   * @param role required authority
		   * @return true if role is present in list of authorities assigned to current user, false otherwise
		   */
		  private boolean isRolePresent(Collection<? extends GrantedAuthority> authorities, String role) {
		    boolean isRolePresent = false;
		    for (GrantedAuthority grantedAuthority : authorities) {
		      isRolePresent = grantedAuthority.getAuthority().equals(role);
		      if (isRolePresent) break;
		    }
		    return isRolePresent;
		  }
    
}
