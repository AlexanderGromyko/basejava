package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapResumeStorage extends AbstractMapStorage {

    @Override
    protected Object getSearchKey(Object searchKey) {
        if (searchKey == null) return null;
        //return storage.get(((Resume) searchKey).getUuid());
        return storage.get((String) searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.replace(((Resume) searchKey).getUuid(), r);
    }
}

