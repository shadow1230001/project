package com.intransition.labs.service.impl;

import com.intransition.labs.domain.content.Creative;
import com.intransition.labs.repository.CreativeRepository;
import com.intransition.labs.service.CreativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service 
public class CreativeServiceImpl implements CreativeService {

	@Autowired
	private CreativeRepository creativeRepository; 
	
	@Transactional
	public Creative getCreative(int id) {
		return creativeRepository.findOne(id);
	}


	public List<Creative> sortCreativesByCreated(Set<Creative> creativesToSort ) {
		ArrayList<Creative> creatives = new ArrayList<Creative>( creativesToSort );
		
		Collections.sort( creatives, (o1, o2) -> {
            if( o1.getCreated().after( o2.getCreated() ) ) return 1; else return -1;
        });
		
		return creatives;
	}


	public List<Creative> sortCreativesByCreatedDesc( Set<Creative> creativesToSort ) {
		ArrayList<Creative> creatives = new ArrayList<Creative>( creativesToSort );
		
		Collections.sort( creatives, (o1, o2) -> {
            if( o2.getCreated().after( o1.getCreated() ) ) return 1; else return -1;
        });
		
		return creatives;
	}
	
	@Transactional
	public Page<Creative> findAllByOrderByCreatedDesc( Pageable pageable ) {
		return creativeRepository.findAllByOrderByCreatedDesc(pageable);
	}


	@Override
	public Page<Creative> findAllByOrderByEditedDesc(Pageable pageable) {
		return creativeRepository.findAllByOrderByEditedDesc(pageable);
	}

	//@Override
	//public Page<Creative> findAllByTagsInTagsOrderByEditedDesc(Collection<Tag> tags, Pageable pageable) {
	//	return creativeRepository.findAllByInTagsOrderByEditedDesc(tags, pageable);
	//}

}
