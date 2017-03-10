package com.intransition.labs.service;

import com.intransition.labs.domain.content.Tag;

import java.util.List;

public interface TagService {

    List<Tag> getAllTags();

    List<String> autocomplete(String name);

}
