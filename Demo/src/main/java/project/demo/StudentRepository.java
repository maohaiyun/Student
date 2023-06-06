package project.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
//    CREATE TABLE `test`.`student` (
//            `id` INT NOT NULL AUTO_INCREMENT,
//  `studentID` VARCHAR(50) NULL,
//  `firstName` VARCHAR(50) NULL,
//  `lastName` VARCHAR(50) NULL,
//  `emailAddress` VARCHAR(200) NULL,
//  `cellphoneNumber` VARCHAR(50) NULL,
//    PRIMARY KEY (`id`));
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Student> getAllStudents(){
        return jdbcTemplate.query("SELECT ID, studentID, firstName, lastName, emailAddress, cellphoneNumber FROM student", new StudentRowMapper());
    }

    public Student getStudentByID(int id) {
        try {
            String sql = "SELECT * FROM student where ID=?";
            return jdbcTemplate.queryForObject(sql, new StudentRowMapper(), id);
        }catch (EmptyResultDataAccessException exception){
            return null;
        }
    }
    public List<Student> getStudentByName(String name){
        String sql = "SELECT * FROM student where firstName=?";
        List<Student> students = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Student.class), name);
        return students;
    }

    public Long createStudent(Student student){
        String sql = "INSERT INTO student(studentID, firstName, lastName, emailAddress, cellphoneNumber) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, student.getStudentID(), student.getFirstName(), student.getLastName(),
                student.getEmailAddress(), student.getCellphoneNumber());
        return jdbcTemplate.queryForObject("SELECT MAX(ID) from student", Long.class);
    }

    public void updateStudent(long id, Student student){
        String sql = "UPDATE student set studentID=?, firstName=?, lastName=?, emailAddress=?, cellphoneNumber=? WHERE id=?";
        jdbcTemplate.update(sql, student.getStudentID(), student.getFirstName(), student.getLastName(),
                student.getEmailAddress(), student.getCellphoneNumber(), id);
    }

    public void deleteStudent(long id){
        String sql = "DELETE FROM student WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
