package hei.school.demo.controller;

import hei.school.demo.entity.Dish;
import hei.school.demo.service.DishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DishController {
    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @GetMapping("/dishes")
    public List<Dish> getDishes() {
        return service.getAllDishes();
    }
}

