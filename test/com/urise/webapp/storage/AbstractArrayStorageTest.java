package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private final Resume RESUME_1 = new Resume(UUID_1);
    private final Resume RESUME_2 = new Resume(UUID_2);
    private final Resume RESUME_3 = new Resume(UUID_3);
    private final Resume RESUME_4 = new Resume(UUID_4);
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
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
        NotExistStorageException exception = Assertions.assertThrows(NotExistStorageException.class, () -> {
            assertGet(RESUME_2);
        });
        Assertions.assertEquals("Resume uuid2 not exist", exception.getMessage());
    }

    @Test
    void getNotExist() {
        NotExistStorageException exception = Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(RESUME_4.getUuid());
        });
        Assertions.assertEquals("Resume uuid4 not exist", exception.getMessage());
    }

    @Test
    void updateNotExist() {
        NotExistStorageException exception = Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(RESUME_4);
        });
        Assertions.assertEquals("Resume uuid4 not exist", exception.getMessage());
    }

    @Test
    void deleteNotExist() {
        NotExistStorageException exception = Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(RESUME_4.getUuid());
        });
        Assertions.assertEquals("Resume uuid4 not exist", exception.getMessage());
    }

    @Test
    void saveExist() {
        ExistStorageException exception = Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(RESUME_1);
        });
        Assertions.assertEquals("Resume uuid1 already exists", exception.getMessage());
    }

    @Test
    void storageOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < storage.getStorageLimit(); i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assertions.fail("Storage overflow too early");
        }
        StorageException exception = Assertions.assertThrows(StorageException.class, () -> {
            storage.save(new Resume("dummy"));
        });
        Assertions.assertEquals("Storage overflow", exception.getMessage());
    }
}