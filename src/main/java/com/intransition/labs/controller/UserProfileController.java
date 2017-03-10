package com.intransition.labs.controller;

import com.intransition.labs.domain.user.User;
import com.intransition.labs.repository.UserRepository;
import com.intransition.labs.service.AwardService;
import com.intransition.labs.service.CreativeService;
import com.intransition.labs.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AwardService awardService;

    @Autowired
    private CreativeService creativeService;

    @GetMapping("/user/")
    public String get() {
        if (securityService.findLoggedInUsername() == null) return "redirect:/";
        return "redirect:/personal";
    }

    @GetMapping(value = "/user/{nickname}")
    public String getUserProfile(Model model, @PathVariable(required = true) String nickname) {
        User requestedUser = userRepository.findByNickname(nickname);

        if (requestedUser == null) {
            return "personal/profileNotFound";
        }

        model.addAttribute("requestedUser", requestedUser);
        model.addAttribute("awards", awardService.sortAwardsByAwardedTime(requestedUser.getAwards()));
        model.addAttribute("creatives", creativeService.sortCreativesByCreatedDesc(requestedUser.getCreatives()));

        return "personal/profile";
    }

}
