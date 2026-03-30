package hei.school.demo.controller;

import hei.school.demo.exception.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // L'URL sera http://localhost:8080/hello
    @GetMapping("/hello")
    public String home(@RequestParam(required = false) String name) {

        // 1. controller vérifie si le message est vide
        if (name != null && name.trim().isEmpty()) {

            // 2. Lancement de l'alerte !!!
            throw new BadRequestException("Le nom ne peut pas être vide");
        }

        // 3. Si tout va bien, on répond normalement
        return "Hello " + (name == null ? "Spring Boot 🚀" : name);
    }
}

