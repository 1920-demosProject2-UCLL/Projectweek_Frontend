package db;

import domain.Student;

import java.util.List;

public interface StudentRepository {

    void add(Student student);

    List<Student> getStudents();

}
