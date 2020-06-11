package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.List;

public class MealDaoListImpl implements MealDao {
    @Override
    public void create(String description, LocalDateTime dateTime, int calories) {
        MealsUtil.meals.add(new Meal(dateTime, description, calories));
    }

    @Override
    public void update(Meal meal) {
        delete(meal.getId());
        create(meal.getDescription(), meal.getDateTime(), meal.getCalories());
    }

    @Override
    public void delete(int id) {
        MealsUtil.meals.remove(get(id));
    }

    @Override
    public List<Meal> getAll() {
        return MealsUtil.meals;
    }

    @Override
    public Meal get(int id) {
        return MealsUtil.meals.stream().filter(m -> m.getId() == id).findFirst().get();
    }
}
