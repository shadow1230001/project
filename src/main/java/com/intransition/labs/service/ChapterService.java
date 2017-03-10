package com.intransition.labs.service;

import com.intransition.labs.domain.content.Chapter;

import java.util.List;
import java.util.Set;

public interface ChapterService {

    List<Chapter> sortChaptersByOrder(Set<Chapter> chaptersToSort);

}
