package com.intransition.labs.service;

import java.util.List;
import java.util.Set;

import com.intransition.labs.domain.content.Award;
import com.intransition.labs.domain.user.User;

public interface AwardService {
	
	Award getAward(AwardType type );
	
	List<Award> sortAwardsByAwardedTime( Set<Award> awardsToSort );
	
	public Award giveUserAward(User user, AwardType type );
	
}
