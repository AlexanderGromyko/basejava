package com.urise.webapp.model;

import java.time.LocalDate;

public class Period {
    private LocalDate dateTo;
    private String title;
    private String description;

    private LocalDate dateFrom;

    public Period(LocalDate dateFrom, LocalDate dateTo, String title, String description) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.title = title;
        this.description = description;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
