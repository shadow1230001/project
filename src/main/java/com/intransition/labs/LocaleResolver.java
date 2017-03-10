package com.intransition.labs;

import com.intransition.labs.domain.user.User;
import com.intransition.labs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.AbstractLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Locale;

public class LocaleResolver extends AbstractLocaleResolver {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) return new Locale("en");
        User user = userRepository.findByNickname(principal.getName());

        return new Locale(user.getUserSettings().getLanguage().getCode());
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }

}
