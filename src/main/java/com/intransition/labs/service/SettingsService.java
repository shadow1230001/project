package com.intransition.labs.service;

import java.util.List;

import com.intransition.labs.domain.user.Language;
import com.intransition.labs.domain.user.User;
import com.intransition.labs.form.SettingsForm;
import com.intransition.labs.domain.user.Theme;

public interface SettingsService {

	SettingsForm getSettingsForm();
	
	List<Theme> getAllAvailableThemes();
	
	List<Language> getAllAvailableLanguages();
	
	void updateUserSettings(User user, SettingsForm settingsForm );
	
}
