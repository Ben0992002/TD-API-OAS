package hei.school.demo.controller;

import hei.school.demo.entity.Student;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> studentList = new ArrayList<>();

    // --- RÉPONSE À LA QUESTION B ---
    @PostMapping("/students")
    public List<Student> createStudents(@RequestBody List<Student> entries) {
        studentList.addAll(entries);
        return studentList;
    }

    // --- RÉPONSE À LA QUESTION C ---
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentList;
    }
}
