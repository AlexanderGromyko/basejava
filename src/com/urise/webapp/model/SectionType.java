package com.urise.webapp.model;

import java.io.Serializable;

public enum SectionType implements Serializable {
    PERSONAL("Личные качеситва"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENTS("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}