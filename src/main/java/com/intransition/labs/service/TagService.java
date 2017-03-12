package com.intransition.labs.service;

import com.intransition.labs.domain.content.Tag;

import java.util.List;

/**
 * User service.Resonsible for operations with tags
 */
public interface TagService {

    /**
     * get all tags from db
     * @return
     */
    List<Tag> getAllTags();

    List<String> autocomplete(String name);

}
