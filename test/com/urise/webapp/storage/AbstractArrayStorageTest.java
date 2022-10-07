package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }
    @BeforeEach
    public void fillStorage() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void size() {
        //fillStorage();
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void getAll() {
        //fillStorage();
        Storage storage1 = new ArrayStorage();
        storage1.save(new Resume(UUID_1));
        storage1.save(new Resume(UUID_2));
        storage1.save(new Resume(UUID_3));

        Resume[] resumeFromStorage = storage.getAll();
        Resume[] resumeFromStorage1 = storage1.getAll();
        for (int i = 0; i < storage.size(); i++) {
            if (!resumeFromStorage[i].equals(resumeFromStorage1[i])) {
                Assertions.fail("Resumes are not equals");
            }
        }
    }

    @Test
    void get() throws Exception {
        NotExistStorageException exception = Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
        Assertions.assertEquals("Resume dummy not exist", exception.getMessage());
    }

    @Test
    void update() {
        NotExistStorageException exception = Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(new Resume("dummy"));
        });
        Assertions.assertEquals("Resume dummy not exist", exception.getMessage());
    }

    @Test
    void save() {
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

    @Test
    void delete() {
        Storage storage1 = new ArrayStorage();
        storage1.save(new Resume(UUID_1));
        storage1.save(new Resume(UUID_3));

        storage.delete(UUID_2);

        Resume[] resumeFromStorage = storage.getAll();
        Resume[] resumeFromStorage1 = storage1.getAll();
        for (int i = 0; i < storage.size(); i++) {
            if (!resumeFromStorage[i].equals(resumeFromStorage1[i])) {
                Assertions.fail("Resumes are not equals");
            }
        }
        NotExistStorageException exception = Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
        Assertions.assertEquals("Resume dummy not exist", exception.getMessage());

    }
}