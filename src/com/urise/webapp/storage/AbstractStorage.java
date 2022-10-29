package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExist(searchKey)) {
            getExistingSearchKey(r.getUuid());
        } else {
            doSave(r, searchKey);
        }
    }

    public final Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            getNotExistingSearchKey(uuid);
        }
        return doGet(searchKey);
    }

    public final void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            getNotExistingSearchKey(uuid);
        }
        doDelete(searchKey);
    }

    public final void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (!isExist(searchKey)) {
            getNotExistingSearchKey(r.getUuid());
        } else {
            doUpdate(r, searchKey);
        }
    }

    private void getExistingSearchKey(String uuid) {
        throw new ExistStorageException(uuid);
    }

    private void getNotExistingSearchKey(String uuid) {
        throw new NotExistStorageException(uuid);
    }

    protected abstract boolean isExist(Object searchKey);

    //protected abstract Object getSearchKey(Resume r);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);
}
