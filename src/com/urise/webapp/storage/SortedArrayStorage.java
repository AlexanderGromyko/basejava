package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected final Integer getSearchKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        System.arraycopy(storage, -(Integer) searchKey - 1, storage, -(Integer) searchKey, size + (Integer) searchKey + 1);
        storage[-(Integer) searchKey - 1] = r;
        size++;
    }

    @Override
    protected void doDelete(Object searchKey) {
        System.arraycopy(storage, (Integer) searchKey + 1, storage, (Integer) searchKey, size - (Integer) searchKey - 1);
        storage[size - 1] = null;
        size--;
    }
}