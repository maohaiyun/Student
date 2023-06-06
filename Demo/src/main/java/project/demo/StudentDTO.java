package project.demo;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private long id;
    private long studentID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String cellphoneNumber;
}
