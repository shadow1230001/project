package com.intransition.labs.service.impl;

import com.intransition.labs.service.ThemeService;
import org.springframework.stereotype.Service;

@Service
public class ThemeServiceImpl implements ThemeService {

	public String getDefaultThemeName() {
		return "light";
	}
	
}
