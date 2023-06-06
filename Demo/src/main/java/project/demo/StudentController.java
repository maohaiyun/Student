package project.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This is a REST API
@RestController
@RequestMapping("/student")
@CrossOrigin(maxAge=45000)
public class StudentController {

    @Autowired
//    @Qualifier("fullTime")
    private IStudentService studentService;

    @Autowired
    private StudentConverter studentConverter;

    //GET all the students
    @GetMapping("/")
    public ResponseEntity<List<StudentDTO>> getAll(){
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(studentConverter.convertModelToDTO(students), HttpStatus.OK);
    }

    //Get the student by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Student> getStudentByID(@PathVariable int id){
        return new ResponseEntity<>(studentService.getStudentByID(id), HttpStatus.OK);
    }
    //Get the student by name
    @GetMapping("/find")
    public ResponseEntity<List<Student>> getStudentByName(@RequestParam String name){
        return new ResponseEntity<>(studentService.getStudentByName(name), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> createStudent(@RequestBody Student student){
        try{
            return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
        }catch(StudentNameExistException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }
    //Update a student
    @PutMapping("/id/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable int id, @RequestBody Student student){
        try{
            studentService.updateStudent(id, student);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (StudentNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }catch(StudentNameExistException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    //Delete an student
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id){
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StudentNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
