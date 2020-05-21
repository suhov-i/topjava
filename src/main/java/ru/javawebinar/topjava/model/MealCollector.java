package ru.javawebinar.topjava.model;


import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MealCollector implements Collector<UserMeal, Map<LocalDate, Integer>, Map<LocalDate, Integer>> {


    @Override
    public Supplier<Map<LocalDate, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<LocalDate, Integer>, UserMeal> accumulator() {
        return (map, val) -> map.merge(val.getDateTime().toLocalDate(), val.getCalories(), Integer::sum);
    }

    @Override
    public BinaryOperator<Map<LocalDate, Integer>> combiner() {
        return (map1, map2) -> {
            map2.forEach((k, v) -> map1.merge(k, v, Integer::sum));
            return map1;
        };
    }

    @Override
    public Function<Map<LocalDate, Integer>, Map<LocalDate, Integer>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
