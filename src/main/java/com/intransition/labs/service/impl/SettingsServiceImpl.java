package com.intransition.labs.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.intransition.labs.form.SettingsForm;
import com.intransition.labs.domain.user.Language;
import com.intransition.labs.domain.user.Theme;
import com.intransition.labs.domain.user.User;
import com.intransition.labs.repository.LanguageRepository;
import com.intransition.labs.repository.ThemeRepository;
import com.intransition.labs.repository.UserRepository;
import com.intransition.labs.service.SecurityService;
import com.intransition.labs.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Override
	public SettingsForm getSettingsForm() {
		SettingsForm form = new SettingsForm();
		
		User user = userRepository.findByNickname( securityService.findLoggedInUsername() );

		form.setEmail( user.getEmail() );
		form.setFirstName( user.getFirstName() );
		form.setLastName( user.getLastName() );
		form.setNickname( user.getNickname() );
		
		return form;
	}

	@Override
	@Transactional
	public List<Theme> getAllAvailableThemes() {
		return themeRepository.findAll();
	}

	@Override
	@Transactional
	public List<Language> getAllAvailableLanguages() {
		return languageRepository.findAll();
	}

	@Transactional
	public void updateUserSettings( User user, SettingsForm settingsForm) {
		user.setFirstName( settingsForm.getFirstName() );
		user.setLastName( settingsForm.getLastName() );
		user.setNickname( settingsForm.getNickname() );
		
		userRepository.save( user );
		
		//Authentication authentication = new UsernamePasswordAuthenticationToken(userObject, userObject.getPassword(), userObject.getAuthorities());
		//SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	
	
	
}
