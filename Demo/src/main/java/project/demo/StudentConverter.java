package project.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentConverter {

    @Autowired
    private ModelMapper modelMapper;

    public List<StudentDTO> convertModelToDTO(List<Student> students){
        List<StudentDTO> result = new ArrayList<>(students.size());

        students.forEach(student -> {
            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
            result.add(studentDTO);
        });
        return result;
    }
}
