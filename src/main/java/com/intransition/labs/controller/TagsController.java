package com.intransition.labs.controller;

import java.util.List;

import com.intransition.labs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TagsController {
	
	@Autowired
	private TagService tagService;
	
	/*
	@RequestMapping( value = "/tags/{tag}" )
	public @ResponseBody List<String> searchByTag( @PathVariable( value = "tag", required = true ) String tag, Model model ) {
		return tagService.autocomplete(tag);
	}
	
	*/
	
	@RequestMapping( value = "/tags", method = RequestMethod.POST )
	public @ResponseBody List<String> autocomplete( @RequestBody( required = true ) String tag ) {
		return tagService.autocomplete( tag );
	}
	
	
}
