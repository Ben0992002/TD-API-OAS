package hei.school.demo.service;

import hei.school.demo.entity.CreateDish;
import hei.school.demo.entity.Dish;
import hei.school.demo.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public List<Dish> createAll(List<CreateDish> createDishes) throws SQLException {
        List<Dish> savedDishes = new ArrayList<>();
        for (CreateDish d : createDishes) {
            if (repository.existsByName(d.name())) {
                // On lance une exception personnalisée ou une RuntimeException
                throw new IllegalArgumentException("Dish.name=" + d.name() + " already exists");
            }
            savedDishes.add(repository.save(d));
        }
        return savedDishes;
    }

    public List<Dish> getAllFiltered(Double pMax, Double pMin, String name) {
        return repository.findByCriteria(pMax, pMin, name);
    }
}
