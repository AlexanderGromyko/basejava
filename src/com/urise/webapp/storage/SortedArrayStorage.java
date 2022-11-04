package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected final Integer getSearchKey(Object searchKey) {
        return Arrays.binarySearch(storage, 0, size, new Resume((String) searchKey, ""));
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