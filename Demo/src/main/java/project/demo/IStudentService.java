package project.demo;

import java.util.List;

public interface IStudentService {

    List<Student> getAllStudents();
    Student getStudentByID(int id);

    List<Student> getStudentByName(String name);

    long createStudent(Student student);

    void updateStudent(int id, Student student);

    void deleteStudent(int id);
}
