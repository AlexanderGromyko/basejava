package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.*;

public class AbstractFileStorage {
    private final static String RESUME_DIRECTORY = ".//resume";

    public void saveResume(Resume resume) throws IOException {
        if (dirExist()) {
            try (FileOutputStream outputStream = new FileOutputStream(RESUME_DIRECTORY + "/" + resume.getUuid() + ".res")) {
                doSave(resume, outputStream);
            }
        } else throw new IOException("Directory " + RESUME_DIRECTORY + " doesn't exist!");
    }

    public Resume getResume(String uuid) throws IOException, ClassNotFoundException {
        if (dirExist()) {
            try (FileInputStream InputStream = new FileInputStream(RESUME_DIRECTORY + "/" + uuid + ".res")) {
                return doGet(InputStream);
            }
        } else throw new IOException("Directory " + RESUME_DIRECTORY + " doesn't exist!");
    }

    private void doSave(Resume resume, FileOutputStream outputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(resume);
        objectOutputStream.close();
    }

    private Resume doGet(FileInputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Resume resume = (Resume) objectInputStream.readObject();
        objectInputStream.close();
        return resume;
    }

    private boolean dirExist() {
        File dir = new File(RESUME_DIRECTORY);
        return dir.mkdir() || dir.exists();
    }
}
