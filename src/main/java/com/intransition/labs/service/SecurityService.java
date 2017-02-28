package com.intransition.labs.service;

import com.intransition.labs.domain.user.User;

public interface SecurityService {
	
    String findLoggedInUsername();

    User getLoggedInUser();
    
    void authorize( String username, String password );
    
    boolean hasRole(String role);
    
}
