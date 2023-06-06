package project.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static project.demo.ErrorMessage.ERROR_STUDENT_EXISTS;
import static project.demo.ErrorMessage.ERROR_STUDENT_NOT_FOUND;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository repository;
    public List<Student> getAllStudents(){
        return repository.getAllStudents();
    }
    public Student getStudentByID(int id){
        Student student = repository.getStudentByID(id);
        if(student == null){
            throw new StudentNotFoundException(ERROR_STUDENT_NOT_FOUND);
        }
        return student;
    }
    public List<Student> getStudentByName(String name){
        return repository.getStudentByName(name);
    }
    public long createStudent(Student student){
        List<Student> foundStudents = getStudentByName(student.getFirstName());
        if(foundStudents.size() == 0){
            return repository.createStudent(student);
        }
        else{
            throw new StudentNameExistException(ERROR_STUDENT_EXISTS);
        }

    }
    public void updateStudent(int id, Student student){
        Student foundStudent = repository.getStudentByID(id);
        if(foundStudent == null){
            throw new StudentNotFoundException(ERROR_STUDENT_NOT_FOUND);
        }
        repository.updateStudent(id, student);
    }
    public void deleteStudent(int id){
        Student foundStudent = repository.getStudentByID(id);
        if(foundStudent == null){
            throw new StudentNotFoundException(ERROR_STUDENT_NOT_FOUND);
        }
        repository.deleteStudent(id);
    }
}
