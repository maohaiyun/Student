package project.demo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private long id;
    private String studentID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String cellphoneNumber;
}
