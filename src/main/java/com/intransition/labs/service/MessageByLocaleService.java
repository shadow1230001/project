package com.intransition.labs.service;

public interface MessageByLocaleService {
    /**
     *
     * @param id message
     * @return Returns a string type message
     */
    public String getMessage(String id);

    /**
     *
     * @param id id message
     * @param args id Objects Array
     * @return  Returns a string type message
     */

    public String getMessage(String id, Object[] args);

}