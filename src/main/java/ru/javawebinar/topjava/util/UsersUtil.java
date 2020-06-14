package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(null, "Vasya Pupkin", "vp@mail.ru", "12345", Role.USER),
            new User(null, "Petya Vasin", "pv@mail.ru", "456", Role.USER),
            new User(null, "Masha Petrova", "mp@mail.ru", "475", Role.USER),
            new User(null, "Sveta Ivanova", "si@mail.ru", "4585", Role.USER),
            new User(null, "Vladimir Putin", "vput@mail.ru", "124", Role.ADMIN),
            new User(null, "Boris Eltsin", "be@mail.ru", "33555", Role.USER)
    );
}
