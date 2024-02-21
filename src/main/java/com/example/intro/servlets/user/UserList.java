package com.example.intro.servlets.user;

import com.example.intro.database.dao.GroupDao;
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
import java.util.List;

//@WebServlet(name = "UserList", value = "/users")
public class UserList extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        var emf = (EntityManagerFactory) getServletContext().getAttribute("entityManagerFactory");
        userDao = new UserDao(emf);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = userDao.getAll();
        list.forEach(System.out::println);
        req.setAttribute("users", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/user_list.jsp");
        dispatcher.forward(req, resp);
    }
}