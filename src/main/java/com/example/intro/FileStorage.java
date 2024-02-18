package com.example.intro;

public class FileStorage {
    private String originalFileName;
    private String generatedName;
    private String contentType;

    public FileStorage(String originalFileName, String generatedName, String contentType) {
        this.originalFileName = originalFileName;
        this.generatedName = generatedName;
        this.contentType = contentType;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getGeneratedName() {
        return generatedName;
    }

    public void setGeneratedName(String generatedName) {
        this.generatedName = generatedName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
