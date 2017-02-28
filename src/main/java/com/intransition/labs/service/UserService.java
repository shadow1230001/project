package com.intransition.labs.service;

import com.intransition.labs.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;

public interface UserService {
	
	void register( User user );
	
    User findByNickname( String nickname );
    
    User findByEmail( String email );
	
    boolean existsEmail(String email);
 
    void registerByVK( String email, VkApiClient vk, UserActor actor );
    
    Page<User> getProfiles( Pageable pageable );
    
}
