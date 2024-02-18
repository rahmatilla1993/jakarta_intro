package com.example.intro;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@WebServlet(name = "FileDownloadServlet", value = "/download")
public class FileDownloadServlet extends HttpServlet {

    private static final Path rootPath = Path.of(System.getProperty("user.home"), "/images/f1");
    private DataBase dataBase;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dataBase = DataBase.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");
        try (Stream<Path> pathStream = Files.walk(rootPath)) {
            FileStorage fileStorage = dataBase.get(fileName);
            Path file = pathStream
                    .filter(path -> path.getFileName().toString().equals(fileStorage.getGeneratedName()))
                    .findFirst()
                    .orElseThrow(() -> new FileNotFoundException("File not found"));
            resp.setHeader("Content-Type", fileStorage.getContentType());
            resp.setHeader("Content-Disposition", "attachment; filename=\"%s\"".formatted(fileStorage.getOriginalFileName()));
            resp.getOutputStream().write(Files.readAllBytes(file));
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }
    }
}
