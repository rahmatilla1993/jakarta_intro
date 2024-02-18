package com.example.intro;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class DataBase {
    private static Path path = Path.of(System.getProperty("user.home"), "/database");
    private static final Path file = Path.of("files.txt");
    private static DataBase instance;

    private DataBase() {
    }

    static {
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
                path = path.resolve(file);
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            path = path.resolve(file);
        }
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void save(FileStorage fileStorage) {
        String originalFileName = fileStorage.getOriginalFileName();
        String contentType = fileStorage.getContentType();
        String generatedName = fileStorage.getGeneratedName();
        String row = String.format("%s:%s:%s%n", originalFileName, contentType, generatedName);
        try {
            Files.writeString(path, row, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileStorage get(String generatedName) {
        try {
            String result = Files.readAllLines(path)
                    .stream()
                    .filter(line -> {
                        String[] row = line.split(":");
                        return row[2].equals(generatedName);
                    })
                    .findFirst()
                    .orElseThrow(() -> new FileNotFoundException("File not found"));
            String[] row = result.split(":");
            return new FileStorage(row[0], row[2], row[1]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
