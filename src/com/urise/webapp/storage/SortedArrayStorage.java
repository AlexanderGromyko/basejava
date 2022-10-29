package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected final Integer getSearchKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        System.arraycopy(storage, -(Integer) searchKey - 1, storage, -(Integer) searchKey, size + (Integer) searchKey + 1);
        storage[-(Integer) searchKey - 1] = r;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        System.arraycopy(storage, (Integer) searchKey + 1, storage, (Integer) searchKey, size - (Integer) searchKey - 1);
    }

}