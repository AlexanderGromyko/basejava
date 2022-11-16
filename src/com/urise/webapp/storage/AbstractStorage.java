package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public final void save(Resume r) {
        SK searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public final Resume get(String uuid) {
        SK searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public final void delete(String uuid) {
        SK searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    public final void update(Resume r) {
        SK searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    private SK getExistingSearchKey(String uuid) {
        SK existingSearchKey = getSearchKey(uuid);
        if (!isExist(existingSearchKey)) {
            LOG.warning("Resume " + uuid + " does not exist");
            throw new NotExistStorageException(uuid);
        }
        return existingSearchKey;
    }

    private SK getNotExistingSearchKey(String uuid) {
        SK existingSearchKey = getSearchKey(uuid);
        if (isExist(existingSearchKey)) {
            throw new ExistStorageException(existingSearchKey);
        }
        return existingSearchKey;
    }

    public List<Resume> getAllSorted() {
        List<Resume> allResumes = doCopyAll();
        allResumes.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return allResumes;
    }

    protected abstract List<Resume> doCopyAll();

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String Uuid);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);
}
