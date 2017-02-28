package com.intransition.labs.controller;

import com.intransition.labs.domain.content.Award;
import com.intransition.labs.domain.user.User;
import com.intransition.labs.service.AwardService;
import com.intransition.labs.service.CreativeService;
import com.intransition.labs.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping( value = "personal" )
public class PersonalController {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private AwardService awardService;
	
	@Autowired
	private CreativeService creativeService;
	
	@ModelAttribute("awards")
	private List<Award> getAwards() {
		return awardService.sortAwardsByAwardedTime( securityService.getLoggedInUser().getAwards() );
	}
	
	@RequestMapping( method = RequestMethod.GET )
	public String get( Model model ) {
		User user = securityService.getLoggedInUser();
		
		model.addAttribute( "user", user );
		model.addAttribute( "creatives", creativeService.sortCreativesByCreatedDesc( user.getCreatives() ) );
		
		return "personal/personal";
	}
		
}
