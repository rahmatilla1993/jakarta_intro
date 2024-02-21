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

@WebServlet(name = "GroupAddServlet", value = "/group/add")
public class GroupAddServlet extends HttpServlet {
    private GroupDao groupDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        var emf = (EntityManagerFactory) getServletContext().getAttribute("entityManagerFactory");
        groupDao = new GroupDao(emf);
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
        Map<String, String> errors = ValidationUtils.validate(group);
        if (errors.isEmpty()) {
            groupDao.save(group);
            resp.sendRedirect("/group/list");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/group/group_add.jsp");
            req.setAttribute("errors", errors);
            dispatcher.forward(req, resp);
        }
    }
}
