package com.urise.webapp;

import com.urise.webapp.exception.StorageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainFile {
    public static void main(String[] args) {
        Path dir = Paths.get(("."));
        listFiles(dir);
    }

    private static void listFiles(Path dir) {
        final int iterationNumber = 1;
        listFiles(dir, iterationNumber);
    }
    private static void listFiles(Path dir, int iteration) {
        try {
            Files.list(dir).forEach((file) -> {
                if (Files.isDirectory(file)) {
                    System.out.println(getPathString(file, iteration));
                    listFiles(file, iteration + 1);
                } else System.out.println(getPathString(file, iteration));
            });
        } catch (IOException e) {
            throw new StorageException("Error while listing files in directory", dir.getFileName().toString());
        }
    }

    private static String getPathString(Path path, int iteration) {
        return "\t".repeat(iteration - 1) + path.getFileName().toString();
    }
}
