package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.DataStreamSerializer;

public class DataStreamStorageTest extends AbstractStorageTest {
    public DataStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new DataStreamSerializer()));
    }
}