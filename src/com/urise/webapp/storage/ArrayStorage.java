package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected final Integer getSearchKey(Object searchKey) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(searchKey)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        storage[size] = r;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage[(Integer) searchKey] = storage[size - 1];
    }
}