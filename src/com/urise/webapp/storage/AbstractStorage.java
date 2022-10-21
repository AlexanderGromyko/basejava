package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected Object storage;

    public abstract void clear();

    public abstract Resume[] getAll();

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getByIndex(index);
    }

    protected abstract Resume getByIndex(int index);

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateByIndex(index, resume);
        }
    }

    protected abstract void updateByIndex(int index, Resume resume);

    public abstract void save(Resume r);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteByIndex(index);
    }

    protected abstract void deleteByIndex(int index);

    protected abstract int getIndex(String uuid);
}
