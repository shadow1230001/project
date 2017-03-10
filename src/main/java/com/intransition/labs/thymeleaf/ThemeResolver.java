package com.intransition.labs.thymeleaf;

import com.intransition.labs.domain.user.User;
import com.intransition.labs.repository.UserRepository;
import com.intransition.labs.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.theme.AbstractThemeResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.security.Principal;

public class ThemeResolver extends AbstractThemeResolver {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeService themeService;

    @Transactional
    public String resolveThemeName(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal == null) return themeService.getDefaultThemeName();

        User user = userRepository.findByNickname(principal.getName());

        return user.getUserSettings().getTheme().getCode();

    }

    @Override
    public void setThemeName(HttpServletRequest request, HttpServletResponse response, String themeName) {
    }

}
