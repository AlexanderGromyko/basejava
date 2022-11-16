package com.urise.webapp.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends AbstractSection{
    private List<Organization> organizations;

    public OrganizationSection() {
        this(new ArrayList<>());
    }

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = new ArrayList(organizations);
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = new ArrayList<>(organizations);
    }

    @Override
    public void print() {
        organizations.stream().forEach((organization) -> {
            System.out.println("organization: " + organization.getName());
            System.out.println("website: " + organization.getWebsite());
            for (Period period : organization.getPeriods()) {
                System.out.println("from : " + period.getDateFrom().format(DateTimeFormatter.ofPattern("MM/YYYY")));
                System.out.println("to : " + period.getDateTo().format(DateTimeFormatter.ofPattern("MM/YYYY")));
                System.out.println(period.getTitle());
                if (period.getDescription() != "") System.out.println(period.getDescription());
                System.out.println();
            }
        });
    }
}
