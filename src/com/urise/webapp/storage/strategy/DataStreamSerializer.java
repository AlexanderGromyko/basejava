package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializerStrategy {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            for (SectionType sectionType : SectionType.values()) {
                writeSection(sectionType, resume, dos);
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            for (int i = 0; i < 6; i++) {
                readSection(SectionType.valueOf(dis.readUTF()), resume, dis);
            }
            return resume;
        }
    }

    private void writeSection(SectionType sectionType, Resume resume, DataOutputStream dos) throws IOException {
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> {
                dos.writeUTF(sectionType.name());
                dos.writeUTF(resume.getSection(sectionType).toString());
            }
            case ACHIEVEMENTS, QUALIFICATIONS -> {
                ListSection listSection = (ListSection) resume.getSection(sectionType);
                dos.writeUTF(sectionType.name());
                dos.writeInt(listSection.getList().size());
                for (String line : listSection.getList()) {
                    dos.writeUTF(line);
                }
            }
            case EXPERIENCE, EDUCATION -> {
                OrganizationSection organizationSection = (OrganizationSection) resume.getSection(sectionType);
                dos.writeUTF(sectionType.name());
                dos.writeInt(organizationSection.getList().size());
                for (Organization organization : organizationSection.getList()) {
                    dos.writeUTF(organization.getName());
                    dos.writeUTF(organization.getWebsite());
                    List<Period> periods = organization.getPeriods();
                    dos.writeInt(periods.size());
                    for (Period period : periods) {
                        dos.writeUTF(period.getDateFrom().toString());
                        dos.writeUTF(period.getDateTo().toString());
                        dos.writeUTF(period.getTitle());
                        dos.writeUTF(period.getDescription());
                    }
                }
            }
        }
    }

    private void readSection(SectionType sectionType, Resume resume, DataInputStream dis) throws IOException {
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> {
                resume.setSection(sectionType, new TextSection(dis.readUTF()));
            }
            case ACHIEVEMENTS, QUALIFICATIONS -> {
                ListSection listSection = new ListSection(new ArrayList<>());
                int size = dis.readInt();
                for (int i = 0; i < size; i++) {
                    listSection.addString(dis.readUTF());
                }
                resume.setSection(sectionType, listSection);
            }
            case EXPERIENCE, EDUCATION -> {
                OrganizationSection organizationSection = new OrganizationSection(new ArrayList<>());
                int size = dis.readInt();
                for (int i = 0; i < size; i++) {
                    Organization organization = new Organization(dis.readUTF(), dis.readUTF());
                    int periodsSize = dis.readInt();
                    for (int y = 0; y < periodsSize; y++) {
                        organization.addPeriod(new Period(LocalDate.parse(dis.readUTF()),
                                LocalDate.parse(dis.readUTF()),
                                dis.readUTF(),
                                dis.readUTF()));
                    }
                    organizationSection.addOrganization(organization);
                }
                resume.setSection(sectionType, organizationSection);
            }
        }
    }
}
