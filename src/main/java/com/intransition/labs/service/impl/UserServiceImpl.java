package com.intransition.labs.service.impl;

import java.util.Calendar;
import javax.transaction.Transactional;

import com.intransition.labs.domain.user.User;
import com.intransition.labs.repository.AwardRepository;
import com.intransition.labs.repository.LanguageRepository;
import com.intransition.labs.repository.RoleRepository;
import com.intransition.labs.repository.ThemeRepository;
import com.intransition.labs.repository.UserRepository;
import com.intransition.labs.repository.UserSettingsRepository;
import com.intransition.labs.service.AwardService;
import com.intransition.labs.service.AwardType;
import com.intransition.labs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.users.UserXtrCounters;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AwardService awardService;
	
	@Autowired
	private AwardRepository awardRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private UserSettingsRepository userSettingsRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void register( User user ) {
        user.setPassword( bCryptPasswordEncoder.encode( user.getPassword() ) );
          
        user.addRole( roleRepository.findByName("USER") );
        user.setRegistrationDate( new java.sql.Date(Calendar.getInstance().getTime().getTime() ) );
        
        user.getUserSettings().setLanguage( languageRepository.findAll().get(0) );
        user.getUserSettings().setTheme( themeRepository.findAll().get(0) ); 
        
        awardRepository.saveAndFlush( awardService.giveUserAward(user, AwardType.SUCCESSFUL_REGISTRATION ) );
        userSettingsRepository.saveAndFlush( user.getUserSettings() );
        userRepository.save(user);
	}

	@Override
	public User findByNickname(String nickname) {
		return userRepository.findByNickname(nickname);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Transactional
	public boolean existsEmail(String email) {
		if( userRepository.findByEmail(email) == null ) {
			return false;
		}
		return true;
	}


	@Transactional
	 public void registerByVK( String email, VkApiClient vk, UserActor actor ) {
		 UserXtrCounters vkprofile;
		try {
			vkprofile = vk.users().get(actor).execute().get(0);
		
			User user = new User();
			 
			user.setEmail( email );
			user.setFirstName( vkprofile.getFirstName() );
			user.setLastName( vkprofile.getLastName() );
			user.setNickname( Integer.toString( vkprofile.getId() ) );
			user.setPassword( bCryptPasswordEncoder.encode( Integer.toString( vkprofile.getId() ) ) );
			user.setRegistrationDate( new java.sql.Date(Calendar.getInstance().getTime().getTime() ) );

	        user.addRole( roleRepository.findByName("USER") );
	                 
	        user.getUserSettings().setLanguage( languageRepository.findAll().get(0) );
	        user.getUserSettings().setTheme( themeRepository.findAll().get(0) ); 
	        
	        awardRepository.saveAndFlush( awardService.giveUserAward(user, AwardType.SUCCESSFUL_REGISTRATION ) );
	        userSettingsRepository.saveAndFlush( user.getUserSettings() );
	        userRepository.save(user);
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public Page<User> getProfiles( Pageable pageable ) {
		return userRepository.findAllByOrderByIdAsc( pageable );
	}
	 
}
