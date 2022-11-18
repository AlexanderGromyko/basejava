package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private final List<Period> periods;
    private String name;
    private String website;

    public Organization(String name) {
        this(name, "");
        this.name = name;
    }

    public Organization(String name,String website) {
        this.name = name;
        this.website = website;
        this.periods = new ArrayList<>();
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
}