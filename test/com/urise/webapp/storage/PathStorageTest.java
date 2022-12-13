package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.PathStorage;
import com.urise.webapp.storage.strategy.StreamStrategy;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new StreamStrategy()));
    }
}