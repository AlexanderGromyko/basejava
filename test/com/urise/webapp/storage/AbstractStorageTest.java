package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private final Resume RESUME_1 = new Resume(UUID_1);
    private final Resume RESUME_2 = new Resume(UUID_2);
    private final Resume RESUME_3 = new Resume(UUID_3);
    private final Resume RESUME_4 = new Resume(UUID_4);
    final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setup() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    private void assertSize(int s) {
        Assertions.assertEquals(s, storage.size());
    }

    private void assertGet(Resume r) {
        Assertions.assertEquals(storage.get(r.getUuid()), r);
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        Resume[] expected = {};
        Assertions.assertArrayEquals(expected, storage.getAll());
    }

    @Test
    void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Assertions.assertArrayEquals(expected, storage.getAll());
        assertSize(expected.length);
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void update() {
        Resume expectedResume = new Resume(UUID_1);
        storage.update(expectedResume);
        Assertions.assertSame(expectedResume, storage.get(expectedResume.getUuid()));
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    void delete() {
        storage.delete(RESUME_2.getUuid());
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> assertGet(RESUME_2));
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get(RESUME_4.getUuid()));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_4));
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(RESUME_4.getUuid()));
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }
}