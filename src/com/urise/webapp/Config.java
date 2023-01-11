package com.urise.webapp;

import com.urise.webapp.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPERTIES = new File(".//config//resumes.properties");
    private static final Config INSTANCE = new Config();
    private Properties properties = new Properties();
    private File storageDir;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private SqlStorage storage;

    private Config() {
        try (InputStream is = new FileInputStream(PROPERTIES)) {
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
            storage = new SqlStorage(dbUrl, dbUser, dbPassword);
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

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public File getStorageDir() {
        return storageDir;
    }
}