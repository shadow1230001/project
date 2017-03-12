package com.intransition.labs.service;

import com.intransition.labs.domain.user.Language;
import com.intransition.labs.domain.user.Theme;
import com.intransition.labs.domain.user.User;
import com.intransition.labs.dto.SettingsForm;

import java.util.List;

/**
 * Settings service.
 */
public interface SettingsService {

    SettingsForm getSettingsForm();

    List<Theme> getAllAvailableThemes();

    List<Language> getAllAvailableLanguages();

    void updateUserSettings(User user, SettingsForm settingsForm);

}
