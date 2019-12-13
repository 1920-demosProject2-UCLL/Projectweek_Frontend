package controller;

import domain.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {

    private StudentService studentService;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public void setModel(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentService getService() {
        return studentService;
    }

}
