package hei.school.demo.validator;

import hei.school.demo.model.Student;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StudentValidator {
    public void validate(List<Student> students) {
        for (Student s : students) {
            // SRP : On ne gère que la vérification ici
            if (s.getReference() == null || s.getReference().isBlank()) {
                throw new RuntimeException("NewStudent.reference cannot be null");
            }
        }
    }
}
