package com.example.intro.servlets.group;

import com.example.intro.database.dao.GroupDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "GroupServlet", value = "/group/list")
public class GroupListServlet extends HttpServlet {

    private GroupDao groupDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        groupDao = GroupDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("groups", groupDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/group/group_list.jsp");
        dispatcher.forward(req, resp);
    }
}
