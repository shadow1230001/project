package com.intransition.labs.service;

import com.intransition.labs.domain.content.Award;
import com.intransition.labs.domain.user.User;

import java.util.List;
import java.util.Set;

public interface AwardService {

    Award getAward(AwardType type);

    List<Award> sortAwardsByAwardedTime(Set<Award> awardsToSort);

    Award giveUserAward(User user, AwardType type);

}
