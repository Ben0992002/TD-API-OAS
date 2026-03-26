package hei.school.demo.service;

import hei.school.demo.model.Student;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> studentsInMemory = new ArrayList<>();

    public List<Student> saveAll(List<Student> students) {
        studentsInMemory.addAll(students);
        return studentsInMemory;
    }

    public List<Student> getAll() {
        return studentsInMemory;
    }
}
