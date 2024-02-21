package com.example.intro.servlets.user;

import com.example.intro.database.dao.UserDao;
import com.example.intro.database.entity.User;
import com.example.intro.utils.ValidationUtils;
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

//@WebServlet(name = "UserEdit", value = "/user/edit/*")
public class UserEdit extends HttpServlet {
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
            req.setAttribute("user", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user_edit.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        User user = User.builder()
                .username(username)
                .id(id)
                .build();
        var validateRes = ValidationUtils.validate(user);
        if (validateRes.isEmpty()) {
            userDao.edit(user);
            resp.sendRedirect("/user/list");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/user_edit.jsp");
            req.setAttribute("errors", validateRes);
            dispatcher.forward(req, resp);
        }
    }
}