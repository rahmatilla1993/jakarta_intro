package com.example.intro.servlets.group;

import com.example.intro.database.dao.GroupDao;
import com.example.intro.database.entity.Group;
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
import java.util.Map;

@WebServlet(name = "GroupEditServlet", value = "/group/edit/*")
public class GroupEditServlet extends HttpServlet {
    private GroupDao groupDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        var emf = (EntityManagerFactory) getServletContext().getAttribute("entityManagerFactory");
        groupDao = new GroupDao(emf);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        Group group = groupDao.getOne(id);
        req.setAttribute("group", group);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/group/group_edit.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        String groupName = req.getParameter("name");
        int studentsCount = Integer.parseInt(req.getParameter("students_count"));
        Group group = Group.builder()
                .studentsCount(studentsCount)
                .name(groupName)
                .id(id)
                .build();
        Map<String, String> errors = ValidationUtils.validate(group);
        if (errors.isEmpty()) {
            groupDao.update(group);
            resp.sendRedirect("/group/list");
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/group/group_edit.jsp");
            req.setAttribute("errors", errors);
            dispatcher.forward(req, resp);
        }
    }
}
