package com.example.intro.servlets;

import com.example.intro.dao.UserDao;
import com.example.intro.domain.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UserDelete", value = "/user/delete/*")
public class UserDelete extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao = UserDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDao.findById(req.getPathInfo().substring(1));
        req.setAttribute("username", user.getUsername());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user_delete.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        userDao.delete(id);
        resp.sendRedirect("/users");
    }
}
