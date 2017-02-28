package com.intransition.labs.service.impl;

import java.util.Locale;

import com.intransition.labs.service.MessageByLocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

	@Autowired
    private MessageSource messageSource;

    @Override

    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id,null,locale);
    }
    
    public String getMessage( String id, Object[] args ) {
        Locale locale = LocaleContextHolder.getLocale();
    	return messageSource.getMessage(id, args, locale);
    }
}
