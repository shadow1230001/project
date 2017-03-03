package com.intransition.labs.service.auth.impl;

import com.intransition.labs.service.auth.AuthService;
import org.springframework.stereotype.Service;

/**
 * Created by User on 02.03.2017.
 */
@Service(value = "authServiceTwitter")
public class AuthServiceTwitter implements AuthService {
    @Override
    public void authorize(String userName, String password) {

    }
}
