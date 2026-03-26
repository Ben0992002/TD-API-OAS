package hei.school.demo.controller;

import hei.school.demo.exception.BadRequestException;
import hei.school.demo.model.Student;
import hei.school.demo.service.StudentService;
import hei.school.demo.validator.StudentValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentValidator studentValidator;

    // INJECTION PAR CONSTRUCTEUR (Pas de @Autowired !)
    public StudentController(StudentService studentService, StudentValidator studentValidator) {
        this.studentService = studentService;
        this.studentValidator = studentValidator;
    }

    @PostMapping
    public ResponseEntity<?> createStudents(@RequestBody List<Student> newStudents) {
        try {
            // 1. On valide (SRP)
            studentValidator.validate(newStudents);

            // 2. On enregistre (SRP)
            List<Student> saved = studentService.saveAll(newStudents);

            // 3. On répond (Code 200 OK)
            return ResponseEntity.ok(saved);

        } catch (BadRequestException e) {
            // 4. Si le validateur a jeté une exception, on renvoie une erreur 400
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
