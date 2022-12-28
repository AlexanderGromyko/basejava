package com.urise.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Period> periods;
    private String name;
    private String website;

    public Organization() {
    }

    public Organization(String name) {
        this(name, "", new ArrayList<>());
    }

    public Organization(String name,String website) {
        this(name, website, new ArrayList<>());
    }

    public Organization(String name,String website, List<Period> periods) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.website = (website == null) ? "" : website;
        this.periods = Objects.requireNonNull(periods, "periods must not be null");
    }

    public void addPeriod(Period period) {
        this.periods.add(period);
    }

    public List<Period> getPeriods() {
        return new ArrayList<>(periods);
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return periods.equals(that.periods) && name.equals(that.name) && Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periods, name, website);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "periods=" + periods +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}