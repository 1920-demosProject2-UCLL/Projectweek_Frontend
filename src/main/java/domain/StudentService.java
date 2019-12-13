package domain;

import db.StudentRepository;
import db.StudentRepositoryStub;

import java.util.List;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepositoryStub();

    public StudentService() {
    }

    public List<Student> getStudents() {
        return getStudentRepository().getStudents();
    }

    private StudentRepository getStudentRepository() {
        return studentRepository;
    }

}
