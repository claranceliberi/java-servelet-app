package me.liberi.sms.views;

import me.liberi.sms.controllers.StudentController;
import me.liberi.sms.models.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ViewAStudent", value = "/view-student")
public class ViewStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        StudentController controller = null;
        Student student = null;
        int studentId = Integer.parseInt(request.getParameter("id"));

        try {
            controller = new StudentController();
            student = controller.getById(studentId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(student != null){
            try {
                request.setAttribute("student", controller.getById(studentId));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewStudent.jsp");
            dispatcher.forward(request,response);
        }else{
            response.sendRedirect("all-students");
        }

        response.getOutputStream().println("<h1>Failed to delete a student "+request.getParameter("id")+"</h1>");
    }
}
