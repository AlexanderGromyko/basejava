package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    private final EnumMap<ContactType, String> contacts;
    private final EnumMap<SectionType, AbstractSection> sections;
    private String uuid;
    private String fullName;

    public Resume() {
        this(UUID.randomUUID().toString(), "");
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
        contacts = new EnumMap<>(ContactType.class);
        sections = new EnumMap<>(SectionType.class);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public EnumMap<ContactType, String> getContacts() {
        return contacts.clone();
    }

    public EnumMap<SectionType, AbstractSection> getSections() {
        return sections.clone();
    }

    public AbstractSection getSection(SectionType sectionType) {
        return sections.get(sectionType);
    }

    public void setContact(ContactType contactType, String contactValue) {
        contacts.put(contactType, contactValue);
    }

    public void setSection(SectionType sectionType, AbstractSection sectionValue) {
        sections.put(sectionType, sectionValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int compareResult = fullName.compareTo(o.fullName);
        if (compareResult != 0) return compareResult;
        return uuid.compareTo(o.uuid);
    }
}