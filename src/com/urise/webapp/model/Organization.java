package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private List<Period> periods;
    private String name;
    private String website;

    public Organization(String name) {
        this.name = name;
        this.periods = new ArrayList<>();
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setPeriods(List <Period> periods) {
        this.periods = new ArrayList<>(periods);
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