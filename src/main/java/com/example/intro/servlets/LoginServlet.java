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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao = UserDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        Optional<User> optionalUser = userDao.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            HttpSession session = req.getSession();
            System.out.println("session.getId() = " + session.getId());
            session.setAttribute("userId", user.getId());
            resp.sendRedirect("/users");
        } else {
            resp.sendError(404, "User not found");
        }
    }
}
