package me.liberi.sms.views;

import me.liberi.sms.controllers.StudentController;
import me.liberi.sms.dao.StudentHibernateDao;
import me.liberi.sms.models.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditStudent", value = "/edit-student")
public class EditStudent extends HttpServlet {
    StudentController controller = new StudentController();

    public EditStudent() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int studentId = Integer.parseInt(request.getParameter("id"));
        try {
            request.setAttribute("student", controller.getById(studentId));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("editStudent.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String year = request.getParameter("year");
        String className = request.getParameter("class");

        StudentController controller;
        try {
//            controller = new StudentController();
//            int result = controller.update(id, firstName, lastName, gender, email, year, className);
//
//            if (result == 1) {
//                response.sendRedirect("all-students");
//            } else {
//                response.getOutputStream().println("<h1>Failed to update the Student </h1>");
//            }


             new StudentHibernateDao().updateStudent(new Student(id, firstName, lastName, gender, email, year, className));
            response.sendRedirect("all-students");


        } catch (Exception e) {
            response.getOutputStream().println("<h1>Failed to update the Student </h1>");
            e.printStackTrace();
        }
    }
}
