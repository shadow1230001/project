package com.intransition.labs.service;

import com.intransition.labs.domain.content.Award;
import com.intransition.labs.domain.user.User;

import java.util.List;
import java.util.Set;

/**
 * Award Service.
 */

public interface AwardService {
    /**
     *
     * @param type Passes the type indicator enum
     * @return Returns an instance of bean
     */

    Award getAward(AwardType type);

    /**
     *
     * @param awardsToSort 
     * @return
     */
    List<Award> sortAwardsByAwardedTime(Set<Award> awardsToSort);

    /**
     * 
     * @param user
     * @param type
     * @return
     */

    Award giveUserAward(User user, AwardType type);

}
