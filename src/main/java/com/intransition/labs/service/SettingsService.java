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
    /**
     *
     * @return config settings
     */

    SettingsForm getSettingsForm();

    /**
     *
     * @return Collection of all available themes
     */

    List<Theme> getAllAvailableThemes();

    /**
     *
     * @return Collection of all available languages
     */

    List<Language> getAllAvailableLanguages();

    /**
     *
     * @param user
     * @param settingsForm
     */

    void updateUserSettings(User user, SettingsForm settingsForm);

}
