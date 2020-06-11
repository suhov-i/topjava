package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private  LocalDateTime dateTime;

    private  String description;

    private  int calories;

//    private final AtomicBoolean excess;      // filteredByAtomic
//    private final Boolean excess;            // filteredByReflection
//    private final Supplier<Boolean> excess;  // filteredByClosure
    private  boolean excess;

    private int id;

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess, int id) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        this.id = id;
    }

//    public Boolean getExcess() {
//        return excess.get();
//    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
