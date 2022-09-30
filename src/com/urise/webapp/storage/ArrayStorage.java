package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int storageSize;

    public void update(Resume resume) {
        int i = findResume(resume.getUuid());
        if (i >= 0) {
            storage[i] = resume;
        } else System.out.println("Resume " + resume.getUuid() + " not found!");
    }

    public void clear() {
        if (storageSize > 0) {
            Arrays.fill(storage, 0, storageSize - 1, null);
            storageSize = 0;
        }
    }

    public void save(Resume r) {
        int i = findResume(r.getUuid());
        if (i >= 0) {
            System.out.println("Resume " + r.getUuid() + " is already in storage!");
        }
        if (storageSize < storage.length) {
            storage[storageSize] = r;
            storageSize++;
        } else System.out.println("Unable to save resume " + r.getUuid() + ". Storage is full!");
    }

    public Resume get(String uuid) {
        int i = findResume(uuid);
        if (i >= 0) {
            return storage[i];
        }
        System.out.println("Resume with uuid " + uuid + " not found!");
        return null;
    }

    public void delete(String uuid) {
        int i = findResume(uuid);
        if (i >= 0) {
            storage[i] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        } else System.out.println("Resume with uuid " + uuid + " not found!");
    }

    private int findResume(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int size() {
        return storageSize;
    }
}
