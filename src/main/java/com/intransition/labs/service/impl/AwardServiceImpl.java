package com.intransition.labs.service.impl;

import com.intransition.labs.domain.content.Award;
import com.intransition.labs.domain.user.User;
import com.intransition.labs.service.AwardService;
import com.intransition.labs.service.AwardType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class AwardServiceImpl implements AwardService {

	@Override
	public Award getAward(AwardType type) {
		return new Award( type );
	}
	
	public List<Award> sortAwardsByAwardedTime( Set<Award> awardsToSort ) {
		ArrayList<Award> awards = new ArrayList<Award>( awardsToSort );
		
		Collections.sort( awards, (o1, o2) -> {
            if( o1.getAwardedTimestamp().after( o2.getAwardedTimestamp() ) ) return 1; else return -1;
        });
		return awards;
	}
	
	public Award giveUserAward(User user, AwardType type ) {
        Award award = getAward( type );
        award.setAwardedTimestamp( new java.sql.Timestamp( Calendar.getInstance().getTimeInMillis() ) );
        user.addAward( award );
		return award;
	}

}
