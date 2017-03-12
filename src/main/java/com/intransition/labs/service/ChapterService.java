package com.intransition.labs.service;

import com.intransition.labs.domain.content.Chapter;

import java.util.List;
import java.util.Set;

public interface ChapterService {
    /**
     *
     * @param chaptersToSort collection Chapter
     * @return collection Chapter sorted by Order
     */

    List<Chapter> sortChaptersByOrder(Set<Chapter> chaptersToSort);

}
