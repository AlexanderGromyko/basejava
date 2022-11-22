package com.urise.webapp;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File dir = new File(".");
        listFiles(dir);
    }

    private static void listFiles(File dir) {
        File [] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isDirectory()) listFiles(file);
                else System.out.println(file.getName());
            }
        }
    }
}
