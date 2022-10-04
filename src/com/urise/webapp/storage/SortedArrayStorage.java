package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInArray(Resume r, int index) {
        for (int i = size - 1; i >= (-index) - 1; i--) {
            storage[i + 1] = storage[i];
        }
        storage[-index - 1] = r;
        size++;
    }

    @Override
    protected void deleteFromArray(int index) {
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}