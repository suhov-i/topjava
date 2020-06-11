package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.List;

public class MealDaoListImpl implements MealDao {
    private static int id = -1; //TODO: thread-safe

    static {
        MealRepo.init();
    }

    @Override
    public void create(String description, LocalDateTime dateTime, int calories) {
        Meal meal = new Meal(dateTime, description, calories);
        meal.setId(++id);
        MealRepo.meals.add(meal);
    }

    @Override
    public void delete(int id) {
        MealRepo.meals.remove(get(id));
    }

    @Override
    public void update(int id, String description, LocalDateTime dateTime, int calories) {
        Meal meal = get(id);
        meal.setDescription(description);
        meal.setCalories(calories);
        meal.setDateTime(dateTime);
    }

    @Override
    public List<Meal> getAll() {
        return MealRepo.meals;
    }

    @Override
    public Meal get(int id) {
        return MealRepo.meals.stream().filter(m -> m.getId() == id).findFirst().get();
    }
}
