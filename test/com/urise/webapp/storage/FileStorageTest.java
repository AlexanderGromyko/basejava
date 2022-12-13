package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.FileStorage;
import com.urise.webapp.storage.strategy.StreamStrategy;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new StreamStrategy()));
    }
}