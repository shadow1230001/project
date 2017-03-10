package com.intransition.labs.controller;

import com.intransition.labs.admin.ProfilePageJsonWrapper;
import com.intransition.labs.domain.user.Profile;
import com.intransition.labs.domain.user.User;
import com.intransition.labs.service.SecurityService;
import com.intransition.labs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminPanelController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String get() {
        if (!securityService.hasRole("ADMIN")) {
            return "redirect:/";
        }

        return "admin";
    }

    @GetMapping("/profiles")
    public
    @ResponseBody
    HashMap<String, Object> getProfiles(@PageableDefault(size = 2) Pageable pageable) {
        List<Profile> profiles = new ArrayList<Profile>();
        Page<User> page = userService.getProfiles(pageable);

        page.forEach(user -> profiles.add(new Profile(user)));

        Collections.sort(profiles, new Comparator<Profile>() {
            public int compare(Profile o1, Profile o2) {
                if (o1.getId() > o2.getId()) return 1;
                else return -1;
            }
        });

        HashMap<String, Object> map = new HashMap<>();
        map.put("profiles", profiles);
        map.put("page", new ProfilePageJsonWrapper(page));
        return map;
    }

}
