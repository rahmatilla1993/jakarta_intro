package com.example.intro.servlets.student;

import com.example.intro.database.dao.GroupDao;
import com.example.intro.database.dao.StudentDao;
import com.example.intro.database.entity.Group;
import com.example.intro.database.entity.Student;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "StudentAddServlet", value = "/student/add")
public class StudentAddServlet extends HttpServlet {

    private StudentDao studentDao;
    private GroupDao groupDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        var emf = (EntityManagerFactory) getServletContext().getAttribute("entityManagerFactory");
        studentDao = new StudentDao(emf);
        groupDao = new GroupDao(emf);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("groups", groupDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/student/student_add.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int age = Integer.parseInt(req.getParameter("age"));
        int groupId = Integer.parseInt(req.getParameter("group"));
        Group group = groupDao.getOne(groupId);
        Student student = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .group(group)
                .build();
        studentDao.save(student);
        resp.sendRedirect("/student/list");
    }
}
