package com.urise.webapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return dateTo.equals(period.dateTo) && title.equals(period.title) && Objects.equals(description, period.description) && dateFrom.equals(period.dateFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTo, title, description, dateFrom);
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("from : " + dateFrom.format(DateTimeFormatter.ofPattern("MM/YYYY")));
        str.append("\nto : " + dateTo.format(DateTimeFormatter.ofPattern("MM/YYYY")));
        str.append("\n" + title);
        if (description != "") str.append("\n" + description);
        return str.toString();
    }
}