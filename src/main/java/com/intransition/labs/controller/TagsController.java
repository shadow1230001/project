package com.intransition.labs.controller;

import com.intransition.labs.domain.content.Creative;
import com.intransition.labs.domain.content.Tag;
import com.intransition.labs.service.CreativeService;
import com.intransition.labs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class TagsController {
	
	@Autowired
	private TagService tagService;

	@Autowired
	private CreativeService creativeService;

	@RequestMapping( value = "/tags/{tag}", method = RequestMethod.GET )
	public String searchByTag(@PageableDefault( size = 5 ) Pageable pageable, @PathVariable( value = "tag", required = true ) String tag, Model model ) {
		//return "nu tut karoch poisk po tegy.";
		//model.addAttribute( "tags", tagService.getAllTags() );

		//Page<Creative> creatives = creativeService.findAllByOrderByCreatedDesc(pageable);


		//creatives.
		List<Creative> creatives = creativeService.findAllByOrderByCreatedDesc(pageable).getContent().parallelStream().filter(creative -> {
			return containsTag(creative.getTags(), tag);
		}).collect(Collectors.toList());
		//Page<Creative> creatives = creativeService.findAllByTagsInTagsOrderByEditedDesc(tag, pageable);

		//pageable.
		model.addAttribute( "creatives", creatives );

		return "searchbytag";
	}

	private boolean containsTag(Set<Tag> tags, String tag ) {
		for( Tag tg : tags ) {
			if( tg.getName().equalsIgnoreCase(tag) ) return true;
		}
		return false;
	}

	@RequestMapping( value = "/tags", method = RequestMethod.POST )
	public @ResponseBody List<String> autocomplete( @RequestBody( required = true ) String tag ) {
		return tagService.autocomplete( tag );
	}
	
	
}
