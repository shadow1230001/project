package com.intransition.labs.service;

import com.intransition.labs.domain.user.User;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User Service. Responsible for operations with users
 */
public interface UserService {

    /**
     * When user signs up
     *
     * @param user object
     */
    void register(User user);

    /**
     * Search user by nick name
     *
     * @param nickname nickname to search
     * @return user if found. Otherwise null
     */
    User findByNickname(String nickname);

    /**
     * Search user by email
     *
     * @param email email to search
     * @return user if found. Otherwise null
     */
    User findByEmail(String email);

    /**
     * Validate email for existence
     *
     * @param email email to validate
     * @return true if email exists otherwise false
     */
    boolean existsEmail(String email);

    /**
     * Authorise user in VKontakte
     *
     * @param email users email
     * @param vk    api client
     * @param actor api actor
     */
    void registerByVK(String email, VkApiClient vk, UserActor actor);

    /**
     * Get all users by pages
     *
     * @param pageable number of elements on the page, sorting and page number
     * @return page with user objects
     */
    Page<User> getProfiles(Pageable pageable);

}
