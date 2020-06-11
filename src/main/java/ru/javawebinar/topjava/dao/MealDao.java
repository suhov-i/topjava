package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDao {
    void create(String description, LocalDateTime dateTime, int calories);
    void delete(int id);
    void update(int id, String description, LocalDateTime dateTime, int calories);
    List<Meal> getAll();
    Meal get(int id);
}
