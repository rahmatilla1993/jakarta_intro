package com.example.intro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public final class Utils {

    private Utils() {

    }

    public static String getImageAsBase64(Path path) {
        Base64.Encoder encoder = Base64.getEncoder();
        try {
            byte[] bytes = Files.readAllBytes(path);
            return encoder.encodeToString(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
