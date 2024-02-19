package com.example.intro.servlets.group;

import com.example.intro.database.dao.GroupDao;
import com.example.intro.database.domain.Group;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "GroupDeleteServlet", value = "/group/delete/*")
public class GroupDeleteServlet extends HttpServlet {
    private GroupDao groupDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        groupDao = GroupDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        Group group = groupDao.getOne(id);
        System.out.println(group);
        req.setAttribute("groupName", group.getName());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/group/group_delete.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        groupDao.delete(id);
        resp.sendRedirect("/group/list");
    }
}
