package com.example.intro;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@WebServlet(name = "ImageListServlet", value = "/images")
public class ImageListServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Stream<Path> pathStream = Files.walk(rootPath)) {
            List<String> images = pathStream
                    .filter(Files::isRegularFile)
                    .map(Utils::getImageAsBase64)
                    .toList();
            req.setAttribute("images", images);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/files/image_list.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
