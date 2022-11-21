package com.urise.webapp.model;

import java.io.Serializable;

public enum ContactType implements Serializable {
    PHONE("Phone number"),
    SKYPE("skype"),
    EMAIL("email"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHUH"),
    STACKOVERFLOW("Stackoverflow"),
    HOMEPAGE("homepage");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}