package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected Object getSearchKey(Object searchKey) {
        if (storage.containsKey((String) searchKey)) {
            return searchKey;
        }
        return null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.replace((String) searchKey, r);
    }
}

