package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage{
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final int size() {
        return size;
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected final Resume getByIndex(int index) {
        return storage[index];
    }

    @Override
    protected final void updateByIndex(int index, Resume resume) {
        storage[index] = resume;
    }

    public final void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size == storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveInArray(r, index);
        }
    }

    @Override
    protected final void deleteByIndex(int index) {
        deleteFromArray(index);
    }

    public final int getStorageLimit() {
        return STORAGE_LIMIT;
    }

    protected abstract void saveInArray(Resume r, int i);

    protected abstract void deleteFromArray(int i);

    protected abstract int getIndex(String uuid);


}