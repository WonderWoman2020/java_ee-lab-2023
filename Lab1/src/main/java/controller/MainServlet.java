package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/api/*")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        /*PrintWriter out = resp.getWriter();
        out.write("<h1>");
        out.write("Hello world!");
        out.write("<h1>");
        out.write("<h2>"+req.getPathInfo()+"<h2>");*/
        //getServletContext().getRequestDispatcher("/api/users/*").forward(req, resp);
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
