package hei.school.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(@RequestParam(name = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            return "Le nom est absent !";
        }
        return "Welcome " + name;
    }
}

