package com.intransition.labs.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.intransition.labs.domain.content.Award;
import com.intransition.labs.domain.user.User;
import com.intransition.labs.service.AwardType;
import com.intransition.labs.service.AwardService;
import org.springframework.stereotype.Service;

@Service
public class AwardServiceImpl implements AwardService {

	@Override
	public Award getAward(AwardType type) {
		return new Award( type );
	}
	
	public List<Award> sortAwardsByAwardedTime( Set<Award> awardsToSort ) {
		ArrayList<Award> awards = new ArrayList<Award>( awardsToSort );
		
		Collections.sort( awards, new Comparator<Award>(){
			public int compare(Award o1, Award o2) {
				if( o1.getAwardedTimestamp().after( o2.getAwardedTimestamp() ) ) return 1; else return -1;
			}
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
