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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "GroupAddServlet", value = "/group/add")
public class GroupAddServlet extends HttpServlet {
    private GroupDao groupDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        groupDao = GroupDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/group/group_add.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupName = req.getParameter("name");
        int studentsCount = Integer.parseInt(req.getParameter("students_count"));
        Group group = Group.builder()
                .name(groupName)
                .studentsCount(studentsCount)
                .build();
        HttpSession session = req.getSession();
        String userId =(String) session.getAttribute("userId");
        groupDao.save(group, userId);
        resp.sendRedirect("/group/list");
    }
}
