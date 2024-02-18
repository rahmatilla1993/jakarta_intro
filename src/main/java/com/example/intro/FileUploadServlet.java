package com.example.intro;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@WebServlet(name = "FileUploadServlet", value = "/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private DataBase dataBase;
    private static final Path rootPath = Path.of(System.getProperty("user.home"), "/images/f1");

    static {
        if (!Files.exists(rootPath)) {
            try {
                Files.createDirectory(rootPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        dataBase = DataBase.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/files/upload.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        String originalFileName = part.getSubmittedFileName();
        String contentType = part.getContentType();
        int dotIndex = originalFileName.lastIndexOf(".");
        String generatedName = String.format("%s.%s", UUID.randomUUID(), originalFileName.substring(dotIndex + 1));
        FileStorage fileStorage = new FileStorage(originalFileName, generatedName, contentType);
        dataBase.save(fileStorage);
        Files.copy(part.getInputStream(), rootPath.resolve(generatedName), StandardCopyOption.REPLACE_EXISTING);
        resp.sendRedirect("/images");
    }
}
