package com.example.intro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ConvertServlet", value = "/convert")
public class ConvertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numberStr = req.getParameter("number");
        int fromBase = Integer.parseInt(req.getParameter("fromBase"));
        int toBase = Integer.parseInt(req.getParameter("toBase"));
        try {
            int number = Integer.parseInt(numberStr, fromBase);
            String result = Integer.toString(number, toBase);
            req.setAttribute("result", result);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid number format");
        }
        req.getRequestDispatcher("convert_result.jsp").forward(req, resp);
    }
}
