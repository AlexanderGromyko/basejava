package com.urise.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(contacts, resume.contacts)
                && Objects.equals(sections, resume.sections)
                && Objects.equals(uuid, resume.uuid)
                && Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contacts, sections, uuid, fullName);
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