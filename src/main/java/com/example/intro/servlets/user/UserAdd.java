package com.example.intro.servlets.user;

import com.example.intro.database.dao.UserDao;
import com.example.intro.database.entity.User;
import com.example.intro.utils.ValidationUtils;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

//@WebServlet(name = "UserAdd", value = "/user/add")
public class UserAdd extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        var emf = (EntityManagerFactory) getServletContext().getAttribute("entityManagerFactory");
        userDao = new UserDao(emf);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/user_add.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        User user = User
                .builder()
                .username(username)
                .build();
        var errorMessages = ValidationUtils.validate(user);
        if (errorMessages.isEmpty()) {
            Optional<User> optionalUser = userDao.findByUsername(username);
            if (optionalUser.isPresent()) {
                resp.sendError(400, "This username already taken");
            } else {
                userDao.add(User.builder().username(username).build());
                resp.sendRedirect("/user/list");
            }
        } else {
//            System.out.println(errorMessages);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/user_add.jsp");
            req.setAttribute("errors", errorMessages);
            dispatcher.forward(req, resp);
        }
    }
}