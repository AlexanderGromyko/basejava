package com.urise.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Organization> organizations;

    public OrganizationSection() {
        this(new ArrayList<>());
    }

    public OrganizationSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizations = organizations;
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        organizations.stream().forEach((organization) -> {
            str.append("\norganization: " + organization.getName());
            str.append("\nwebsite: " + organization.getWebsite());
            str.append("\n");
            for (Period period : organization.getPeriods()) {
                str.append(period);
            }
        });
        return str.toString();
    }

    public List<Organization> getList() {
        return organizations;
    }
}