package me.liberi.sms.controllers;

import me.liberi.sms.dao.StudentDAO;
import me.liberi.sms.dao.StudentHibernateDao;
import me.liberi.sms.models.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentController {
    private final StudentHibernateDao dao;

    public StudentController() throws SQLException {
        dao = new StudentHibernateDao();
    }

    public List<Student> getStudents() throws SQLException {
        System.out.println("fetching students");
        return dao.getAllStudent();
    }

    public Student getById(int id) throws SQLException {
        return dao.getStudent(id);
    }

    public void create(String firstName, String lastName, String gender, String email, String year, String className) throws SQLException {
         dao.saveStudent(new Student(firstName, lastName, gender, email , year, className));
    }

    public void update(int id, String firstName, String lastName, String gender, String email, String year, String className) throws SQLException {
        Student student = new Student(firstName, lastName, gender, email, year, className);
        student.setId(id);
        dao.updateStudent(student);
    }

    public void delete(int id) throws SQLException {
        Student student = new Student(id);
        dao.deleteStudent(id);
    }
}
