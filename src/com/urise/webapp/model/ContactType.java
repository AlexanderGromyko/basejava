package com.urise.webapp.model;

public enum ContactType {
    PHONE("Phone number"),
    SKYPE("skype"),
    EMAIL("email"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHUH"),
    STACKOVERFLOW("Stackoverflow"),
    HOMEPAGE("homepage");

    private  String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
