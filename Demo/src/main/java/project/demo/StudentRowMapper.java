package project.demo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Student.builder().id(rs.getInt("id")).
                studentID(rs.getString("studentID")).
                firstName(rs.getString("firstName")).
                lastName(rs.getString("lastName")).
                emailAddress(rs.getString("emailAddress")).
                cellphoneNumber(rs.getString("cellphoneNumber")).
                build();
    }
}
