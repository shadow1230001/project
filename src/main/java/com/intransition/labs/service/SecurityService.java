package com.intransition.labs.service;

import com.intransition.labs.domain.user.User;

public interface SecurityService {
    /**
     *
     * @return Logged for type String
     */

    String findLoggedInUsername();

    /**
     *
     * @return Logged User type
     */

    User getLoggedInUser();

    /**
     *
     * @param username username user
     * @param password user password
     */

    void authorize(String username, String password);

    /**
     *
     * @param role name of Role
     * @return Returns a presence flag for the role
     */

    boolean hasRole(String role);

}
