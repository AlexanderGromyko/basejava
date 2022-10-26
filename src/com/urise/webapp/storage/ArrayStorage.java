package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected final Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        storage[size] = r;
        size++;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage[(Integer) searchKey] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
}