package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public List<Resume> doGetAllSorted(Comparator<Resume> resumeComparator) {
        return Arrays.stream(storage).sorted(resumeComparator).collect(Collectors.toList());
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        saveResume(r, searchKey);
        size++;
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteResume(searchKey);
        storage[size - 1] = null;
        size--;
    }

    abstract void saveResume(Resume r, Object searchKey);

    abstract void deleteResume(Object searchKey);
}