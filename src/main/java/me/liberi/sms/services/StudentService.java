package me.liberi.sms.services;

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

@WebServlet(name = "Students", value = "/students")
public class StudentService extends HttpServlet {
//    private final StudentController controller = new StudentController();
    private final StudentHibernateDao studentDao = new StudentHibernateDao();

    public StudentService() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int studentId = Integer.parseInt(request.getParameter("id"));

        switch (action) {
            case "DELETE":
//                boolean result = false;
//                try {
//                    result = controller.delete(studentId);
//                } catch (SQLException throwable) {
//                    throwable.printStackTrace();
//                }
//                if(result) response.sendRedirect("all-students");
//                else response.getOutputStream().println("<h1>Failed to delete a student</h1>");

                try {
                    studentDao.deleteStudent(studentId);
                    response.sendRedirect("all-students");
                } catch (Exception throwable) {
                    response.getOutputStream().println("<h1>Failed to delete a student</h1>");
                    throwable.printStackTrace();
                }

                break;
            case "REDIRECT_TO_UPDATE":
                try {
                    request.setAttribute("student", studentDao.getStudent(studentId));
                } catch (Exception throwable) {
                    throwable.printStackTrace();
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("editStudent.jsp");
                dispatcher.forward(request,response);

                break;
            default:
                response.sendRedirect("all-students");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String lastName = request.getParameter("last_name");
        String firstName = request.getParameter("first_name");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String year = request.getParameter("year");
        String className = request.getParameter("class");


        StudentController controller;
        try {
//            controller = new StudentController();
//            int result = controller.create(firstName, lastName, gender, email, year, className);
//            if (result == 1) {
//            } else {
//                response.getOutputStream().println("<h1>Failed to create the Student </h1>");
//            }
            studentDao.saveStudent(new Student(firstName,lastName,gender,email,year,className));
            response.sendRedirect("all-students");
        } catch (Exception e) {
            response.getOutputStream().println("<h1>Failed to create the Student </h1>");
            e.printStackTrace();
        }
    }
}
