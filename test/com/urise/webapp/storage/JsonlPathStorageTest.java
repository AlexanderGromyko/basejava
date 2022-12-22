package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.JsonStreamSerializer;

public class JsonlPathStorageTest extends AbstractStorageTest {
    public JsonlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new JsonStreamSerializer()));
    }
}