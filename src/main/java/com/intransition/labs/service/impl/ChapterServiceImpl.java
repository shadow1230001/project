package com.intransition.labs.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.intransition.labs.domain.content.Chapter;
import com.intransition.labs.service.ChapterService;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceImpl implements ChapterService {

	@Override
	public List<Chapter> sortChaptersByOrder(Set<Chapter> chaptersToSort) {
		ArrayList<Chapter> chapters = new ArrayList<Chapter>( chaptersToSort );
		
		Collections.sort( chapters, new Comparator<Chapter>(){
			public int compare( Chapter o1, Chapter o2 ) {
				if( o1.getChapterOrder() > o2.getChapterOrder() ) return 1; else return -1;
			}
		});
		return chapters;
	}

}
