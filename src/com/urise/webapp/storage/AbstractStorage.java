package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public final Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public final void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    public final void update(Resume r) {
        Object searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    private Object getExistingSearchKey(Object anyKey) {
        Object searchKey = getSearchKey(anyKey);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(anyKey);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(Object anyKey) {
        Object searchKey = getSearchKey(anyKey);
        if (isExist(searchKey)) {
            throw new ExistStorageException(searchKey);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);

    //protected abstract Object getSearchKey(Resume r);

    protected abstract Object getSearchKey(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);
}
