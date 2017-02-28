package com.intransition.labs.service;

import java.util.List;
import java.util.Set;

import com.intransition.labs.domain.content.Chapter;

public interface ChapterService {

	List<Chapter> sortChaptersByOrder(Set<Chapter> chaptersToSort );
	
}
