package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.dao.MealDaoListImpl;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MealRepo {
    public static List<Meal> meals = new ArrayList<>();
    private static final MealDaoListImpl dao = new MealDaoListImpl();

    public static void init() {
        dao.create("Завтрак", LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), 500);
        dao.create( "Обед", LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), 1000);
        dao.create( "Ужин", LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), 500);
        dao.create( "Еда на граничное значение", LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), 100);
        dao.create( "Завтрак", LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), 1000);
        dao.create( "Обед", LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), 500);
        dao.create( "Ужин", LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), 410);
    }
}
