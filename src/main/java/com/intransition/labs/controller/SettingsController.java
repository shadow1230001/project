package com.intransition.labs.controller;

import com.intransition.labs.domain.user.Language;
import com.intransition.labs.domain.user.Theme;
import com.intransition.labs.domain.user.UserSettings;
import com.intransition.labs.dto.SettingsForm;
import com.intransition.labs.repository.UserSettingsRepository;
import com.intransition.labs.service.SecurityService;
import com.intransition.labs.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "personal/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserSettingsRepository userSettingsRepository;

    @GetMapping
    public String getSettingsPage(Model model) {
        Theme currentTheme = securityService.getLoggedInUser().getUserSettings().getTheme();
        List<Theme> themes = settingsService.getAllAvailableThemes();
        themes.remove(currentTheme);

        Language currentLanguage = securityService.getLoggedInUser().getUserSettings().getLanguage();
        List<Language> languages = settingsService.getAllAvailableLanguages();
        languages.remove(currentLanguage);

        model.addAttribute("currentTheme", currentTheme);
        model.addAttribute("currentLang", currentLanguage);

        model.addAttribute("settingsForm", settingsService.getSettingsForm());
        model.addAttribute("siteThemes", themes);
        model.addAttribute("siteLanguages", languages);

        return "personal/settings";
    }


    @PostMapping
    public String registration(@ModelAttribute("settingsForm") SettingsForm settingsForm, BindingResult bindingResult, Model model) {
        String currentThemeCode = settingsForm.getTheme();
        Theme currentTheme = null;
        List<Theme> themes = settingsService.getAllAvailableThemes();
        for (Theme theme : themes) {
            if (theme.getCode().equals(currentThemeCode)) {
                currentTheme = theme;
                break;
            }
        }
        themes.remove(currentTheme);

        model.addAttribute("currentTheme", currentTheme);
        model.addAttribute("siteThemes", themes);

        String currentLangCode = settingsForm.getLang();
        Language currentLang = null;
        List<Language> languages = settingsService.getAllAvailableLanguages();
        for (Language lang : languages) {
            if (lang.getCode().equals(currentLangCode)) {
                currentLang = lang;
                break;
            }
        }
        languages.remove(currentLang);

        model.addAttribute("currentLang", currentLang);
        model.addAttribute("siteLanguages", languages);


        UserSettings userSettings = securityService.getLoggedInUser().getUserSettings();

        userSettings.setTheme(currentTheme);
        userSettings.setLanguage(currentLang);

        userSettingsRepository.saveAndFlush(userSettings);

        settingsService.updateUserSettings(securityService.getLoggedInUser(), settingsForm);


        if (bindingResult.hasErrors()) {
            return "personal/settings";
        }

        // return "personal/settings";

        //save settings
        return "redirect:../";
    }

}
