package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class DataStreamSerializer implements SerializerStrategy {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            ThrowingConsumer <Map.Entry<ContactType, String>> contactConsumer = entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            };
            ThrowingConsumer<String> listSectionConsumer = stringItem -> {
                dos.writeUTF(stringItem);
            };
            ThrowingConsumer<Period> periodConsumer = period -> {
                dos.writeUTF(period.getDateFrom().toString());
                dos.writeUTF(period.getDateTo().toString());
                dos.writeUTF(period.getTitle());
                dos.writeUTF(period.getDescription());
            };
            ThrowingConsumer<Organization> organizationSectionConsumer = organization -> {
                dos.writeUTF(organization.getName());
                dos.writeUTF(organization.getWebsite());
                writeWithException(organization.getPeriods(), periodConsumer, dos);
            };

            writeWithException(resume.getContacts().entrySet(), contactConsumer, dos);

            for (SectionType sectionType : SectionType.values()) {
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> {
                        dos.writeUTF(((TextSection) resume.getSection(sectionType)).getContent());
                    }
                    case ACHIEVEMENTS, QUALIFICATIONS -> {
                        writeWithException(((ListSection) resume.getSection(sectionType)).getList(), listSectionConsumer, dos);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        writeWithException(((OrganizationSection) resume.getSection(sectionType)).getList(), organizationSectionConsumer, dos);
                    }
                }
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
            for (int i = 0; i < SectionType.values().length; i++) {
                readSection(SectionType.valueOf(dis.readUTF()), resume, dis);
            }
            return resume;
        }
    }

    private <T> void writeWithException(Collection<T> collection, ThrowingConsumer<T> consumer, DataOutputStream dos) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            consumer.write(item);
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

    private interface ThrowingConsumer<T> {
        void write(T t) throws IOException;
    }
}