package com.intransition.labs.service;

public enum AwardType {

    SUCCESSFUL_REGISTRATION("award.registration.name", "award.registration.codename", "award.registration.description"),
    FIRST_CREATIVE("award.firstpost.name", "award.firstpost.codename", "award.firstpost.description"),
    FIFTH_CREATIVE("award.fifthpost.name", "award.fifthpost.codename", "award.firstpost.description"),
    TENTH_CREATIVE("award.tenthpost.name", "award.tenthpost.codename", "award.tenthpost.description");

    private final String name, codename, description;

    private AwardType(String name, String codename, String description) {
        this.name = name;
        this.codename = codename;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCodename() {
        return codename;
    }

    public String getDescription() {
        return description;
    }

}
