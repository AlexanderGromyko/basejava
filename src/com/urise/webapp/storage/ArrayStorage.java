package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void update(Resume resume) {
        int index = findResume(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not found!");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = findResume(r.getUuid());
        if (size == storage.length) {
            System.out.println("Unable to save resume " + r.getUuid() + ". Storage is full!");
        } else if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " is already in storage!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = findResume(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " not found!");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = findResume(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " not found!");
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    private int findResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}