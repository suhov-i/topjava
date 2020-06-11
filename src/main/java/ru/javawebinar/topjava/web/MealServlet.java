package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDaoListImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDaoListImpl dao = new MealDaoListImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");

        final LocalTime startTime = LocalTime.MIN;
        final LocalTime endTime = LocalTime.MAX;

        String forward = "";
        String action = req.getParameter("action");

        if (action == null) {
            forward = "/meals.jsp";
            List<MealTo> mealToList = MealsUtil.filteredByStreams(dao.getAll(), startTime, endTime, 2000);
            req.setAttribute("mealsList", mealToList);
            req.getRequestDispatcher(forward).forward(req, resp);
            return;
        }

        if (action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            forward = "/meals.jsp";
            List<MealTo> mealToList = MealsUtil.filteredByStreams(dao.getAll(), startTime, endTime, 2000);
            req.setAttribute("mealsList", mealToList);
        } else {
            Meal meal = action.equalsIgnoreCase("create") ?
                    new Meal(LocalDateTime.now(), "", 1000) :
                    dao.get(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("meal", meal);
            forward = "/mealsEdit.jsp";
        }
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("dateTime"));
        int calories = Integer.parseInt(req.getParameter("calories"));

        dao.create(description, ldt, calories);

        req.setAttribute("meals", dao.getAll());
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
