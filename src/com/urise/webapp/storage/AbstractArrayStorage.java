package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    final public int size() {
        return size;
    }

    final public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    final public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    final public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    final public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " not found!");
        } else {
            storage[index] = resume;
        }
    }

    final public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size == storage.length) {
            System.out.println("Unable to save resume " + r.getUuid() + ". Storage is full!");
        } else if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " is already in storage!");
        } else {
            saveInArray(r, index);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " not found!");
            return;
        }
        deleteFromArray(index);
    }

    protected abstract void saveInArray(Resume r, int i);

    protected abstract void deleteFromArray(int i);

    protected abstract int getIndex(String uuid);
}