package com.example.intro.servlets.user;

import com.example.intro.database.dao.UserDao;
import com.example.intro.database.entity.User;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

//@WebServlet(name = "UserDelete", value = "/user/delete/*")
public class UserDelete extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        var emf = (EntityManagerFactory) getServletContext().getAttribute("entityManagerFactory");
        userDao = new UserDao(emf);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            req.setAttribute("username", user.getUsername());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user_delete.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        userDao.delete(id);
        resp.sendRedirect("/user/list");
    }
}
