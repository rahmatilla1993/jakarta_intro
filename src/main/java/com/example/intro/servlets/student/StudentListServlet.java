package com.example.intro.servlets.student;

import com.example.intro.database.dao.StudentDao;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "StudentListServlet", value = "/student/list")
public class StudentListServlet extends HttpServlet {

    private StudentDao studentDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        var emf = (EntityManagerFactory) getServletContext().getAttribute("entityManagerFactory");
        studentDao = new StudentDao(emf);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", studentDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/student/student_list.jsp");
        dispatcher.forward(req, resp);
    }
}
