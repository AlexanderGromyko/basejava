package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage{
    protected ArrayList<Resume> storage = new ArrayList<>();

    public final int size() {
        return storage.size();
    }

    @Override
    public int getStorageLimit() {
        return Integer.MAX_VALUE;
    }

    public final void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected final Resume getByIndex(int index) {
        return storage.get(index);
    }

    @Override
    protected final void updateByIndex(int index, Resume resume) {
        storage.set(index, resume);
    }

    public final void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            storage.add(r);
        }
    }

    @Override
    protected final void deleteByIndex(int index) {
        storage.remove(index);
    }

    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }
}
