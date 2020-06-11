package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDao {
    void create(String description, LocalDateTime dateTime, int calories);
    void update(Meal meal);
    void delete(int id);
    List<Meal> getAll();
    Meal get(int id);
}
