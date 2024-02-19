package com.example.intro.servlets.student;

import com.example.intro.database.dao.GroupDao;
import com.example.intro.database.dao.StudentDao;
import com.example.intro.database.domain.Group;
import com.example.intro.database.domain.Student;
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
        studentDao = StudentDao.getInstance();
        groupDao = GroupDao.getInstance();
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
        Student student = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .groupId(groupId)
                .build();
        studentDao.save(student);
        resp.sendRedirect("/student/list");
    }
}
