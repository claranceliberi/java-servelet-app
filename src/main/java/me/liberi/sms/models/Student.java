package me.liberi.sms.models;

import javax.persistence.*;

@Entity()
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length=64)
    private int id;
    @Column(name="firstName",nullable = true, unique = true)
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "email")
    private String email;
    @Column(name = "year")
    private String year;
    @Column(name = "className")
    private String  className;

    public Student(){}

    public Student(int id){
        this.id = id;
    }

    public Student(String firstName, String lastName, String gender, String email, String year, String className) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.year = year;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String  getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
