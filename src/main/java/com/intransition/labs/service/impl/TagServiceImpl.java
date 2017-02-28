package com.intransition.labs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.intransition.labs.domain.content.Tag;
import com.intransition.labs.repository.TagRepository;
import com.intransition.labs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;
		
	@Override
	public List<Tag> getAllTags() {
		return tagRepository.findAll();
	}

	@Override
	public List<String> autocomplete(String name) {
		List<Tag> tags = tagRepository.findFirst5ByNameStartingWith(name);
		
		ArrayList<String> autocompleteSuggestions = new ArrayList<String>();
		
		tags.forEach( t -> autocompleteSuggestions.add( t.getName() )  );
		
		return autocompleteSuggestions;
	}
	
	
	
}
