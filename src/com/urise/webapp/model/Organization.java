package com.urise.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private final List<Period> periods;
    private String name;
    private String website;

    public Organization(String name) {
        this(name, "", new ArrayList<>());
    }

    public Organization(String name,String website) {
        this(name, website, new ArrayList<>());
    }

    public Organization(String name,String website, List<Period> periods) {
        this.name = name;
        this.website = website;
        this.periods = periods;
    }

    public void setWebsite(String website) {
        this.website = website;
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