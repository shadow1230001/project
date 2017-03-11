package com.intransition.labs.dto;

import org.springframework.data.domain.Page;

public class ProfilePageJsonWrapper {

    private int totalPages;

    private int number;

    private boolean hasPrevious;

    private boolean hasNext;

    public ProfilePageJsonWrapper() {
    }

    public ProfilePageJsonWrapper(Page<?> page) {
        setTotalPages(page.getTotalPages());
        setNumber(page.getNumber());
        setHasPrevious(page.hasPrevious());
        setHasNext(page.hasNext());
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }


}
