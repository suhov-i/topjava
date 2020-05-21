package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.MealCollector;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsToCycles = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsToCycles.forEach(System.out::println);

        System.out.println("====");

        List<UserMealWithExcess> mealsToStreams = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsToStreams.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesByDay = new HashMap<>();    //maps each day on consumed calories
        for (UserMeal meal : meals) {
            LocalDate date = meal.getDateTime().toLocalDate();
            caloriesByDay.merge(date, meal.getCalories(), Integer::sum);
        }

        List<UserMealWithExcess> result = new ArrayList<>();        //result list
        for (UserMeal meal : meals)
            if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime))
                result.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        caloriesByDay.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));

        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesByDay;          //maps each day on consumed calories
        caloriesByDay = meals
                            .stream()
                            .collect(Collectors.toMap(m -> m.getDateTime().toLocalDate(), UserMeal::getCalories, Integer::sum));

        Map<LocalDate, Integer> finalCaloriesByDay = caloriesByDay;
        return meals
                    .stream()
                    //without it collect will throw NPE because we reduce list and not all KEY dates in map are present
                    .filter(m -> Objects.nonNull(finalCaloriesByDay.get(m.getDateTime().toLocalDate())))
                    .filter(m -> TimeUtil.isBetweenHalfOpen(m.getDateTime().toLocalTime(), startTime, endTime))
                    .map(m -> new UserMealWithExcess(m.getDateTime(), m.getDescription(), m.getCalories(),
                            finalCaloriesByDay.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                    .collect(Collectors.toList());


//        System.out.println("TEST:");
//        System.out.println(meals.stream()
//                .collect(new MealCollector()));
//
//        return null;





    }
}
