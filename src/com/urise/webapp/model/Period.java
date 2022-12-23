package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateTo;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateFrom;
    private String title;
    private String description;

    public Period() {
    }

    public Period(LocalDate dateFrom, LocalDate dateTo, String title, String description) {
        Objects.requireNonNull(dateFrom, "dateFrom must not be null");
        Objects.requireNonNull(dateTo, "dateTo must not be null");
        Objects.requireNonNull(title, "title must not be null");
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