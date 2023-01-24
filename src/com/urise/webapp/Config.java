package com.urise.webapp;

import com.urise.webapp.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPERTIES = new File("C://Java//basejava//basejava//config//resumes.properties");
    private static final Config INSTANCE = new Config();
    private Properties properties = new Properties();
    private File storageDir;
    private SqlStorage storage;

    private Config() {
        try (InputStream is = new FileInputStream(PROPERTIES)) {
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            storage = new SqlStorage(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config fil " + PROPERTIES.getAbsolutePath());
        }
    }

    public static Config get() {
        return INSTANCE;
    }

    public SqlStorage getStorage() {
        return storage;
    }

    public File getStorageDir() {
        return storageDir;
    }
}