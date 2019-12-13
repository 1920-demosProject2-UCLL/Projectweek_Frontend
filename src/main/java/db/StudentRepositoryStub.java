package db;

import domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepositoryStub implements StudentRepository {

    private Map<String, Student> students = new HashMap<>();

    public StudentRepositoryStub() {
        Student one = new Student("Student", "One", "https://github.com/...", "r0000001");
        add(one);
        Student two = new Student("Student", "Two", "https://github.com/...", "r0000002");
        add(two);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students.values());
    }

    public void add(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("No student given");
        }
        if (students.containsKey(student.getrNumber())) {
            throw new IllegalArgumentException("Student already exists");
        }
        students.put(student.getrNumber(), student);
    }

}
