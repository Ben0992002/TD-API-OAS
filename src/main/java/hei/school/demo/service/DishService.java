package hei.school.demo.service;

import hei.school.demo.entity.Dish;
import hei.school.demo.repository.DishRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DishService {
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public List<Dish> getAllDishes() {
        return repository.findAll();
    }
}
