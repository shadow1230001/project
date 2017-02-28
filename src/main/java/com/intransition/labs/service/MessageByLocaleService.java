package com.intransition.labs.service;

public interface MessageByLocaleService {

    public String getMessage(String id);
    
    public String getMessage( String id, Object[] args );

}